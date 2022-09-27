package com.zpl.eshop.comment.service;

import com.zpl.eshop.comment.domain.CommentAggregateDTO;
import com.zpl.eshop.comment.domain.CommentInfoDTO;

/**
 * 评论统计信息管理模块的service组件接口
 * @author ZhangPeiL1n
 *
 */
public interface CommentAggregateService {

	/**
	 * 更新评论统计信息
	 * @param commentInfoDTO 评论信息
	 * @return 处理结果
	 */
	CommentAggregateDTO refreshCommentAggregate(
			CommentInfoDTO commentInfoDTO) throws Exception;
	
	/**
	 * 根据商品id查询评论统计信息
	 * @param goodsId 商品id
	 * @return 评论统计信息
	 */
	CommentAggregateDTO getCommentAggregateByGoodsId(
			Long goodsId) throws Exception;
	
}
