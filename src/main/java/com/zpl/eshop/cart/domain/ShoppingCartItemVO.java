package com.zpl.eshop.cart.domain;

import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 购物车条目VO类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/9 0:34
 **/
@Data
public class ShoppingCartItemVO {

    /**
     * id
     */
    private Long id;

    /**
     * 购物车id
     */
    private Long shoppingCartId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品skuId
     */
    private Long goodsSkuId;

    /**
     * 商品sku编号
     */
    private String goodsSkuCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 销售属性
     */
    private String saleProperties;

    /**
     * 商品Sku售价
     */
    private Double salePrice;

    /**
     * 商品毛重
     */
    private Double grossWeight;

    /**
     * 商品长度
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
     * 购买数量
     */
    private Long purchaseQuantity;

    /**
     * 销售库存
     */
    private Long saleStockQuantity;

    /**
     * 促销活动集合
     */
    private List<PromotionActivityDTO> promotionActivityDTOList;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;


}
