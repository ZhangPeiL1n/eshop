package com.zpl.eshop.comment.service;

import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.comment.domain.CommentInfoQuery;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;

import java.util.List;

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

    /**
     * 分页列表查询
     *
     * @param query 分页查询条件
     * @return 评论信息
     */
    List<CommentInfoDTO> listByPage(CommentInfoQuery query);

    /**
     * 根据id获取评论详情
     *
     * @param id 评论id
     * @return 评论详情
     */
    CommentInfoDTO getById(Long id);
}
