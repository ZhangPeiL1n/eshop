package com.zpl.eshop.order.price;

import com.zpl.eshop.logistics.service.LogisticsService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 含赠品的运费计算器
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 17:15
 **/
@Component
public class GiftIncludedFreightCalculator implements FreightCalculator {

    /**
     * 物流中心接口
     */
    @Autowired
    private LogisticsService logisticsService;

    /**
     * 计算运费
     *
     * @param order     订单
     * @param orderItem 订单条目
     * @param result    计算结果
     * @return
     */
    @Override
    public Double calculate(OrderInfoDTO order, OrderItemDTO orderItem, PromotionActivityResult result) {
        Double freight = 0.0;
        freight += logisticsService.calculateFreight(order, orderItem);
        List<OrderItemDTO> gifts = result.getOrderItems();
        for (OrderItemDTO gift : gifts) {
            freight += logisticsService.calculateFreight(order, gift);
        }
        return freight;
    }
}
