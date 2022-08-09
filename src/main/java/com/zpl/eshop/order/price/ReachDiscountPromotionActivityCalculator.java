package com.zpl.eshop.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.stereotype.Component;

/**
 * 满减类型的促销活动的处理器
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 17:10
 **/
@Component
public class ReachDiscountPromotionActivityCalculator implements PromotionActivityCalculator {

    /**
     * 计算促销活动的减免金额
     *
     * @return 减免金额
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) {
        double totalAmount = item.getPurchasePrice() * item.getPurchaseQuantity();

        String rulesJson = promotionActivity.getRule();
        JSONArray rules = JSONArray.parseArray(rulesJson);
        for (int i = 0; i < rules.size(); i++) {
            JSONObject rule = rules.getJSONObject(i);
            Double thresholdAmount = rule.getDouble("thresholdAmount");
            if (totalAmount > thresholdAmount) {
                return new PromotionActivityResult(rule.getDouble("reduceAmount"));
            }
        }
        return new PromotionActivityResult();
    }
}
