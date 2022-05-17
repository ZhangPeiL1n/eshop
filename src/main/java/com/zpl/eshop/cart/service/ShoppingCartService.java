package com.zpl.eshop.cart.service;

import java.text.ParseException;

/**
 * 购物车管理模块Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/20 22:45
 **/
public interface ShoppingCartService {

    /**
     * 添加购物车条目
     *
     * @param userAccountId 用户帐号id
     * @param goodsSkuId    商品条目
     * @return 处理结果
     */
    Boolean addShoppingCartItem(Long userAccountId, Long goodsSkuId) throws ParseException;


}
