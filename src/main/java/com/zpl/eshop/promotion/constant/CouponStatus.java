package com.zpl.eshop.promotion.constant;

/**
 * 优惠券状态
 *
 * @author ZhangPeiL1n
 * @date 2022/9/18 19:32
 **/
public class CouponStatus {

    /**
     * 未开始
     */
    public static final Integer UNSTARTED = 1;

    /**
     * 发放中
     */
    public static final Integer GIVING_OUT = 2;

    /**
     * 已发完
     */
    public static final Integer GIVEN_OUT = 3;

    /**
     * 已过期
     */
    public static final Integer EXPIRED = 4;

    private CouponStatus() {
    }
}
