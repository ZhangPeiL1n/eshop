package com.zpl.eshop.pay.api;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.pay.constant.PayTransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 支付api
 *
 * @author ZhangPeiL1n
 * @date 2022/10/25 22:01
 **/
@Component
public class PayApiImpl implements PayApi {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 预创建支付订单：获取用于支付的二维码
     *
     * @param transactionChannel 交易渠道
     * @param orderNo            订单编号
     * @param totalAmount        订单总金额
     * @return 支付二维码
     * @throws Exception
     */
    @Override
    public String getQrCode(Integer transactionChannel,
                            String orderNo, Double totalAmount) throws Exception {
        // 根据交易渠道自己选择调用了远程的支付宝/微信支付的接口，同时指定了二维码的有效期是30分钟
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 查询支付状态
     *
     * @param transactionChannel 交易渠道
     * @param orderNo            订单编号
     * @return 支付状态
     * @throws Exception
     */
    @Override
    public QueryPayStatusResponse queryPayStatus(Integer transactionChannel,
                                                 String orderNo) throws Exception {
        // 根据交易渠道选择调用支付宝或者微信支付的远程接口，根据订单号去查询支付状态
        // 接口里需要判断，当前支付的一个状态

        QueryPayStatusResponse response = new QueryPayStatusResponse();
        response.setUserPayAccount(UUID.randomUUID().toString().replace("-", ""));
        response.setTransactionNumber(UUID.randomUUID().toString().replace("-", ""));
        response.setResponseCode("SUCCESS");
        response.setFinishPayTime(dateProvider.getCurrentTime());
        response.setPayTransactionStatus(PayTransactionStatus.SUCCESS);

        return response;
    }
}
