package com.zpl.eshop.schedule.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.schedule.dao.ScheduleOrderPickingItemDAO;
import com.zpl.eshop.schedule.domain.ScheduleOrderPickingItemDO;
import com.zpl.eshop.schedule.domain.ScheduleOrderPickingItemDTO;
import com.zpl.eshop.schedule.mapper.ScheduleOrderPickingItemMapper;
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
     * 批量插入拣货条目
     *
     * @param orderItem    订单条目
     * @param pickingItems 拣货条目
     */
    @Override
    public void batchSave(OrderItemDTO orderItem,
                          List<ScheduleOrderPickingItemDTO> pickingItems) throws Exception {
        for (ScheduleOrderPickingItemDTO pickingItem : pickingItems) {
            pickingItem.setId(null);
            pickingItem.setOrderInfoId(orderItem.getOrderInfoId());
            pickingItem.setOrderItemId(orderItem.getId());
            pickingItem.setGmtCreate(dateProvider.getCurrentTime());
            pickingItem.setGmtModified(dateProvider.getCurrentTime());

            pickingItemMapper.save(pickingItem.clone(ScheduleOrderPickingItemDO.class));
        }
    }

    /**
     * 根据订单id和订单条目id查询拣货条目
     *
     * @param orderInfoId 订单id
     * @param orderItemId 订单条目id
     * @return
     */
    @Override
    public List<ScheduleOrderPickingItemDO> listByOrderItemId(Long orderInfoId, Long orderItemId) {
        return pickingItemMapper.listByOrderItemId(orderInfoId, orderItemId);
    }

    /**
     * 根据订单条目id删除拣货条目
     *
     * @param orderInfoId 订单id
     * @param orderItemId 订单条目id
     */
    @Override
    public void removeByOrderItemId(Long orderInfoId, Long orderItemId) {
        pickingItemMapper.removeByOrderItemId(orderInfoId, orderItemId);
    }
}
