package com.zpl.eshop.cart.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/23 16:41
 **/
@Getter
@Setter
public class AddShoppingCartItemQuery {
    /**
     * 用户帐号id
     */
    private Long userAccountId;
    /**
     * 商品SkuId
     */
    private Long goodsSkuId;
}
