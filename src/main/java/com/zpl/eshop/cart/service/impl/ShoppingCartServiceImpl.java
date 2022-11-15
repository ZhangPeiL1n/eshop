package com.zpl.eshop.cart.service.impl;

import com.zpl.eshop.cart.dao.ShoppingCartDAO;
import com.zpl.eshop.cart.dao.ShoppingCartItemDAO;
import com.zpl.eshop.cart.domain.ShoppingCartDO;
import com.zpl.eshop.cart.domain.ShoppingCartItemDO;
import com.zpl.eshop.cart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/20 22:48
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingCartServiceImpl implements ShoppingCartService {

    /**
     * 购物车管理模块DAO组件
     */
    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    /**
     * 购物车条目管理模块DAO组件
     */
    @Autowired
    private ShoppingCartItemDAO shoppingCartItemDAO;

    /**
     * 添加购物车条目
     *
     * @param userAccountId 用户帐号id
     * @param goodsSkuId    商品条目
     * @return 处理结果
     */
    @Override
    public Boolean addShoppingCartItem(Long userAccountId, Long goodsSkuId) throws Exception {
        // 根据用户帐号id查找一下购物车
        ShoppingCartDO shoppingCart = shoppingCartDAO.getShoppingCartByUserAccountId(userAccountId);
        // 如果购物车不存在，则创建一个购物车
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCartDO();
            shoppingCart.setUserAccountId(userAccountId);
            shoppingCartDAO.saveShoppingCart(shoppingCart);
        }

        // 查询一下购物车中是否存在这个商品条目
        ShoppingCartItemDO shoppingCartItem = shoppingCartItemDAO.getShoppingCartItemByGoodsSkuId(shoppingCart.getId(), goodsSkuId);
        // 如果不存在就新增
        if (shoppingCartItem == null) {
            shoppingCartItem = new ShoppingCartItemDO();
            shoppingCartItem.setShoppingCartId(shoppingCart.getId());
            shoppingCartItem.setGoodsSkuId(goodsSkuId);
            shoppingCartItem.setPurchaseQuantity(1L);
            shoppingCartItemDAO.saveShoppingCartItem(shoppingCartItem);
            // 如果存在则购买数量 +1
        } else {
            shoppingCartItem.setPurchaseQuantity(shoppingCartItem.getPurchaseQuantity() + 1L);
            shoppingCartItemDAO.updateShoppingCartItem(shoppingCartItem);
        }
        return true;
    }
}
