package com.zpl.eshop.pay.api;

import com.zpl.eshop.order.service.OrderService;
import com.zpl.eshop.pay.constant.PayTransactionStatus;
import com.zpl.eshop.pay.domain.PayTransactionDTO;
import com.zpl.eshop.pay.service.PayTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/26 21:37
 **/
@RestController
@RequestMapping("/pay/api")
public class PayApiController {

    private static final Logger logger = LoggerFactory.getLogger(PayApiController.class);

    /**
     * 支付交易流水管理service组件
     */
    @Autowired
    private PayTransactionService payTransactionService;
    /**
     * 订单中心接口
     */
    @Autowired
    private OrderService orderService;

    /**
     * 接收微信支付的状态
     *
     * @param response 支付响应
     * @return 处理结果
     */
    @PostMapping("/weixin/status")
    public Boolean receiveWeixinPayStatus(QueryPayStatusResponse response) {
        try {
            PayTransactionDTO payTransaction = payTransactionService
                    .getByOrderNo(response.getOrderNo());

            payTransaction.setUserPayAccount(response.getUserPayAccount());
            payTransaction.setTransactionNumber(response.getTransactionNumber());
            payTransaction.setFinishPayTime(response.getFinishPayTime());
            payTransaction.setResponseCode(response.getResponseCode());
            payTransaction.setStatus(response.getPayTransactionStatus());

            payTransactionService.update(payTransaction);

            if (PayTransactionStatus.SUCCESS.equals(response.getPayTransactionStatus())) {
                orderService.informPayOrderSucceed(payTransaction.getOrderInfoId());
            }

            return true;
        } catch (Exception e) {
            logger.error("Error", e);
            return false;
        }
    }
}
