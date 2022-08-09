package com.zpl.eshop.order.price;

import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 默认订单金额计算工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 19:37
 **/
@Component
public class DefaultOrderPriceCalculatorFactory implements OrderPriceCalculatorFactory {

    /**
     * 默认总金额计算组件
     */
    @Autowired
    private DefaultTotalPriceCalculator defaultTotalPriceCalculator;

    /**
     * 无促销活动金额计算器
     */
    @Autowired
    private DefaultPromotionActivityCalculator defaultPromotionActivityCalculator;

    /**
     * 默认运费计算器
     */
    @Autowired
    private DefaultFreightCalculator defaultFreightCalculator;

    /**
     * 创建总金额计算组件
     *
     * @return 总金额计算组件
     */
    @Override
    public TotalPriceCalculator createTotalPriceCalculator() {
        return defaultTotalPriceCalculator;
    }

    /**
     * 创建促销活动金额计算组件
     *
     * @return 促销活动金额计算组件
     */
    @Override
    public PromotionActivityCalculator createPromotionActivityCalculator(PromotionActivityDTO promotionActivity) {
        return defaultPromotionActivityCalculator;
    }

    /**
     * 创建运费计算组件
     *
     * @return 运费计算组件
     */
    @Override
    public FreightCalculator createFreightCalculator() {
        return defaultFreightCalculator;
    }
}
