package com.zpl.eshop.cart.service;

/**
 * 购物车条目管理 service 组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/5/28 10:35
 **/
public interface ShoppingCartItemService {
    /**
     * 删除购物车条目
     *
     * @param id 购物车条目id
     * @return 处理结果
     * @throws Exception
     */
    Boolean remove(Long id) throws Exception;

    /**
     * 更新购物车条目购买数量
     *
     * @param id               购物车条目id
     * @param purchaseQuantity 购买数量
     * @return 处理结果
     * @throws Exception
     */
    Boolean updatePurchaseQuantity(Long id, Long purchaseQuantity) throws Exception;
}
