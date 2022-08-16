package com.zpl.eshop.order.price;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.promotion.domain.CouponDTO;
import org.springframework.stereotype.Component;

/**
 * 默认优惠券计算组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 15:06
 **/
@Component
public class DefaultCouponCalculator implements CouponCalculator {
    @Override
    public Double calculate(OrderInfoDTO order, CouponDTO coupon) {
        return 0.0;
    }
}
