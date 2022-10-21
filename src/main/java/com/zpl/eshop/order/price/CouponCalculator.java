package com.zpl.eshop.order.price;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.promotion.domain.CouponDTO;

/**
 * 优惠券抵扣金额计算接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 14:20
 **/
public interface CouponCalculator {

    /**
     * 计算优惠券对当前订单的金额
     *
     * @param order  订单
     * @param coupon 优惠券
     * @return 抵扣金额
     */
    Double calculate(OrderInfoDTO order, CouponDTO coupon) throws Exception;
}
