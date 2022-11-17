package com.zpl.eshop.logistics.service;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.LogisticOrderDTO;

import java.util.Date;

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
     * @throws Exception
     */
    Double calculateFreight(OrderInfoDTO order, OrderItemDTO orderItem) throws Exception;

    /**
     * 申请物流单
     *
     * @param order 订单
     * @return 物流单
     * @throws Exception
     */
    LogisticOrderDTO applyLogisticOrder(OrderInfoDTO order) throws Exception;

    /**
     * 获取订单签收时间
     *
     * @param orderId 订单id
     * @param orderNo 订单编号
     * @return 签收时间
     * @throws Exception
     */
    Date getSignedTime(Long orderId, String orderNo) throws Exception;
}
