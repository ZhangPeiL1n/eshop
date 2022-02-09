package com.zpl.eshop.cart.dao;

import com.zpl.eshop.cart.domain.ShoppingCartItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param shoppingCartItemDO 购物车条目DO对象
     * @return 新增成功返回true
     */
    Long saveShoppingCartItem(ShoppingCartItemDO shoppingCartItemDO);

    /**
     * 根据购物车和商品id查询购物车条目
     *
     * @param shoppingCartId 购物车id
     * @param goodsSkuId     商品id
     * @return 购物车条目DO
     */
    ShoppingCartItemDO getShoppingCartItemByGoodsSkuId(@Param("shoppingCartId") Long shoppingCartId, @Param("goodsSkuId") Long goodsSkuId);


    /**
     * 更新购物车条目
     *
     * @param shoppingCartItemDO 更新购物车条目
     * @return 更新成功返回 true
     */
    Boolean updateShoppingCartItem(ShoppingCartItemDO shoppingCartItemDO);

    /**
     * 查询购物车中的所有条目
     *
     * @param shoppingCartId 购物车id
     * @return 购物车中所有条目DO集合
     */
    List<ShoppingCartItemDO> listShoppingCartItemByCartSkuId(@Param("shoppingCartId") Long shoppingCartId);


}
