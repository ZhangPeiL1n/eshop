package com.zpl.eshop.order.state;

import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单状态管理器
 *
 * @author ZhangPeiL1n
 * @date 2022/9/12 14:51
 **/
@Component
public class OrderStateManagerImpl implements OrderStateManager {


    /**
     * 订单状态工厂
     */
    @Autowired
    private OrderStateFactory orderStateFactory;

    /**
     * 创建订单
     *
     * @param order 订单
     */
    @Override
    public void createOrder(OrderInfoDTO order) throws Exception {
        OrderState waitForPayState = orderStateFactory.getByOrderStatus(OrderStatus.WAIT_FOR_PAY);
        waitForPayState.doTransition(order);
    }

    /**
     * 能否取消订单
     *
     * @param order 订单
     * @return
     */
    @Override
    public Boolean canCancel(OrderInfoDTO order) {
        OrderState orderState = orderStateFactory.get(order);
        return orderState.canCancel(order);
    }

    /**
     * 取消订单
     *
     * @param order 订单
     */
    @Override
    public void cancelOrder(OrderInfoDTO order) throws Exception {
        OrderState canceledState = orderStateFactory.getByOrderStatus(OrderStatus.CANCELED);
        canceledState.doTransition(order);
    }

    /**
     * 能否支付订单
     *
     * @param order 订单
     * @return
     */
    @Override
    public Boolean canPay(OrderInfoDTO order) {
        OrderState orderState = orderStateFactory.get(order);
        return orderState.canPay(order);
    }

    /**
     * 支付订单
     *
     * @param order
     */
    @Override
    public void payOrder(OrderInfoDTO order) throws Exception {
        OrderState waitForDeliveryState = orderStateFactory.getByOrderStatus(OrderStatus.WAIT_FOR_DELIVERY);
        waitForDeliveryState.doTransition(order);
    }
}
