package com.zpl.eshop.order.price;

import com.zpl.eshop.promotion.constant.PromotionActivityType;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 折扣见面订单价格计算组件工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 18:07
 **/
@Component
public class DiscountOrderPriceCalculatorFactory implements OrderPriceCalculatorFactory {

    /**
     * 默认总金额计算组件
     */
    @Autowired
    private DefaultTotalPriceCalculator totalPriceCalculator;

    /**
     * 满减型的促销活动计算组件
     */
    @Autowired
    private ReachDiscountPromotionActivityCalculator reachDiscountPromotionActivityCalculator;

    /**
     * 单品促销活动计算组件
     */
    @Autowired
    private DirectDiscountPromotionActivityCalculator directDiscountPromotionActivityCalculator;

    /**
     * 多买优惠促销活动计算组件
     */
    @Autowired
    private MultiDiscountPromotionActivityCalculator multiDiscountPromotionActivityCalculator;

    /**
     * 默认促销活动计算组件
     */
    @Autowired
    private DefaultPromotionActivityCalculator defaultPromotionActivityCalculator;

    /**
     * 默认运费计算组件
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
        return totalPriceCalculator;
    }

    /**
     * 创建促销活动金额计算组件
     *
     * @return 促销活动金额计算组件
     */
    @Override
    public PromotionActivityCalculator createPromotionActivityCalculator(PromotionActivityDTO promotionActivity) {
        if (promotionActivity == null) {
            return defaultPromotionActivityCalculator;
        }
        Integer promotionActivityType = promotionActivity.getType();
        if (PromotionActivityType.DIRECT_DISCOUNT.equals(promotionActivityType)) {
            return directDiscountPromotionActivityCalculator;
        } else if (PromotionActivityType.MULTI_DISCOUNT.equals(promotionActivityType)) {
            return multiDiscountPromotionActivityCalculator;
        } else if (PromotionActivityType.REACH_DISCOUNT.equals(promotionActivityType)) {
            return reachDiscountPromotionActivityCalculator;
        }
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
