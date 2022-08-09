package com.zpl.eshop.order.price;

import com.zpl.eshop.order.domain.OrderItemDTO;
import org.springframework.stereotype.Component;

/**
 * 总金额默认计算器
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 17:07
 **/
@Component
public class DefaultTotalPriceCalculator implements TotalPriceCalculator {

    @Override
    public Double calculate(OrderItemDTO item) {
        return item.getPurchasePrice() * item.getPurchaseQuantity();
    }
}
