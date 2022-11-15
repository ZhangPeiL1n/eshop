package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentAggregateDO;

/**
 * 评论管理模块DAO组件接口
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
     * @throws Exception
     */
    CommentAggregateDO getCommentAggregateByGoodsId(Long goodsId) throws Exception;


    /**
     * 新增评论统计信息
     *
     * @param commentAggregate 评论统计信息DO
     * @throws Exception
     */
    void saveCommentAggregate(CommentAggregateDO commentAggregate) throws Exception;

    /**
     * 更新评论统计信息
     *
     * @param commentAggregate 评论统计信息DO
     * @throws Exception
     */
    void updateCommentAggregate(CommentAggregateDO commentAggregate) throws Exception;
}
