package com.zpl.eshop.order.dao;

import com.zpl.eshop.order.domain.OrderItemDO;

import java.util.List;

/**
 * 订单条目模块 DAO 组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 17:14
 **/
public interface OrderItemDAO {

    /**
     * 新增订单条目
     *
     * @param orderItem 订单条目
     */
    Long save(OrderItemDO orderItem);

    /**
     * 查询订单条目
     *
     * @param orderInfoId 订单id
     * @return 订单条目
     */
    List<OrderItemDO> listByOrderInfoId(Long orderInfoId);
}
