package com.zpl.eshop.order.dao.impl;

import com.zpl.eshop.order.dao.OrderOperateLogDAO;
import com.zpl.eshop.order.domain.OrderOperateLogDO;
import com.zpl.eshop.order.mapper.OrderOperateLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单操作日志DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/9/4 15:08
 **/
@Repository
public class OrderOperateLogDAOImpl implements OrderOperateLogDAO {

    /**
     * 订单操作日志Mapper组件
     */
    @Autowired
    private OrderOperateLogMapper orderOperateLogMapper;

    /**
     * 新增订单操作日志
     *
     * @param log 订单操作日志
     */
    @Override
    public void save(OrderOperateLogDO log) {
        orderOperateLogMapper.save(log);
    }

    /**
     * 查询订单操作日志
     *
     * @param orderInfoId 订单id
     * @return 订单操作日志
     */
    @Override
    public List<OrderOperateLogDO> listByOrderInfoId(Long orderInfoId) {
        return orderOperateLogMapper.listByOrderInfoId(orderInfoId);
    }
}
