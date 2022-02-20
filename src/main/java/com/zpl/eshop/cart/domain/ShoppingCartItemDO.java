package com.zpl.eshop.cart.domain;

import lombok.Data;

import java.util.Date;

/**
 * 购物车条目类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/20 21:55
 **/
@Data
public class ShoppingCartItemDO {

    /**
     * id
     */
    private Long id;

    /**
     * 购物车id
     */
    private Long shoppingCartId;

    /**
     * 商品SKUid
     */
    private Long goodsSkuId;

    /**
     * 购买数量
     */
    private Long purchaseQuantity;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
