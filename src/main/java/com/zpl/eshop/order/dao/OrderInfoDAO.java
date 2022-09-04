package com.zpl.eshop.order.dao;

import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.domain.OrderInfoQuery;

import java.util.List;

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

    /**
     * 分页查询订单
     *
     * @param query 查询条件
     * @return 订单
     */
    List<OrderInfoDO> listByPage(OrderInfoQuery query);

    /**
     * 查询订单详情
     *
     * @return 订单
     */
    OrderInfoDO getById(Long id);
}
