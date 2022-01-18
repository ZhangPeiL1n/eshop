package com.zpl.eshop.comment.service;

import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;

/**
 * 评论信息管理模块 Service 接口组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/12 22:38
 **/
public interface CommentInfoService {
    /**
     * 新增手动发表的评论信息
     *
     * @param commentInfoDTO 评论信息DTO对象
     * @return 新增是否成功
     */
    Boolean saveManualPublishedCommentInfo(CommentInfoDTO commentInfoDTO);

    /**
     * 新增自动发表的评论信息
     *
     * @param orderInfoDTO 订单信息DTO
     * @param orderItemDTO 订单项DTO
     * @return 新增成功返回 true
     */
    CommentInfoDTO saveAutoPublishedCommentInfo(OrderInfoDTO orderInfoDTO, OrderItemDTO orderItemDTO);
}
