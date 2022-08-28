package com.zpl.eshop.order.price;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;

/**
 * 运费计算组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 17:13
 **/
public interface FreightCalculator {

    /**
     * 计算运费
     *
     * @param order  订单
     * @param item   订单条目
     * @param result 计算结果
     * @return 运费
     */
    Double calculate(OrderInfoDTO order, OrderItemDTO item, PromotionActivityResult result);
}
