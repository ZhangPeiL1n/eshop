package com.zpl.eshop.cart.dao;

import com.zpl.eshop.cart.domain.ShoppingCartItemDO;

/**
 * 购物车条目管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/20 22:36
 **/
public interface ShoppingCartItemDAO {

    /**
     * 新增购物车条目
     *
     * @param shoppingCartItem 购物车条目DO对象
     * @throws Exception
     */
    void saveShoppingCartItem(ShoppingCartItemDO shoppingCartItem) throws Exception;

    /**
     * 根据购物车和商品id查询购物车条目
     *
     * @param shoppingCartId 购物车id
     * @param goodsSkuId     商品id
     * @return 购物车条目DO
     * @throws Exception
     */
    ShoppingCartItemDO getShoppingCartItemByGoodsSkuId(Long shoppingCartId, Long goodsSkuId) throws Exception;


    /**
     * 更新购物车条目
     *
     * @param shoppingCartItem 更新购物车条目
     * @throws Exception
     */
    void updateShoppingCartItem(ShoppingCartItemDO shoppingCartItem) throws Exception;
}
