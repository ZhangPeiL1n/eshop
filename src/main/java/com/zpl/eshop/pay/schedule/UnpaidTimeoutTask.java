package com.zpl.eshop.pay.schedule;

import com.zpl.eshop.pay.constant.PayTransactionStatus;
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
 * 未支付订单超时检查任务
 *
 * @author ZhangPeiL1n
 * @date 2022/10/26 21:42
 **/
@Component
public class UnpaidTimeoutTask {
    private static final Logger logger = LoggerFactory.getLogger(UnpaidTimeoutTask.class);

    /**
     * 支付交易流水管理DAO组件
     */
    @Autowired
    private PayTransactionDAO payTransactionDAO;

    @Scheduled(fixedRate = 10 * 1000)
    public void execute() {
        try {
            List<PayTransactionDO> payTransactions = listUnpayedTransactions();

            for (PayTransactionDO payTransaction : payTransactions) {
                if (System.currentTimeMillis() - payTransaction.getGmtCreate().getTime() > 30 * 60 * 1000) {
                    payTransaction.setStatus(PayTransactionStatus.CLOSED);
                    payTransactionDAO.update(payTransaction);
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
    private List<PayTransactionDO> listUnpayedTransactions() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("status", PayTransactionStatus.UNPAID);
        return payTransactionDAO.listByCondition(parameters);
    }

}
