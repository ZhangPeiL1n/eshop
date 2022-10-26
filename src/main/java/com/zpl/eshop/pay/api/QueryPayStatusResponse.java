package com.zpl.eshop.pay.api;

import lombok.Data;

import java.util.Date;

/**
 * 查询支付状态的响应结果
 *
 * @author ZhangPeiL1n
 * @date 2022/10/25 22:22
 **/
@Data
public class QueryPayStatusResponse {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户支付账号
     */
    private String userPayAccount;

    /**
     * 第三方支付交易流水号
     */
    private String transactionNumber;

    /**
     * 第三方支付完成支付的时间
     */
    private Date finishPayTime;

    /**
     * 第三方支付响应状态码
     */
    private String responseCode;

    /**
     * 支付交易状态
     */
    private Integer payTransactionStatus;
}
