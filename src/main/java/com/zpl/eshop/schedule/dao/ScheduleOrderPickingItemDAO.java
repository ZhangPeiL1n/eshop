package com.zpl.eshop.schedule.dao;

import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderPickingItemDTO;

import java.util.List;

/**
 * 调度中心拣货条目管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 18:24
 **/
public interface ScheduleOrderPickingItemDAO {

    /**
     * 新增拣货条目
     *
     * @param saleDeliveryOrderPickingItems 销售出库单拣货条目
     * @param orderItem                     订单条目
     */
    void batchSave(List<SaleDeliveryOrderPickingItemDTO> saleDeliveryOrderPickingItems,
                   OrderItemDTO orderItem) throws Exception;
}
