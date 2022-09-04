package com.zpl.eshop.order.dao;

import com.zpl.eshop.order.domain.OrderOperateLogDO;

import java.util.List;

/**
 * 订单操作日志DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/4 15:06
 **/
public interface OrderOperateLogDAO {

    /**
     * 新增订单操作日志
     *
     * @param log 订单操作日志
     */
    void save(OrderOperateLogDO log);

    /**
     * 查询订单操作日志
     *
     * @param orderInfoId 订单id
     * @return 订单操作日志
     */
    List<OrderOperateLogDO> listByOrderInfoId(Long orderInfoId);
}
