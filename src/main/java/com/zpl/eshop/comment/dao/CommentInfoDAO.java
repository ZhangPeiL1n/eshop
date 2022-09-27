package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoQuery;

import java.util.List;

/**
 * 评论信息管理模块的DAO组件接口
 * @author ZhangPeiL1n
 *
 */
public interface CommentInfoDAO {

	/**
	 * 新增评论信息
	 * @param commentInfoDO 评论信息DO对象
	 */
	Long saveCommentInfo(CommentInfoDO commentInfoDO);
	
	/**
	 * 分页查询评论信息
	 * @param query 评论查询条件
	 * @return 评论信息
	 */
	List<CommentInfoDO> listByPage(CommentInfoQuery query);
	
	/**
	 * 根据id查询评论信息
	 * @param id 评论信息id
	 * @return 评论信息
	 */
	CommentInfoDO getById(Long id);
	
	/**
	 * 更新评论
	 * @param comment 评论信息
	 */
	Boolean update(CommentInfoDO comment);
	
	/**
	 * 删除评论
	 * @param id 评论id
	 */
	Boolean remove(Long id);
	
}
