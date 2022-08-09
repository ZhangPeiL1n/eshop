package com.zpl.eshop.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.stereotype.Component;

/**
 * 多买优惠
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 20:36
 **/
@Component
public class MultiDiscountPromotionActivityCalculator implements PromotionActivityCalculator {

    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) {
        PromotionActivityResult result = new PromotionActivityResult();
        Long purchaseCount = item.getPurchaseQuantity();
        double totalAmount = item.getPurchasePrice() * item.getPurchaseQuantity();

        String rulesJson = promotionActivity.getRule();
        JSONArray rules = JSONArray.parseArray(rulesJson);
        for (int i = 0; i < rules.size(); i++) {
            JSONObject rule = rules.getJSONObject(i);
            Long thresholdCount = rule.getLong("thresholdCount");
            if (purchaseCount > thresholdCount) {
                return new PromotionActivityResult(rule.getDouble("discountRate"));
            }
        }
        return new PromotionActivityResult();
    }
}
