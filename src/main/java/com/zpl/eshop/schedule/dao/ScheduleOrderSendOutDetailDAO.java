package com.zpl.eshop.schedule.dao;

import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.schedule.domain.ScheduleOrderSendOutDetailDO;
import com.zpl.eshop.schedule.domain.ScheduleOrderSendOutDetailDTO;

import java.util.List;

/**
 * 调度中心发货明细管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 18:30
 **/
public interface ScheduleOrderSendOutDetailDAO {

    /**
     * 批量新增发货明细
     *
     * @param orderItem      订单条目
     * @param sendOutDetails 销售出库单发货明细
     */
    void batchSave(OrderItemDTO orderItem,
                   List<ScheduleOrderSendOutDetailDTO> sendOutDetails) throws Exception;

    /**
     * 根据订单id和订单条目id查询发货明细
     *
     * @param orderInfoId 订单id
     * @param orderItemId 订单条目id
     * @return
     */
    List<ScheduleOrderSendOutDetailDO> listByOrderItemId(Long orderInfoId, Long orderItemId);

    /**
     * 根据订单条目id删除发货明细
     *
     * @param orderInfoId 订单id
     * @param orderItemId 订单条目id
     */
    void removeByOrderItemId(Long orderInfoId, Long orderItemId);
}
