package com.zpl.eshop.comment.service.impl;

import com.zpl.eshop.comment.constant.*;
import com.zpl.eshop.comment.dao.CommentInfoDAO;
import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.comment.domain.CommentInfoQuery;
import com.zpl.eshop.comment.service.CommentInfoService;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 评论信息管理模块的service组件
 *
 * @author ZhangPeiL1n
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentInfoServiceImpl implements CommentInfoService {

    private static final Integer GOOD_COMMENT_THRESHOLD = 4;
    private static final Integer MEDIUM_COMMENT_THRESHOLD = 3;
    private static final Integer BAD_COMMENT_THRESHOLD = 2;
    /**
     * 评论信息管理模块的DAO组件
     */
    @Autowired
    private CommentInfoDAO commentInfoDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增手动发表的评论信息
     *
     * @param commentInfo 评论信息DTO对象
     */
    @Override
    public Boolean saveManualPublishedCommentInfo(CommentInfoDTO commentInfo) throws Exception {
        // 计算评论的总分数
        Integer totalScore = Math.round((commentInfo.getGoodsScore()
                + commentInfo.getCustomerServiceScore()
                + commentInfo.getLogisticsScore()) / 3);
        commentInfo.setTotalScore(totalScore);

        // 设置是否为默认评论
        commentInfo.setDefaultComment(DefaultComment.NO);

        // 设置评论的状态
        commentInfo.setCommentStatus(CommentStatus.APPROVING);

        // 设置评论的类型
        Integer commentType = 0;
        if (totalScore >= GOOD_COMMENT_THRESHOLD) {
            commentType = CommentType.GOOD_COMMENT;
        } else if (totalScore.equals(MEDIUM_COMMENT_THRESHOLD)) {
            commentType = CommentType.MEDIUM_COMMENT;
        } else if (totalScore > 0 && totalScore <= BAD_COMMENT_THRESHOLD) {
            commentType = CommentType.BAD_COMMENT;
        }

        commentInfo.setCommentType(commentType);

        // 设置创建时间和修改时间
        commentInfo.setGmtCreate(dateProvider.getCurrentTime());
        commentInfo.setGmtModified(dateProvider.getCurrentTime());

        // 将评论信息保存到数据库中去
        CommentInfoDO commentInfoDO = commentInfo.clone(CommentInfoDO.class);
        commentInfoDAO.saveCommentInfo(commentInfoDO);

        // 设置评论信息的id
        commentInfo.setId(commentInfoDO.getId());
        return true;
    }

    /**
     * 新增自动发表的评论信息
     *
     * @param orderInfo 订单信息DTO对象
     * @param orderItem 订单条目DTO对象
     * @return 处理结果
     */
    @Override
    public CommentInfoDTO saveAutoPublishedCommentInfo(
            OrderInfoDTO orderInfo, OrderItemDTO orderItem) throws Exception {
        CommentInfoDTO commentInfo = createAutoPublishedCommentInfoDTO(orderInfo, orderItem);
        CommentInfoDO commentInfoDO = commentInfo.clone(CommentInfoDO.class);
        commentInfoDAO.saveCommentInfo(commentInfoDO);
        commentInfo.setId(commentInfoDO.getId());

        return commentInfo;
    }

    /**
     * 创建评论信息DTO对象
     *
     * @param orderInfo 订单信息DTO对象
     * @param orderItem 订单条目DTO对象
     * @return 评论信息DTO对象
     */
    private CommentInfoDTO createAutoPublishedCommentInfoDTO(
            OrderInfoDTO orderInfo, OrderItemDTO orderItem) throws Exception {
        CommentInfoDTO commentInfo = new CommentInfoDTO();

        commentInfo.setUserAccountId(orderInfo.getUserAccountId());
        commentInfo.setUsername(orderInfo.getUsername());
        commentInfo.setOrderInfoId(orderInfo.getId());
        commentInfo.setOrderItemId(orderItem.getId());
        commentInfo.setGoodsId(orderItem.getGoodsId());
        commentInfo.setGoodsSkuId(orderItem.getGoodsSkuId());
        commentInfo.setGoodsSkuSaleProperties(orderItem.getSaleProperties());
        commentInfo.setTotalScore(CommentInfoScore.FIVE);
        commentInfo.setGoodsScore(CommentInfoScore.FIVE);
        commentInfo.setCustomerServiceScore(CommentInfoScore.FIVE);
        commentInfo.setLogisticsScore(CommentInfoScore.FIVE);
        commentInfo.setCommentContent(CommentContent.DEFAULT);
        commentInfo.setShowPictures(ShowPictures.NO);
        commentInfo.setDefaultComment(DefaultComment.YES);
        commentInfo.setCommentStatus(CommentStatus.APPROVED);
        commentInfo.setCommentType(CommentType.GOOD_COMMENT);
        commentInfo.setGmtCreate(dateProvider.getCurrentTime());
        commentInfo.setGmtModified(dateProvider.getCurrentTime());

        return commentInfo;
    }

    /**
     * 分页查询评论信息
     *
     * @param query 评论查询条件
     * @return 评论信息
     */
    @Override
    public List<CommentInfoDTO> listByPage(CommentInfoQuery query) throws Exception {
        List<CommentInfoDO> comments = commentInfoDAO.listByPage(query);
        return ObjectUtils.convertList(comments, CommentInfoDTO.class);
    }

    /**
     * 根据id查询评论信息
     *
     * @param id 评论信息id
     * @return 评论信息
     */
    @Override
    public CommentInfoDTO getById(Long id) throws Exception {
        CommentInfoDO comment = commentInfoDAO.getById(id);
        return comment.clone(CommentInfoDTO.class);
    }

    /**
     * 更新评论
     *
     * @param comment 评论信息
     */
    @Override
    public Boolean update(CommentInfoDTO comment) throws Exception {
        comment.setGmtModified(dateProvider.getCurrentTime());
        CommentInfoDO targetComment = comment.clone(CommentInfoDO.class);
        return commentInfoDAO.update(targetComment);
    }

    /**
     * 删除评论
     *
     * @param id 删除评论
     * @return 处理结果
     */
    @Override
    public Boolean remove(Long id) throws Exception {
        return commentInfoDAO.remove(id);
    }

}
