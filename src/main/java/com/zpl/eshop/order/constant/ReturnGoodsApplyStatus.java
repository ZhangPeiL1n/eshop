package com.zpl.eshop.order.constant;

/**
 * 退货申请状态
 *
 * @author ZhangPeiL1n
 * @date 2022/10/15 21:34
 **/
public class ReturnGoodsApplyStatus {

    /**
     * 待审核
     */
    public static final Integer WAIT_FOR_APPROVE = 1;

    /**
     * 审核通过
     */
    public static final Integer PASSED = 2;

    /**
     * 审核通过
     */
    public static final Integer REJECTED = 3;

    private ReturnGoodsApplyStatus() {

    }
}
