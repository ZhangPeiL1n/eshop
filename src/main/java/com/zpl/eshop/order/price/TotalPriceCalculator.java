package com.zpl.eshop.order.price;

import com.zpl.eshop.order.domain.OrderItemDTO;

/**
 * 商品条目总金额计算器
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 17:06
 **/
public interface TotalPriceCalculator {


    /**
     * 计算总金额
     *
     * @param item
     * @return
     */
    Double calculate(OrderItemDTO item);
}
