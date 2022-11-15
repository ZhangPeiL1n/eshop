package com.zpl.eshop.schedule.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.ScheduleOrderSendOutDetailDAO;
import com.zpl.eshop.schedule.domain.ScheduleOrderSendOutDetailDO;
import com.zpl.eshop.schedule.mapper.ScheduleOrderSendOutDetailMapper;
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
     * 批量新增发货明细
     *
     * @param orderItem      订单条目
     * @param sendOutDetails 销售出库单发货明细
     * @throws Exception
     */
    @Override
    public void batchSave(Long orderInfoId, Long orderItemId,
                          List<ScheduleOrderSendOutDetailDO> sendOutDetails) throws Exception {
        for (ScheduleOrderSendOutDetailDO sendOutDetail : sendOutDetails) {
            sendOutDetail.setId(null);
            sendOutDetail.setOrderInfoId(orderInfoId);
            sendOutDetail.setOrderItemId(orderItemId);
            sendOutDetail.setGmtCreate(dateProvider.getCurrentTime());
            sendOutDetail.setGmtModified(dateProvider.getCurrentTime());

            sendOutDetailMapper.save(sendOutDetail);
        }
    }

    /**
     * 根据订单id和订单条目id查询发货明细
     *
     * @param orderInfoId 订单id
     * @param orderItemId 订单条目id
     * @return 发货明细
     * @throws Exception
     */
    @Override
    public List<ScheduleOrderSendOutDetailDO> listByOrderItemId(Long orderInfoId, Long orderItemId) {
        return sendOutDetailMapper.listByOrderItemId(orderInfoId, orderItemId);
    }

    /**
     * 根据订单条目id删除发货明细
     *
     * @param orderInfoId 订单id
     * @param orderItemId 订单条目id
     * @throws Exception
     */
    @Override
    public void removeByOrderItemId(Long orderInfoId, Long orderItemId) throws Exception {
        sendOutDetailMapper.removeByOrderItemId(orderInfoId, orderItemId);
    }
}
