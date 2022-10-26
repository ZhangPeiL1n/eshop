package com.zpl.eshop.pay.service;

import com.zpl.eshop.pay.domain.PayTransactionDTO;

/**
 * 支付交易流水管理service接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/25 21:52
 **/
public interface PayTransactionService {

    /**
     * 新增支付交易流水
     *
     * @param payTransaction 支付交易流水
     */
    void save(PayTransactionDTO payTransaction) throws Exception;

    /**
     * 更新支付交易流水
     *
     * @param payTransaction 支付交易流水
     * @throws Exception
     */
    void update(PayTransactionDTO payTransaction) throws Exception;

    /**
     * 根据订单编号查询支付交易流水
     *
     * @param orderNo 订单编号
     * @return 支付交易流水
     * @throws Exception
     */
    PayTransactionDTO getByOrderNo(String orderNo) throws Exception;
}
