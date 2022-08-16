package com.zpl.eshop.order.dao.impl;

import com.zpl.eshop.order.dao.OrderItemDAO;
import com.zpl.eshop.order.domain.OrderItemDO;
import com.zpl.eshop.order.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 订单条目模块 DAO 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 17:17
 **/
@Repository
public class OrderItemDAOImpl implements OrderItemDAO {

    /**
     * 订单条目管理模块 mapper 组件
     */
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 新增订单条目
     *
     * @param item 订单条目
     */
    @Override
    public Long save(OrderItemDO item) {
        orderItemMapper.save(item);
        return item.getId();
    }
}
