package com.zpl.eshop.cart.dao.impl;

import com.zpl.eshop.cart.dao.ShoppingCartDAO;
import com.zpl.eshop.cart.domain.ShoppingCartDO;
import com.zpl.eshop.cart.mapper.ShoppingCartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ShoppingCartDAOImpl.class);
    /**
     * 购物车管理模块DAO组件
     */
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 根据用户帐号获取购物车
     *
     * @param userAccountId 用户帐号
     * @return 购物车
     */
    @Override
    public ShoppingCartDO getShoppingCartByUserAccountId(Long userAccountId) {
        try {
            logger.debug("testString");
            return shoppingCartMapper.getShoppingCartByUserAccountId(userAccountId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 新增购物车
     *
     * @param shoppingCartDO 购物车DO
     * @return 插入成功返回 true
     */
    @Override
    public Long saveShoppingCart(ShoppingCartDO shoppingCartDO) {
        try {
            shoppingCartMapper.saveShoppingCart(shoppingCartDO);
            return shoppingCartDO.getId();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
