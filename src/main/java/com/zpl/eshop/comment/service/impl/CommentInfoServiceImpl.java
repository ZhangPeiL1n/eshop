package com.zpl.eshop.comment.service.impl;

import com.zpl.eshop.comment.constant.*;
import com.zpl.eshop.comment.dao.CommentInfoDAO;
import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.comment.service.CommentInfoService;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评论信息管理模块 Service 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/12 22:40
 **/
@Service
public class CommentInfoServiceImpl implements CommentInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CommentInfoServiceImpl.class);
    /**
     * 评论信息管理模块DAO组件
     */
    @Autowired
    private CommentInfoDAO commentInfoDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 手动新增评论信息
     *
     * @param commentInfoDTO 评论信息对象
     * @return 新增是否成功
     */
    @Override
    public Boolean saveManualPublishedCommentInfo(CommentInfoDTO commentInfoDTO) {
        try {
            // 计算总分数
            Integer totalScore = Math.round((commentInfoDTO.getGoodsScore()
                    + commentInfoDTO.getLogisticsScore()
                    + commentInfoDTO.getCustomerServiceScore()) / 3);
            commentInfoDTO.setTotalScore(totalScore);

            // 设置是否是默认评论
            commentInfoDTO.setDefaultComment(DefaultComment.NO);
            // 设置评论状态
            commentInfoDTO.setCommentStatus(CommentStatus.APPROVING);
            // 设置评论的类型
            Integer commentType = 0;
            if (totalScore >= 4) {
                commentType = CommentType.GOOD_COMMENT;
            } else if (totalScore >= 3) {
                commentType = CommentType.MEDIUM_COMMENT;
            } else if (totalScore > 0) {
                commentType = CommentType.BAD_COMMENT;
            }
            commentInfoDTO.setCommentType(commentType);

            commentInfoDTO.setGmtCreate(dateProvider.getCurrentTime());
            commentInfoDTO.setGmtModified(dateProvider.getCurrentTime());

            // 保存评论信息
            CommentInfoDO commentInfoDO = commentInfoDTO.clone(CommentInfoDO.class);
            Long commentInfoId = commentInfoDAO.saveCommentInfo(commentInfoDO);
            //设置评论信息的 id
            commentInfoDTO.setId(commentInfoDO.getId());
            return commentInfoId > 0L;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 新增自动发表的评论信息
     *
     * @param orderInfoDTO 订单信息DTO
     * @param orderItemDTO 订单项DTO
     * @return 新增成功返回 true
     */
    @Override
    public CommentInfoDTO saveAutoPublishedCommentInfo(OrderInfoDTO orderInfoDTO, OrderItemDTO orderItemDTO) {
        CommentInfoDTO commentInfoDTO = null;
        try {
            commentInfoDTO = createAutoPublishedCommentInfoDTO(orderInfoDTO, orderItemDTO);
            // 保存评论信息
            CommentInfoDO commentInfoDO = commentInfoDTO.clone(CommentInfoDO.class);
            commentInfoDAO.saveCommentInfo(commentInfoDO);
            //设置评论信息的 id
            commentInfoDTO.setId(commentInfoDO.getId());
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
        return commentInfoDTO;
    }

    /**
     * 创建评论信息DTO对象
     *
     * @param orderInfoDTO 订单信息DTO
     * @param orderItemDTO 订单条目DTO
     * @return 评论信息DTO对象
     */
    public CommentInfoDTO createAutoPublishedCommentInfoDTO(OrderInfoDTO orderInfoDTO, OrderItemDTO orderItemDTO) throws Exception {
        // 创建评论信息DTO对象
        CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
        commentInfoDTO.setUserAccountId(orderInfoDTO.getUserAccountId());
        commentInfoDTO.setUsername(orderInfoDTO.getUsername());
        commentInfoDTO.setOrderInfoId(orderInfoDTO.getId());
        commentInfoDTO.setOrderItemId(orderItemDTO.getId());
        commentInfoDTO.setGoodsId(orderItemDTO.getGoodsId());
        commentInfoDTO.setGoodsSkuId(orderItemDTO.getGoodsSkuId());
        commentInfoDTO.setGoodsSkuSaleProperties(orderItemDTO.getSaleProperties());
        // 计算总分数
        commentInfoDTO.setTotalScore(CommentInfoScore.FIVE);
        commentInfoDTO.setGoodsScore(CommentInfoScore.FIVE);
        commentInfoDTO.setCustomerServiceScore(CommentInfoScore.FIVE);
        commentInfoDTO.setLogisticsScore(CommentInfoScore.FIVE);
        commentInfoDTO.setCommentContent(CommentContent.DEFAULT);
        commentInfoDTO.setShowPicture(ShowPictures.NO);
        // 设置是否是默认评论
        commentInfoDTO.setDefaultComment(DefaultComment.YES);
        // 设置评论状态
        commentInfoDTO.setCommentStatus(CommentStatus.APPROVED);
        // 设置评论的类型
        commentInfoDTO.setCommentType(CommentType.GOOD_COMMENT);
        commentInfoDTO.setGmtCreate(dateProvider.getCurrentTime());
        commentInfoDTO.setGmtModified(dateProvider.getCurrentTime());
        return commentInfoDTO;
    }
}
