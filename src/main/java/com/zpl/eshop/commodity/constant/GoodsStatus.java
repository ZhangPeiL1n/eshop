package com.zpl.eshop.commodity.constant;

/**
 * 商品状态
 *
 * @author ZhangPeiL1n
 * @date 2022/9/7 15:30
 **/
public class GoodsStatus {

    /**
     * 未知状态
     */
    public static final Integer UNKNOWN = 0;

    /**
     * 待审核
     */
    public static final Integer WAIT_FOR_APPROVE = 1;
    /**
     * 待上架
     */
    public static final Integer WAIT_FOR_PUT_ON_SHELVES = 2;
    /**
     * 审核不通过
     */
    public static final Integer APPROVE_REJECT = 3;
    /**
     * 已上架
     */
    public static final Integer PUTTED_ON_SHELVES = 4;

    private GoodsStatus() {
    }
}
