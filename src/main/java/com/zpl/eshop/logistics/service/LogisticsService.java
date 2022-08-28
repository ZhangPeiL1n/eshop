package com.zpl.eshop.logistics.service;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.LogisticOrderDTO;

/**
 * 物流中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:20
 **/
public interface LogisticsService {

    /**
     * 计算商品 sku 的运费
     *
     * @param order     订单
     * @param orderItem 订单条目
     * @return 商品 sku 的运费
     */
    Double calculateFreight(OrderInfoDTO order, OrderItemDTO orderItem);

    /**
     * 申请物流单
     *
     * @param order 订单
     * @return 物流单
     */
    LogisticOrderDTO applyLogisticOrder(OrderInfoDTO order);
}
