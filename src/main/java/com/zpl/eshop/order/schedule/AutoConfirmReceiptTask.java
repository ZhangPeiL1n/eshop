package com.zpl.eshop.order.schedule;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.logistics.service.LogisticsService;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.state.OrderStateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 自动确认收货任务
 *
 * @author ZhangPeiL1n
 * @date 2022/10/11 21:20
 **/
@Component
public class AutoConfirmReceiptTask {

    private static final Logger logger = LoggerFactory.getLogger(AutoConfirmReceiptTask.class);

    /**
     * 订单管理DAO组件
     */
    @Autowired
    private OrderInfoDAO orderInfoDAO;

    /**
     * 物流中心接口
     */
    @Autowired
    private LogisticsService logisticsService;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 订单状态管理器
     */
    @Autowired
    @Qualifier("loggedOrderStateManager")
    private OrderStateManager orderStateManager;

    @Scheduled(fixedRate = 60 * 1000)
    public void execute() {
        try {
            List<OrderInfoDO> unreceivedOrders = orderInfoDAO.listUnreceived();

            for (OrderInfoDO unreceivedOrder : unreceivedOrders) {
                Date signedTime = logisticsService.getSignedTime(unreceivedOrder.getId(),
                        unreceivedOrder.getOrderNo());
                if (signedTime == null) {
                    continue;
                }
                if (dateProvider.getCurrentTime().getTime() - signedTime.getTime() >
                        7 * 24 * 60 * 60 * 1000) {
                    orderStateManager.confirmReceipt(unreceivedOrder.clone(OrderInfoDTO.class));
                    orderInfoDAO.updateConfirmReceiptTime(unreceivedOrder.getId(), dateProvider.getCurrentTime());
                }
            }
        } catch (Exception e) {
            logger.error("error", e);
        }
    }

}
