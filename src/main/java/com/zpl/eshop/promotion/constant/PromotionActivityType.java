package com.zpl.eshop.promotion.constant;

/**
 * 促销活动类型
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 19:04
 **/
public class PromotionActivityType {

    /**
     * 满减促销
     */
    public static final Integer REACH_DISCOUNT = 1;

    /**
     * 多买优惠
     */
    public static final Integer MULTI_DISCOUNT = 2;

    /**
     * 单品促销
     */
    public static final Integer DIRECT_DISCOUNT = 3;

    /**
     * 满赠促销
     */
    public static final Integer REACH_GIFT = 4;

    /**
     * 赠品促销
     */
    public static final Integer DIRECT_GIFT = 5;

    private PromotionActivityType() {
    }
}
