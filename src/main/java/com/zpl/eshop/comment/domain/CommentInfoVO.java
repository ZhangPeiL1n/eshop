package com.zpl.eshop.comment.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 评论信息
 * @author ZhangPeiL1n
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentInfoVO extends AbstractObject {
	
	/**
	 * id
	 */
	private Long id;

	/**
	 * 用户账号id
	 */
	private Long userAccountId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 订单信息id
	 */
	private Long orderInfoId;

	/**
	 * 订单条目id
	 */
	private Long orderItemId;

	/**
	 * 订单条目对应的商品id
	 */
	private Long goodsId;

	/**
	 * 订单条目对应的商品sku id
	 */
	private Long goodsSkuId;

	/**
	 * 商品sku销售属性
	 */
	private String goodsSkuSaleProperties;

	/**
	 * 总评分
	 */
	private Integer totalScore;

	/**
	 * 商品评分
	 */
	private Integer goodsScore;

	/**
	 * 客服评分
	 */
	private Integer customerServiceScore;

	/**
	 * 物流评分
	 */
	private Integer logisticsScore;

	/**
	 * 评论内容
	 */
	private String commentContent;

	/**
	 * 是否晒图
	 */
	private Integer showPictures;

	/**
	 * 是否是默认评论
	 */
	private Integer defaultComment;

	/**
	 * 评论状态
	 */
	private Integer commentStatus;

	/**
	 * 评论类型
	 */
	private Integer commentType;

	/**
	 * 评论的创建时间
	 */
	private Date gmtCreate;

	/**
	 * 评论的修改时间
	 */
	private Date gmtModified;

	/**
	 * 评论图片集合
	 */
	private List<CommentPictureVO> pictures;
}
