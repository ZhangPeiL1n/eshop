package com.zpl.eshop.pay.api;

/**
 * 支付接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/25 22:01
 **/
public interface PayApi {

    /**
     * 预创建支付订单：获取用于支付的二维码
     *
     * @param transactionChannel 交易渠道
     * @param orderNo            订单编号
     * @param totalAmount        订单总金额
     * @return 支付二维码
     * @throws Exception
     */
    String getQrCode(Integer transactionChannel,
                     String orderNo, Double totalAmount) throws Exception;

    /**
     * 查询支付状态
     *
     * @param transactionChannel 交易渠道
     * @param orderNo            订单编号
     * @return 支付状态
     * @throws Exception
     */
    QueryPayStatusResponse queryPayStatus(Integer transactionChannel,
                                          String orderNo) throws Exception;
}
