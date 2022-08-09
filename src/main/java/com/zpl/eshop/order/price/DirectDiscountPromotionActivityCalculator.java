package com.zpl.eshop.order.price;

import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.stereotype.Component;

/**
 * 单品促销活动的计算组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 20:37
 **/
@Component
public class DirectDiscountPromotionActivityCalculator implements PromotionActivityCalculator {

    /**
     * 计算促销活动的减免金额
     *
     * @param item              商品条目
     * @param promotionActivity 促销活动
     * @return 见面金额
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) {
        double totalAmount = item.getPurchasePrice() * item.getPurchaseQuantity();
        JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
        Double discountRate = rule.getDouble("discountRate");
        return new PromotionActivityResult(totalAmount * (1 - discountRate));
    }
}
