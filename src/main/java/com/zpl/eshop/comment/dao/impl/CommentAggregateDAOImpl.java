package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentAggregateDAO;
import com.zpl.eshop.comment.domain.CommentAggregateDO;
import com.zpl.eshop.comment.mapper.CommentAggregateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 评论统计信息管理模块的DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class CommentAggregateDAOImpl implements CommentAggregateDAO {

    /**
     * 评论信息管理模块的mapper组件
     */
    @Autowired
    private CommentAggregateMapper commentAggregateMapper;

    /**
     * 根据商品id查询评论统计信息
     *
     * @param goodsId 商品id
     * @return 评论统计信息
     */
    @Override
    public CommentAggregateDO getCommentAggregateByGoodsId(Long goodsId) {
        return commentAggregateMapper.getCommentAggregateByGoodsId(goodsId);
    }

    /**
     * 新增评论统计信息
     *
     * @param commentAggregateDO 评论统计信息DO对象
     */
    @Override
    public Boolean saveCommentAggregate(CommentAggregateDO commentAggregateDO) {
        commentAggregateMapper.saveCommentAggregate(commentAggregateDO);
        return true;
    }

    /**
     * 更新评论统计信息
     *
     * @param commentAggregateDO 评论统计信息DO对象
     */
    @Override
    public Boolean updateCommentAggregate(CommentAggregateDO commentAggregateDO) {
        commentAggregateMapper.updateCommentAggregate(commentAggregateDO);
        return true;
    }

}
