package com.zpl.eshop.order.state;

import com.zpl.eshop.order.domain.OrderInfoDTO;

/**
 * 订单状态管理器接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/12 14:51
 **/
public interface OrderStateManager {

    /**
     * 创建订单
     *
     * @param order 订单
     */
    void createOrder(OrderInfoDTO order) throws Exception;

    /**
     * 能否取消订单
     *
     * @param order 订单
     * @return
     */
    Boolean canCancel(OrderInfoDTO order) throws Exception;

    /**
     * 取消订单
     *
     * @param order 订单
     */
    void cancelOrder(OrderInfoDTO order) throws Exception;

    /**
     * 能否支付订单
     *
     * @param order 订单
     * @return
     */
    Boolean canPay(OrderInfoDTO order) throws Exception;

    /**
     * 支付订单
     *
     * @param order 订单
     */
    void payOrder(OrderInfoDTO order) throws Exception;

    /**
     * 完成商品发货
     *
     * @param order 订单
     * @throws Exception
     */
    void finishDelivery(OrderInfoDTO order) throws Exception;
}
