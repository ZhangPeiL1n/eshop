package com.zpl.eshop.commodity.state;

/**
 * 审核结果
 *
 * @author ZhangPeiL1n
 * @date 2022/9/7 16:28
 **/
public class ApproveResult {

    /**
     * 审核通过
     */
    public static final Integer APPROVE_PASS = 1;

    /**
     * 审核未通过
     */
    public static final Integer APPROVE_REJECT = 0;

    private ApproveResult() {
    }
}
