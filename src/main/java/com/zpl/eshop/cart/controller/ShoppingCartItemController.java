package com.zpl.eshop.cart.controller;

import com.zpl.eshop.cart.service.ShoppingCartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车条目管理 controller
 *
 * @author ZhangPeiL1n
 * @date 2022/5/28 10:59
 **/
@RestController
@RequestMapping("/cart/item")
public class ShoppingCartItemController {
    /**
     * 日期辅助组件
     */
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartItemController.class);
    /**
     * 购物车条目管理模块 service 组件
     */
    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    /**
     * 更新购物车条目
     *
     * @param id               条目id
     * @param purchaseQuantity 购买数量
     * @return 操作结果
     */
    @PutMapping("/{id}")
    public Boolean updateShoppingCartItem(@PathVariable("id") Long id, Long purchaseQuantity) {
        try {
            return shoppingCartItemService.updatePurchaseQuantity(id, purchaseQuantity);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除购物车条目
     *
     * @param id 购物车条目
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            return shoppingCartItemService.remove(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
