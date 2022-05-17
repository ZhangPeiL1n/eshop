package com.zpl.eshop.cart.dao.impl;

import com.zpl.eshop.cart.dao.ShoppingCartItemDAO;
import com.zpl.eshop.cart.domain.ShoppingCartItemDO;
import com.zpl.eshop.cart.mapper.ShoppingCartItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 购物车条目管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/20 22:38
 **/
@Repository
public class ShoppingCartItemDAOImpl implements ShoppingCartItemDAO {

    private final Logger logger = LoggerFactory.getLogger(ShoppingCartItemDAOImpl.class);
    /**
     * 购物车条目管理模块mapper
     */
    @Autowired
    private ShoppingCartItemMapper shoppingCartItemMapper;

    /**
     * 新增购物车条目
     *
     * @param shoppingCartItemDO 购物车条目DO对象
     * @return 新增成功返回true
     */
    @Override
    public Long saveShoppingCartItem(ShoppingCartItemDO shoppingCartItemDO) {
        try {
            shoppingCartItemMapper.saveShoppingCartItem(shoppingCartItemDO);
            return shoppingCartItemDO.getId();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据购物车和商品id查询购物车条目
     *
     * @param shoppingCartId 购物车id
     * @param goodsSkuId     商品id
     * @return 购物车条目DO
     */
    @Override
    public ShoppingCartItemDO getShoppingCartItemByGoodsSkuId(Long shoppingCartId, Long goodsSkuId) {
        try {
            return shoppingCartItemMapper.getShoppingCartItemByGoodsSkuId(shoppingCartId, goodsSkuId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }


    /**
     * 更新购物车条目
     *
     * @param shoppingCartItemDO 更新购物车条目
     * @return 更新成功返回 true
     */
    @Override
    public Boolean updateShoppingCartItem(ShoppingCartItemDO shoppingCartItemDO) {
        try {
            shoppingCartItemMapper.updateShoppingCartItem(shoppingCartItemDO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

}
