package com.zpl.eshop.order.price;

import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.stereotype.Component;

/**
 * 没有促销活动的计算组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 19:38
 **/
@Component
public class DefaultPromotionActivityCalculator implements PromotionActivityCalculator {

    /**
     * 没有绑定促销活动的商品金额计算
     *
     * @return 不减
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) {
        return new PromotionActivityResult();
    }
}
