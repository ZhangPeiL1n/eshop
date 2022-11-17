package com.zpl.eshop.cart.dao;

import com.zpl.eshop.cart.domain.ShoppingCartDO;

/**
 * 购物车管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/20 22:28
 **/
public interface ShoppingCartDAO {
    /**
     * 根据用户帐号获取购物车
     *
     * @param userAccountId 用户帐号
     * @return 购物车
     * @throws Exception
     */
    ShoppingCartDO getShoppingCartByUserAccountId(Long userAccountId) throws Exception;

    /**
     * 新增购物车
     *
     * @param shoppingCartDO 购物车DO
     * @return 插入成功返回 true
     * @throws Exception
     */
    Long saveShoppingCart(ShoppingCartDO shoppingCartDO) throws Exception;
}
