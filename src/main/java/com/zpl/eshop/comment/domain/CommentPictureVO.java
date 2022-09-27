package com.zpl.eshop.comment.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 评论晒图
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentPictureVO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 评论信息id
	 */
	private Long commentInfoId;

	/**
	 * 评论图片路径
	 */
	private String commentPicturePath;

	/**
	 * 评论晒图的创建时间
	 *
	 */
	private Date gmtCreate;

	/**
	 * 评论晒图的修改时间
	 */
	private Date gmtModified;
}
