package com.zpl.eshop.schedule.service;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;

import java.util.List;

/**
 * 销售出库单builder接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/18 16:47
 **/
public interface SaleDeliveryOrderBuilder {

    /**
     * 设置订单相关数据
     *
     * @param order 订单
     * @return builder
     */
    SaleDeliveryOrderBuilder setOrderRelatedData(OrderInfoDTO order) throws Exception;

    /**
     * 创建销售出库单条目
     *
     * @param orderItems 订单条目
     * @return builder
     */
    SaleDeliveryOrderBuilder createSaleDeliveryOrderItems(List<OrderItemDTO> orderItems) throws Exception;

    /**
     * 创建发货单
     *
     * @param order 订单
     * @return builder
     */
    SaleDeliveryOrderBuilder createSendOutOrder(OrderInfoDTO order) throws Exception;

    /**
     * 创建物流单
     *
     * @param order 订单
     * @return builder
     */
    SaleDeliveryOrderBuilder createLogisticOrder(OrderInfoDTO order) throws Exception;

    /**
     * 初始化销售出库单的状态
     *
     * @return builder
     */
    SaleDeliveryOrderBuilder initStatus() throws Exception;

    /**
     * 初始化时间相关的字段
     *
     * @return builder
     */
    SaleDeliveryOrderBuilder initTimes() throws Exception;

    /**
     * 创建最终构造好的销售出库单
     *
     * @return 销售出库单
     */
    SaleDeliveryOrderDTO create() throws Exception;
}