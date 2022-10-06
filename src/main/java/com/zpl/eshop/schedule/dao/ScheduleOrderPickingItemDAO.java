package com.zpl.eshop.schedule.dao;

import com.zpl.eshop.schedule.domain.ScheduleOrderPickingItemDO;

import java.util.List;

/**
 * 调度中心拣货条目管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 18:24
 **/
public interface ScheduleOrderPickingItemDAO {

    /**
     * 批量插入拣货条目
     *
     * @param orderItem    订单条目
     * @param pickingItems 拣货条目
     */
    void batchSave(Long orderInfoId, Long orderItemId,
                   List<ScheduleOrderPickingItemDO> pickingItems) throws Exception;

    /**
     * 根据订单id和订单条目id查询拣货条目
     *
     * @param orderInfoId 订单id
     * @param orderItemId 订单条目id
     * @return
     */
    List<ScheduleOrderPickingItemDO> listByOrderItemId(Long orderInfoId, Long orderItemId);

    /**
     * 根据订单条目id删除拣货条目
     *
     * @param orderInfoId 订单id
     * @param orderItemId 订单条目id
     */
    void removeByOrderItemId(Long orderInfoId, Long orderItemId);
}
