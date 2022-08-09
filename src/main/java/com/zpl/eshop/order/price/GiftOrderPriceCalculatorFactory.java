package com.zpl.eshop.order.price;

import com.zpl.eshop.promotion.constant.PromotionActivityType;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 赠品类型的订单价格计算工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 18:21
 **/
@Component
public class GiftOrderPriceCalculatorFactory implements OrderPriceCalculatorFactory {

    /**
     * 默认总金额计算组件
     */
    @Autowired
    private DefaultTotalPriceCalculator totalPriceCalculator;

    /**
     * 满赠类型的促销活动计算组件
     */
    @Autowired
    private ReachGiftPromotionActivityCalculator reachGiftPromotionActivityCalculator;

    /**
     * 赠品类型的促销活动计算组件
     */
    @Autowired
    private DirectGiftPromotionActivityCalculator directGiftPromotionActivityCalculator;

    /**
     * 包含赠品的运费计算组件
     */
    @Autowired
    private GiftIncludedFreightCalculator giftIncludedFreightCalculator;

    /**
     * 默认促销活动计算组件
     */
    @Autowired
    private DefaultPromotionActivityCalculator defaultPromotionActivityCalculator;

    /**
     * 创建订单总金额计算组件
     *
     * @return 订单总金额计算组件
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
        if (PromotionActivityType.DIRECT_GIFT.equals(promotionActivityType)) {
            return directGiftPromotionActivityCalculator;
        } else if (PromotionActivityType.REACH_GIFT.equals(promotionActivityType)) {
            return reachGiftPromotionActivityCalculator;
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
        return giftIncludedFreightCalculator;
    }
}
