package com.zpl.eshop.schedule.service.impl;

import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderItemDTO;

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
     * @return 销售出库单条目
     */
    SaleDeliveryOrderItemDTO schedule(OrderItemDTO orderItem);
}
