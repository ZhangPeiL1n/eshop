package com.zpl.eshop.comment.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 评论信息
 *
 * @author ZhangPeiL1n
 * @date 2022/1/11 22:14
 **/
@Getter
@Setter
@ToString
public class CommentInfoVO extends AbstractObject {

    private final Logger logger = LoggerFactory.getLogger(CommentInfoVO.class);

    /**
     * id
     */
    private Long id;

    /**
     * 用户帐号id
     */
    private Long userAccountId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 订单信息id;
     */
    private Long orderInfoId;

    /**
     * 订单条目id
     */
    private Long orderItemId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品 sku id
     */
    private Long goodsSkuId;

    /**
     * 商品销售属性
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
    private Integer showPicture;

    /**
     * 是否系统自动给的默认评论
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
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 修改时间
     */
    private String gmtModified;

    /**
     * 评论图片集合
     */
    private List<CommentPictureVO> pictures;
}
