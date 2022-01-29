package com.zpl.eshop.order.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 订单条目DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/1/11 21:46
 **/
@Getter
@Setter
@ToString
public class OrderItemDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderInfoId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品 sku id
     */
    private Long goodsSkuId;

    /**
     * 商品编码
     */
    private String goodsSkuCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品sku的销售属性
     */
    private String saleProperties;

    /**
     * 商品毛重
     */
    private Double goodsGrossWeight;

    /**
     * 商品sku的购买数量
     */
    private Long purchaseQuantity;

    /**
     * 商品sku的购买价格
     */
    private Double purchasePrice;

    /**
     * 商品sku使用的促销活动id
     */
    private Long promotionActivityId;

    /**
     * 产品长度
     */
    private Double goodsLength;

    /**
     * 商品宽度
     */
    private Double goodsWidth;

    /**
     * 商品高度
     */
    private Double goodsHeight;

    /**
     * 订单创建时间
     */
    private Date gmtCreate;

    /**
     * 订单更新时间
     */
    private Date gmtModified;

}
