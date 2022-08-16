package com.zpl.eshop.order.dao;

import com.zpl.eshop.order.domain.OrderInfoDO;

/**
 * 订单管理模块 DAO 组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 17:14
 **/
public interface OrderInfoDAO {

    /**
     * 新建订单
     *
     * @param order 订单
     */
    Long save(OrderInfoDO order);
}
