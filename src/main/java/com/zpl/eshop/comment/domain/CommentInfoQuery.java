package com.zpl.eshop.comment.domain;

import lombok.Data;

/**
 * 评论信息的查询条件
 *
 * @author ZhangPeiL1n
 */
@Data
public class CommentInfoQuery {

	/**
	 * 分页查询起始位置
	 */
	private Integer offset;

	/**
	 * 每页查询记录数
	 */
	private Integer size;

	/**
	 * 起始时间
	 */
	private String startTime;

	/**
	 * 结束时间
	 */
	private String endTime;

	/**
	 * 总评分
	 */
	private Integer totalScore;

	/**
	 * 评论状态
	 */
	private Integer commentStatus;

	/**
	 * 评论类型
	 */
	private Integer commentType;

	/**
	 * 是否晒图
	 */
	private Integer showPictures;

	/**
	 * 是否为默认评论
	 */
	private Integer defaultComment;
}
