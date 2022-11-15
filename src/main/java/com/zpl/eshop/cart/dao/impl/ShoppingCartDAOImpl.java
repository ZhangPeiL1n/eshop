package com.zpl.eshop.cart.dao.impl;

import com.zpl.eshop.cart.dao.ShoppingCartDAO;
import com.zpl.eshop.cart.domain.ShoppingCartDO;
import com.zpl.eshop.cart.mapper.ShoppingCartMapper;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 购物车管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/20 22:29
 **/
@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO {

    /**
     * 购物车管理模块DAO组件
     */
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 根据用户帐号获取购物车
     *
     * @param userAccountId 用户帐号
     * @return 购物车
     * @throws Exception
     */
    @Override
    public ShoppingCartDO getShoppingCartByUserAccountId(Long userAccountId) throws Exception {
        return shoppingCartMapper.getShoppingCartByUserAccountId(userAccountId);
    }

    /**
     * 新增购物车
     *
     * @param shoppingCart 购物车DO
     * @return 插入成功返回 true
     * @throws Exception
     */
    @Override
    public Long saveShoppingCart(ShoppingCartDO shoppingCart) throws Exception {
        shoppingCart.setGmtCreate(dateProvider.getCurrentTime());
        shoppingCart.setGmtModified(dateProvider.getCurrentTime());
        shoppingCartMapper.saveShoppingCart(shoppingCart);
        return shoppingCart.getId();
    }
}
