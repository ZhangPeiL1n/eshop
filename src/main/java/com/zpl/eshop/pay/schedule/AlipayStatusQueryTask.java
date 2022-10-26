package com.zpl.eshop.pay.schedule;

import com.zpl.eshop.order.service.OrderService;
import com.zpl.eshop.pay.api.PayApi;
import com.zpl.eshop.pay.api.QueryPayStatusResponse;
import com.zpl.eshop.pay.constant.PayTransactionStatus;
import com.zpl.eshop.pay.constant.PayType;
import com.zpl.eshop.pay.dao.PayTransactionDAO;
import com.zpl.eshop.pay.domain.PayTransactionDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付宝支付状态查询的后台任务
 *
 * @author ZhangPeiL1n
 * @date 2022/10/25 22:29
 **/
@Component
public class AlipayStatusQueryTask {

    private static final Logger logger = LoggerFactory.getLogger(AlipayStatusQueryTask.class);

    /**
     * 支付交易流水管理DAO组件
     */
    @Autowired
    private PayTransactionDAO payTransactionDAO;

    /**
     * 支付接口
     */
    @Autowired
    private PayApi payApi;

    /**
     * 订单中心接口
     */
    @Autowired
    private OrderService orderService;

    /**
     * 执行任务
     */
    @Scheduled(fixedRate = 10 * 1000)
    public void execute() {
        try {
            List<PayTransactionDO> payTransactions = listUnpaidAlipayTransactions();

            for (PayTransactionDO payTransaction : payTransactions) {
                // 此处会调用支付宝代理接口，去查询支付交易的状态
                QueryPayStatusResponse response = payApi.queryPayStatus(
                        payTransaction.getTransactionChannel(), payTransaction.getOrderNo());

                if (!PayTransactionStatus.UNPAID.equals(response.getPayTransactionStatus())) {
                    payTransaction.setUserPayAccount(response.getUserPayAccount());
                    payTransaction.setTransactionNumber(response.getTransactionNumber());
                    payTransaction.setFinishPayTime(response.getFinishPayTime());
                    payTransaction.setResponseCode(response.getResponseCode());
                    payTransaction.setStatus(response.getPayTransactionStatus());
                    payTransactionDAO.update(payTransaction);

                    if (PayTransactionStatus.SUCCESS.equals(response.getPayTransactionStatus())) {
                        orderService.informPaySucceed(payTransaction.getOrderInfoId());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("error", e);
        }
    }

    /**
     * 查询未支付的支付宝交易流水
     *
     * @return 交易流水
     */
    private List<PayTransactionDO> listUnpaidAlipayTransactions() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("transactionChannel", PayType.ALIPAY);
        parameters.put("status", PayTransactionStatus.UNPAID);

        return payTransactionDAO.listByCondition(parameters);
    }
}
