package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentAggregateDAO;
import com.zpl.eshop.comment.domain.CommentAggregateDO;
import com.zpl.eshop.comment.mapper.CommentAggregateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 评论统计信息管理模块的DAO组件
 * @author ZhangPeiL1n
 *
 */
@Repository
public class CommentAggregateDAOImpl implements CommentAggregateDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentAggregateDAOImpl.class);

	/**
	 * 评论信息管理模块的mapper组件
	 */
	@Autowired
	private CommentAggregateMapper commentAggregateMapper;
	
	/**
	 * 根据商品id查询评论统计信息
	 * @param goodsId 商品id
	 * @return 评论统计信息
	 */
	public CommentAggregateDO getCommentAggregateByGoodsId(Long goodsId) {
		try {
			return commentAggregateMapper.getCommentAggregateByGoodsId(goodsId);
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 新增评论统计信息
	 * @param commentAggregateDO 评论统计信息DO对象
	 */
	public Boolean saveCommentAggregate(CommentAggregateDO commentAggregateDO) {
		try {
			commentAggregateMapper.saveCommentAggregate(commentAggregateDO); 
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
		return true;
	}
	
	/**
	 * 更新评论统计信息
	 * @param commentAggregateDO 评论统计信息DO对象
	 */
	public Boolean updateCommentAggregate(CommentAggregateDO commentAggregateDO) {
		try {
			commentAggregateMapper.updateCommentAggregate(commentAggregateDO);
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
		return true;
	}
	
}
