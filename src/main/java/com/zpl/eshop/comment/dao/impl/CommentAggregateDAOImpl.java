package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentAggregateDAO;
import com.zpl.eshop.comment.domain.CommentAggregateDO;
import com.zpl.eshop.comment.mapper.CommentAggregateMapper;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 评论统计信息管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/18 21:44
 **/
@Repository
public class CommentAggregateDAOImpl implements CommentAggregateDAO {
    /**
     * 评论统计信息管理模块Mapper
     */
    @Autowired
    private CommentAggregateMapper commentAggregateMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 根据商品 id 查询评论统计信息
     *
     * @param goodsId 商品id
     * @return 评论统计信息
     * @throws Exception
     */
    @Override
    public CommentAggregateDO getCommentAggregateByGoodsId(Long goodsId) throws Exception {
        return commentAggregateMapper.getCommentAggregateByGoodsId(goodsId);
    }

    /**
     * 新增评论统计信息
     *
     * @param commentAggregate 评论统计信息DO
     * @throws Exception
     */
    @Override
    public void saveCommentAggregate(CommentAggregateDO commentAggregate) throws Exception {
        commentAggregate.setGmtCreate(dateProvider.getCurrentTime());
        commentAggregate.setGmtModified(dateProvider.getCurrentTime());
        commentAggregateMapper.saveCommentAggregate(commentAggregate);
    }

    /**
     * 更新评论统计信息
     *
     * @param commentAggregate 评论统计信息DO
     * @throws Exception
     */
    @Override
    public void updateCommentAggregate(CommentAggregateDO commentAggregate) throws Exception {
        commentAggregate.setGmtModified(dateProvider.getCurrentTime());
        commentAggregateMapper.updateCommentAggregate(commentAggregate);
    }
}
