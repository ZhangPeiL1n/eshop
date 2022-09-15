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
    Boolean canCancel(OrderInfoDTO order);

    /**
     * 取消订单
     *
     * @param order 订单
     */
    void cancelOrder(OrderInfoDTO order) throws Exception;
}
