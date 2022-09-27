package com.zpl.eshop.comment.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 评论聚合统计信息
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentAggregateDO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 商品id
	 */
	private Long goodsId;

	/**
	 * 总评论数
	 */
	private Long totalCommentCount;

	/**
	 * 好评数
	 */
	private Long goodCommentCount;

	/**
	 * 好评率
	 */
	private Double goodCommentRate;

	/**
	 * 晒图评论数
	 */
	private Long showPicturesCommentCount;

	/**
	 * 中评评论数
	 */
	private Long mediumCommentCount;

	/**
	 * 差评评论数
	 */
	private Long badCommentCount;

	/**
	 * 评论聚合统计信息的创建时间
	 */
	private Date gmtCreate;

	/**
	 * 评论聚合统计信息的修改时间
	 */
	private Date gmtModified;
}
