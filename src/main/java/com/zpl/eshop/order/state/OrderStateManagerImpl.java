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
    public Boolean canCancel(OrderInfoDTO order) throws Exception {
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
    public Boolean canPay(OrderInfoDTO order) throws Exception {
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

    /**
     * 完成商品发货
     *
     * @param order 订单
     * @throws Exception
     */
    @Override
    public void finishDelivery(OrderInfoDTO order) throws Exception {
        OrderState waitForReceiveOrderState = orderStateFactory.getByOrderStatus(OrderStatus.WAIT_FOR_RECEIVE);
        waitForReceiveOrderState.doTransition(order);
    }

    /**
     * 判断能否手动确认收货
     *
     * @param order 订单
     * @return 能否手动确认收货
     */
    @Override
    public Boolean canConfirmReceipt(OrderInfoDTO order) {
        OrderState orderState = orderStateFactory.get(order);
        return orderState.canConfirmReceipt(order);
    }

    /**
     * 手动确认收货
     *
     * @param order 订单
     */
    @Override
    public void confirmReceipt(OrderInfoDTO order) throws Exception {
        OrderState orderState = orderStateFactory.getByOrderStatus(OrderStatus.FINISHED);
        orderState.doTransition(order);
    }

    /**
     * 能否申请退货
     *
     * @param order 订单
     * @return 能否申请退货
     */
    @Override
    public Boolean canApplyReturnGoods(OrderInfoDTO order) {
        OrderState orderState = orderStateFactory.get(order);
        return orderState.canApplyReturnGoods(order);
    }

    /**
     * 申请退货
     *
     * @param order 订单
     */
    @Override
    public void applyReturnGoods(OrderInfoDTO order) throws Exception {
        OrderState orderState = orderStateFactory.getByOrderStatus(OrderStatus.WAIT_FOR_RETURN_GOODS_APPROVE);
        orderState.doTransition(order);
    }

    /**
     * 拒绝退货申请
     *
     * @param order 订单
     */
    @Override
    public void rejectReturnGoodsApply(OrderInfoDTO order) throws Exception {
        OrderState orderState = orderStateFactory.getByOrderStatus(OrderStatus.RETURN_GOODS_REJECTED);
        orderState.doTransition(order);
    }

    /**
     * 通过退货申请
     *
     * @param order 订单
     * @throws Exception
     */
    @Override
    public void passedReturnGoodsApply(OrderInfoDTO order) throws Exception {
        OrderState orderState = orderStateFactory.getByOrderStatus(OrderStatus.WAIT_FOR_SEND_OUT_RETURN_GOODS);
        orderState.doTransition(order);
    }
}
