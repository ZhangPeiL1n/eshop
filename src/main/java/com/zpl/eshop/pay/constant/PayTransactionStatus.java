package com.zpl.eshop.pay.constant;

/**
 * 支付交易状态
 *
 * @author ZhangPeiL1n
 * @date 2022/10/25 22:05
 **/
public class PayTransactionStatus {

    /**
     * 未付款
     */
    public static final Integer UNPAID = 1;

    /**
     * 支付成功
     */
    public static final Integer SUCCESS = 2;

    /**
     * 支付失败
     */
    public static final Integer FAILURE = 3;

    /**
     * 支付交易关闭
     */
    public static final Integer CLOSED = 4;

    private PayTransactionStatus() {

    }
}
