package com.zpl.eshop.schedule.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.schedule.dao.ScheduleOrderPickingItemDAO;
import com.zpl.eshop.schedule.domain.ScheduleOrderPickingItemDO;
import com.zpl.eshop.schedule.mapper.ScheduleOrderPickingItemMapper;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderPickingItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/3 18:25
 **/
@Repository
public class ScheduleOrderPickingItemDAOImpl implements ScheduleOrderPickingItemDAO {

    /**
     * 调度中心拣货条目管理Mapper组件
     */
    @Autowired
    private ScheduleOrderPickingItemMapper pickingItemMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增拣货条目
     *
     * @param saleDeliveryOrderPickingItems 拣货条目
     * @param orderItem                     订单条目
     */
    @Override
    public void batchSave(List<SaleDeliveryOrderPickingItemDTO> saleDeliveryOrderPickingItems,
                          OrderItemDTO orderItem) throws Exception {
        for (SaleDeliveryOrderPickingItemDTO saleDeliveryOrderPickingItem : saleDeliveryOrderPickingItems) {
            ScheduleOrderPickingItemDO pickingItem = saleDeliveryOrderPickingItem.clone(
                    ScheduleOrderPickingItemDO.class);

            pickingItem.setId(null);
            pickingItem.setOrderInfoId(orderItem.getOrderInfoId());
            pickingItem.setOrderItemId(orderItem.getId());
            pickingItem.setGmtCreate(dateProvider.getCurrentTime());
            pickingItem.setGmtModified(dateProvider.getCurrentTime());

            pickingItemMapper.save(pickingItem);
        }
    }
}
