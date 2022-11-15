package com.zpl.eshop.comment.service.impl;

import com.zpl.eshop.comment.constant.*;
import com.zpl.eshop.comment.dao.CommentInfoDAO;
import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.comment.service.CommentInfoService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 评论信息管理模块 Service 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/12 22:40
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentInfoServiceImpl implements CommentInfoService {

    /**
     * 评论信息管理模块DAO组件
     */
    @Autowired
    private CommentInfoDAO commentInfoDAO;

    /**
     * 新增评论信息
     *
     * @param commentInfo 评论信息对象
     * @return 新增是否成功
     */
    @Override
    public saveManualPublishedCommentInfo(CommentInfoDTO commentInfo) throws Exception {
        // 计算总分数
        Integer totalScore = Math.round((commentInfo.getGoodsScore()
                + commentInfo.getLogisticsScore()
                + commentInfo.getCustomerServiceScore()) / 3);
        commentInfo.setTotalScore(totalScore);

        // 设置是否是默认评论
        commentInfo.setDefaultComment(DefaultComment.NO);
        // 设置评论状态
        commentInfo.setCommentStatus(CommentStatus.APPROVING);
        // 设置评论的类型
        Integer commentType = 0;
        int goodThreshold = 4;
        int midThreshold = 3;
        int badThreshold = 0;
        if (totalScore >= goodThreshold) {
            commentType = CommentType.GOOD_COMMENT;
        } else if (totalScore >= midThreshold) {
            commentType = CommentType.MEDIUM_COMMENT;
        } else if (totalScore > badThreshold) {
            commentType = CommentType.BAD_COMMENT;
        }
        commentInfo.setCommentType(commentType);

        // 保存评论信息
        Long id = commentInfoDAO.saveCommentInfo(commentInfo.clone(CommentInfoDO.class));
        //设置评论信息的 id
        commentInfo.setId(id);
        return true;
    }

    /**
     * 新增自动发表的评论信息
     *
     * @param orderInfo 订单信息DTO
     * @param orderItem 订单项DTO
     * @return 新增成功返回 true
     */
    @Override
    public CommentInfoDTO saveAutoPublishedCommentInfo(OrderInfoDTO orderInfo, OrderItemDTO orderItem) throws Exception {
        CommentInfoDTO commentInfoDTO = createCommentInfoDTO(orderInfo, orderItem);
        // 保存评论信息
        Long id = commentInfoDAO.saveCommentInfo(commentInfoDTO.clone(CommentInfoDO.class));
        //设置评论信息的 id
        commentInfoDTO.setId(id);
        return commentInfoDTO;
    }

    /**
     * 创建评论信息DTO对象
     *
     * @param orderInfo 订单信息DTO
     * @param orderItem 订单条目DTO
     * @return 评论信息DTO对象
     */
    private CommentInfoDTO createCommentInfoDTO(OrderInfoDTO orderInfo, OrderItemDTO orderItem) {
        // 创建评论信息DTO对象
        CommentInfoDTO commentInfo = new CommentInfoDTO();
        commentInfo.setUserAccountId(orderInfo.getUserAccountId());
        commentInfo.setUsername(orderInfo.getUsername());
        commentInfo.setOrderInfoId(orderInfo.getId());
        commentInfo.setOrderItemId(orderItem.getId());
        commentInfo.setGoodsId(orderItem.getGoodsId());
        commentInfo.setGoodsSkuId(orderItem.getGoodsSkuId());
        commentInfo.setGoodsSkuSaleProperties(orderItem.getSaleProperties());
        // 计算总分数
        commentInfo.setTotalScore(CommentInfoScore.FIVE);
        commentInfo.setGoodsScore(CommentInfoScore.FIVE);
        commentInfo.setCustomerServiceScore(CommentInfoScore.FIVE);
        commentInfo.setLogisticsScore(CommentInfoScore.FIVE);
        commentInfo.setCommentContent(CommentContent.DEFAULT);
        commentInfo.setShowPicture(ShowPicture.NO);
        // 设置是否是默认评论
        commentInfo.setDefaultComment(DefaultComment.YES);
        // 设置评论状态
        commentInfo.setCommentStatus(CommentStatus.APPROVED);
        // 设置评论的类型
        commentInfo.setCommentType(CommentType.GOOD_COMMENT);
        return commentInfo;
    }
}
