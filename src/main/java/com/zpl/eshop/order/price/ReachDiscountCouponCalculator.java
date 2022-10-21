package com.zpl.eshop.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.common.json.JsonExtractor;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.promotion.domain.CouponDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 满减券抵扣金额计算组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 14:47
 **/
@Component
public class ReachDiscountCouponCalculator implements CouponCalculator {

    /**
     * json 字段值提取器
     */
    @Autowired
    private JsonExtractor jsonExtractor;

    @Override
    public Double calculate(OrderInfoDTO order, CouponDTO coupon) throws Exception {
        Double payableAmount = order.getPayableAmount();

        String rulesJson = coupon.getRule();
        JSONArray rules = JSONArray.parseArray(rulesJson);
        for (int i = 0; i < rules.size(); i++) {
            JSONObject rule = rules.getJSONObject(i);
            Double thresholdAmount = jsonExtractor.getDouble(rule, "thresholdAmount");
            if (payableAmount > thresholdAmount) {
                return jsonExtractor.getDouble(rule, "reduceAmount");
            }
        }

        return 0.0;
    }
}
