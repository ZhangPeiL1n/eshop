package com.zpl.eshop.order.domain;

import com.zpl.eshop.promotion.domain.CouponVO;
import lombok.Data;

/**
 * 计算优惠券抵扣金额VO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/28 19:41
 **/
@Data
public class CalculateCouponDiscountPriceVO {

    /**
     * 订单
     */
    private OrderInfoVO order;

    /**
     * 优惠券
     */
    private CouponVO coupon;
}
