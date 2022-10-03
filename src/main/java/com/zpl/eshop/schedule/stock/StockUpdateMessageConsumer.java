package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.inventory.async.StockUpdateMessage;
import com.zpl.eshop.inventory.async.StockUpdateQueue;
import com.zpl.eshop.inventory.constant.GoodsStockUpdateOperation;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.schedule.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 库存更新消息消费者
 *
 * @author ZhangPeiL1n
 * @date 2022/10/2 13:58
 **/
@Component
public class StockUpdateMessageConsumer extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(StockUpdateMessageConsumer.class);

    /**
     * 库存更新消息队列
     */
    @Autowired
    private StockUpdateQueue stockUpdateQueue;

    /**
     * 调度中心接口
     */
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 消费库存更新消息
     */
    @Override
    public void run() {
        while (true) {
            try {
                StockUpdateMessage message = stockUpdateQueue.take();
                if (!isOrderRelatedMessage(message)) {
                    continue;
                }
                OrderInfoDTO order = getOrderFromMessage(message);
                processMessage(message, order);
            } catch (Exception e) {
                logger.error("error", e);
            }
        }
    }

    /**
     * 是否是订单相关的操作
     *
     * @param message 消息
     * @return 是否是订单相关的操作
     * @throws Exception
     */
    private Boolean isOrderRelatedMessage(StockUpdateMessage message) throws Exception {
        return GoodsStockUpdateOperation.SUBMIT_ORDER.equals(message.getOperation())
                || GoodsStockUpdateOperation.CANCEL_ORDER.equals(message.getOperation())
                || GoodsStockUpdateOperation.PAY_ORDER.equals(message.getOperation());
    }

    /**
     * 从消息中获取订单
     *
     * @param message 消息
     * @return 订单
     * @throws Exception
     */
    private OrderInfoDTO getOrderFromMessage(StockUpdateMessage message) throws Exception {
        return (OrderInfoDTO) message.getParameter();
    }

    /**
     * 处理消息
     *
     * @param order 订单
     * @return 处理结果
     * @throws Exception
     */
    private Boolean processMessage(StockUpdateMessage message,
                                   OrderInfoDTO order) throws Exception {
        if (GoodsStockUpdateOperation.SUBMIT_ORDER.equals(message.getOperation())) {
            return scheduleService.informSubmitOrderEvent(order);
        } else if (GoodsStockUpdateOperation.CANCEL_ORDER.equals(message.getOperation())) {
            return scheduleService.informCancelOrderEvent(order);
        } else if (GoodsStockUpdateOperation.PAY_ORDER.equals(message.getOperation())) {
            return scheduleService.informPayOrderEvent(order);
        }
        return false;
    }
}
