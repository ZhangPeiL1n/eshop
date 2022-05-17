package com.zpl.eshop.cart.controller;

import com.zpl.eshop.cart.domain.AddShoppingCartItemQuery;
import com.zpl.eshop.cart.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/20 23:00
 **/
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);
    /**
     * 购物车管理模块Service组件
     */
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车条目
     *
     * @param query 用户帐号id
     * @return 操作结果
     */
    @PostMapping("/item/add")
    public boolean addShoppingCartItem(@RequestBody AddShoppingCartItemQuery query) {
        try {
            return shoppingCartService.addShoppingCartItem(query.getUserAccountId(), query.getGoodsSkuId());
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
