package com.zpl.eshop.comment.service;

import com.zpl.eshop.comment.domain.CommentAggregateDTO;
import com.zpl.eshop.comment.domain.CommentInfoDTO;

/**
 * 评论统计信息管理模块Service接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/18 21:50
 **/
public interface CommentAggregateService {


    /**
     * 更新评论统计信息
     *
     * @param commentInfoDTO 评论信息DTO
     * @return 更新是否成功
     */
    CommentAggregateDTO refreshCommentAggregate(CommentInfoDTO commentInfoDTO) throws Exception;

    /**
     * 根据商品 id 查询评论统计信息
     *
     * @param goodsId 商品id
     * @return 评论统计信息
     */
    CommentAggregateDTO getCommentAggregateByGoodsId(Long goodsId) throws Exception;
}
