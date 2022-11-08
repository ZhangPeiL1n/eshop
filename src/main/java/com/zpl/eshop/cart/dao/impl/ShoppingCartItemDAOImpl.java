package com.zpl.eshop.cart.dao.impl;

import com.zpl.eshop.cart.dao.ShoppingCartItemDAO;
import com.zpl.eshop.cart.domain.ShoppingCartItemDO;
import com.zpl.eshop.cart.mapper.ShoppingCartItemMapper;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车条目管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/20 22:38
 **/
@Repository
public class ShoppingCartItemDAOImpl implements ShoppingCartItemDAO {

    /**
     * 购物车条目管理模块mapper
     */
    @Autowired
    private ShoppingCartItemMapper shoppingCartItemMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增购物车条目
     *
     * @param shoppingCartItem 购物车条目DO对象
     * @throws Exception
     */
    @Override
    public void saveShoppingCartItem(ShoppingCartItemDO shoppingCartItem) throws Exception {
        shoppingCartItem.setGmtCreate(dateProvider.getCurrentTime());
        shoppingCartItem.setGmtModified(dateProvider.getCurrentTime());
        shoppingCartItemMapper.saveShoppingCartItem(shoppingCartItem);
    }

    /**
     * 根据购物车和商品id查询购物车条目
     *
     * @param shoppingCartId 购物车id
     * @param goodsSkuId     商品id
     * @return 购物车条目DO
     * @throws Exception
     */
    @Override
    public ShoppingCartItemDO getShoppingCartItemByGoodsSkuId(Long shoppingCartId, Long goodsSkuId) throws Exception {
        return shoppingCartItemMapper.getShoppingCartItemByGoodsSkuId(shoppingCartId, goodsSkuId);
    }


    /**
     * 更新购物车条目
     *
     * @param shoppingCartItem 更新购物车条目
     * @throws Exception
     */
    @Override
    public void updateShoppingCartItem(ShoppingCartItemDO shoppingCartItem) throws Exception {
        shoppingCartItem.setGmtModified(dateProvider.getCurrentTime());
        shoppingCartItemMapper.updateShoppingCartItem(shoppingCartItem);
    }

    /**
     * 查询购物车中的所有条目
     *
     * @param shoppingCartId 购物车id
     * @return 购物车中所有条目DO集合
     * @throws Exception
     */
    @Override
    public List<ShoppingCartItemDO> listShoppingCartItemByCartId(Long shoppingCartId) throws Exception {
        return shoppingCartItemMapper.listShoppingCartItemByCartSkuId(shoppingCartId);
    }

    /**
     * 删除购物车条目
     *
     * @param id 购物车条目id
     * @throws Exception
     */
    @Override
    public void remove(Long id) throws Exception {
        shoppingCartItemMapper.remove(id);
    }
}
