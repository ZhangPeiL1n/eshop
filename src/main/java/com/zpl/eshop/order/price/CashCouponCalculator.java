package com.zpl.eshop.order.price;

import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.common.json.JsonExtractor;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.promotion.domain.CouponDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 现金券抵扣金额计算组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 14:23
 **/
@Component
public class CashCouponCalculator implements CouponCalculator {

    /**
     * json 字段值提取器
     */
    @Autowired
    private JsonExtractor jsonExtractor;

    @Override
    public Double calculate(OrderInfoDTO order, CouponDTO coupon) throws Exception {
        JSONObject rule = JSONObject.parseObject(coupon.getRule());
        Double discountAmount = jsonExtractor.getDouble(rule, "discountAmount");
        Double payAbleAmount = order.getPayableAmount();
        if (discountAmount > payAbleAmount) {
            return payAbleAmount;
        }
        return discountAmount;
    }
}
