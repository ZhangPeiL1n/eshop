package com.zpl.eshop.schedule.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.schedule.dao.ScheduleOrderSendOutDetailDAO;
import com.zpl.eshop.schedule.domain.ScheduleOrderSendOutDetailDO;
import com.zpl.eshop.schedule.mapper.ScheduleOrderSendOutDetailMapper;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderSendOutDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 调度中心发货明细管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 18:31
 **/
@Repository
public class ScheduleOrderSendOutDetailDAOImpl implements ScheduleOrderSendOutDetailDAO {

    /**
     * 调度中心发货明细管理Mapper
     */
    @Autowired
    private ScheduleOrderSendOutDetailMapper sendOutDetailMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增发货明细
     *
     * @param saleDeliveryOrderSendOutDetails 销售出库单发货明细
     * @param orderItem                       订单条目
     */
    @Override
    public void batchSave(List<SaleDeliveryOrderSendOutDetailDTO> saleDeliveryOrderSendOutDetails,
                          OrderItemDTO orderItem) throws Exception {
        for (SaleDeliveryOrderSendOutDetailDTO saleDeliveryOrderSendOutDetail : saleDeliveryOrderSendOutDetails) {
            ScheduleOrderSendOutDetailDO sendOutDetail =
                    saleDeliveryOrderSendOutDetail.clone(ScheduleOrderSendOutDetailDO.class);

            sendOutDetail.setId(null);
            sendOutDetail.setOrderInfoId(orderItem.getOrderInfoId());
            sendOutDetail.setOrderItemId(orderItem.getId());
            sendOutDetail.setGmtCreate(dateProvider.getCurrentTime());
            sendOutDetail.setGmtModified(dateProvider.getCurrentTime());

            sendOutDetailMapper.save(sendOutDetail);
        }
    }
}
