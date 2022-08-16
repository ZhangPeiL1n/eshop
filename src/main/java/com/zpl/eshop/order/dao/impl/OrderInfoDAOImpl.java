package com.zpl.eshop.order.dao.impl;

import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
