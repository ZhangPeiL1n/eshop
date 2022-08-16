package com.zpl.eshop.order.price;

import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.promotion.domain.CouponDTO;
import org.springframework.stereotype.Component;

/**
 * 现金券抵扣金额计算组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 14:23
 **/
@Component
public class CashCouponCalculator implements CouponCalculator {
    @Override
    public Double calculate(OrderInfoDTO order, CouponDTO coupon) {
        JSONObject rule = JSONObject.parseObject(coupon.getRule());
        Double discountAmount = rule.getDouble("discountAmount");
        Double payAbleAmount = order.getPayableAmount();
        if (discountAmount > payAbleAmount) {
            return payAbleAmount;
        }
        return discountAmount;
    }
}
