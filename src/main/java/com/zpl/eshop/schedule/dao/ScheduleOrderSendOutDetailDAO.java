package com.zpl.eshop.schedule.dao;

import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderSendOutDetailDTO;

import java.util.List;

/**
 * 调度中心发货明细管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 18:30
 **/
public interface ScheduleOrderSendOutDetailDAO {

    /**
     * 新增发货明细
     *
     * @param saleDeliveryOrderSendOutDetails 销售出库单发货明细
     * @param orderItem                       订单条目
     */
    void batchSave(List<SaleDeliveryOrderSendOutDetailDTO> saleDeliveryOrderSendOutDetails,
                   OrderItemDTO orderItem) throws Exception;
}
