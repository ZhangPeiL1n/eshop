package com.zpl.eshop.pay.domain;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.pay.constant.PayTransactionStatus;

/**
 * 支付交易流水builder
 *
 * @author ZhangPeiL1n
 * @date 2022/10/25 22:04
 **/
public class PayTransactionBuilder {
    /**
     * 支付交易流水
     */
    private final PayTransactionDTO payTransaction = new PayTransactionDTO();

    private PayTransactionBuilder() {

    }

    /**
     * 获取builder实例
     *
     * @return
     */
    public static PayTransactionBuilder get() {
        return new PayTransactionBuilder();
    }

    /**
     * 构建订单相关的数据
     *
     * @param order 订单
     * @return builder
     * @throws Exception
     */
    public PayTransactionBuilder buildOrderRelatedData(OrderInfoDTO order) throws Exception {
        payTransaction.setOrderInfoId(order.getId());
        payTransaction.setOrderNo(order.getOrderNo());
        payTransaction.setPayableAmount(order.getPayableAmount());
        payTransaction.setUserAccountId(order.getUserAccountId());
        payTransaction.setTransactionChannel(order.getPayType());
        return this;
    }

    /**
     * 初始化状态
     *
     * @return builder
     * @throws Exception
     */
    public PayTransactionBuilder initStatus() throws Exception {
        payTransaction.setStatus(PayTransactionStatus.UNPAID);
        return this;
    }

    /**
     * 创建支付交易流水
     *
     * @return 支付交易流水
     * @throws Exception
     */
    public PayTransactionDTO create() throws Exception {
        return payTransaction;
    }
}
