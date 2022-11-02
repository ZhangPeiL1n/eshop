package com.zpl.eshop.schedule.domain;

import com.zpl.eshop.order.domain.OrderItemDTO;
import lombok.Data;

import java.util.List;

/**
 * 调度销售出库结果
 *
 * @author ZhangPeiL1n
 * @date 2022/10/30 17:53
 **/
@Data
public class SaleDeliveryScheduleResult {
    /**
     * 订单条目
     */
    private OrderItemDTO orderItem;
    /**
     * 拣货条目
     */
    private List<ScheduleOrderPickingItemDTO> pickingItems;
    /**
     * 发货明细
     */
    private List<ScheduleOrderSendOutDetailDTO> sendOutDetails;
}
