package com.zpl.eshop.schedule.service;

import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.schedule.domain.SaleDeliveryScheduleResult;

/**
 * 销售出库调度器
 *
 * @author ZhangPeiL1n
 * @date 2022/8/17 20:38
 **/
public interface SaleDeliveryScheduler {

    /**
     * 调度销售出库
     *
     * @param orderItem 订单条目
     * @return 调度结果
     */
    SaleDeliveryScheduleResult schedule(OrderItemDTO orderItem) throws Exception;

    /**
     * 获取订单条目的调度结果
     *
     * @param orderItem 订单条目
     * @return 调度结果
     * @throws Exception
     */
    SaleDeliveryScheduleResult getScheduleResult(OrderItemDTO orderItem) throws Exception;

}
