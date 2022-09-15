package com.zpl.eshop.order.dao.impl;

import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.domain.OrderInfoQuery;
import com.zpl.eshop.order.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单管理模块 DAO 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 17:15
 **/
@Repository
public class OrderInfoDAOImpl implements OrderInfoDAO {

    /**
     * 订单管理模块 mapper 组件
     */
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 新建订单
     *
     * @param order 订单
     */
    @Override
    public Long save(OrderInfoDO order) {
        orderInfoMapper.save(order);
        return order.getId();
    }

    /**
     * 分页查询订单
     *
     * @param query 查询条件
     * @return 订单
     */
    @Override
    public List<OrderInfoDO> listByPage(OrderInfoQuery query) {
        return orderInfoMapper.listByPage(query);
    }

    /**
     * 查询订单详情
     *
     * @return 订单
     */
    @Override
    public OrderInfoDO getById(Long id) {
        return orderInfoMapper.getById(id);
    }

    /**
     * 更新订单状态
     *
     * @param order 订单
     */
    @Override
    public void updateStatus(OrderInfoDO order) {
        orderInfoMapper.updateStatus(order);
    }

    /**
     * 查询所有未付款的订单
     *
     * @return 所有未付款的订单
     */
    @Override
    public List<OrderInfoDO> listAllUnpaid() {
        return orderInfoMapper.listAllUnpaid();
    }
}
