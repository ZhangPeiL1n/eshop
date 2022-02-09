package com.zpl.eshop.cart.controller;

import com.zpl.eshop.cart.domain.*;
import com.zpl.eshop.cart.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查看购物车
     *
     * @param userAccountId 用户帐号id
     * @return 购物车
     */
    @GetMapping("/view/{userAccountId}")
    public ShoppingCartVO getShoppingCartVO(@PathVariable("userAccountId") Long userAccountId) {
        try {
            ShoppingCartDTO shoppingCartDTO = shoppingCartService.getShoppingCartDTOByUserAccountId(userAccountId);
            ShoppingCartVO shoppingCartVO = shoppingCartDTO.clone(ShoppingCartVO.class);

            List<ShoppingCartItemVO> shoppingCartItemVOList = new ArrayList<>();
            shoppingCartVO.setShoppingCartItemVOList(shoppingCartItemVOList);
            for (ShoppingCartItemDTO shoppingCartItemDTO : shoppingCartDTO.getShoppingCartItemDTOList()) {
                shoppingCartItemVOList.add(shoppingCartItemDTO.clone(ShoppingCartItemVO.class));
            }
            return shoppingCartVO;
        } catch (Exception e) {
            logger.error("error", e);
            return new ShoppingCartVO();
        }
    }
}
