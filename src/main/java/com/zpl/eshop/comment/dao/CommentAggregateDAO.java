package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentAggregateDO;

/**
 * 评论统计信息管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/18 21:42
 **/
public interface CommentAggregateDAO {

    /**
     * 根据商品 id 查询评论统计信息
     *
     * @param goodsId 商品id
     * @return 评论统计信息
     */
    CommentAggregateDO getCommentAggregateByGoodsId(Long goodsId);


    /**
     * 新增评论统计信息
     *
     * @param commentAggregateDO 评论统计信息DO
     * @return 新增是否成功
     */
    Boolean saveCommentAggregate(CommentAggregateDO commentAggregateDO);

    /**
     * 更新评论统计信息
     *
     * @param commentAggregateDO 评论统计信息DO
     * @return 更新是否成功
     */
    Boolean updateCommentAggregate(CommentAggregateDO commentAggregateDO);
}
