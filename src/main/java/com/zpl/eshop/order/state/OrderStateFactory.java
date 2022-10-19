package com.zpl.eshop.order.state;

import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单状态工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/9/12 14:57
 **/
@Component
public class OrderStateFactory {

    /**
     * 待付款状态组件
     */
    @Autowired
    private WaitForPayOrderState waitForPayOrderState;

    /**
     * 已取消状态组件
     */
    @Autowired
    private CanceledOrderState canceledOrderState;

    /**
     * 待发货状态组件
     */
    @Autowired
    private WaitForDeliveryState waitForDeliveryState;

    /**
     * 待收货状态
     */
    @Autowired
    private WaitForReceiveState waitForReceiveState;

    /**
     * 已完成
     */
    @Autowired
    private FinishOrderState finishOrderState;

    /**
     * 退货申请待审核状态组件
     */
    @Autowired
    private WaitForReturnGoodsApproveOrderState waitForReturnGoodsApproveOrderState;

    /**
     * 退货审核不通过
     */
    @Autowired
    private ReturnGoodsRejectedOrderState returnGoodsRejectedOrderState;

    /**
     * 退货商品待寄送状态
     */
    @Autowired
    private WaitForSendOutReturnGoodsOrderState waitForSendOutReturnGoodsOrderState;

    /**
     * 退货商品待收货状态
     */
    @Autowired
    private WaitForReceiveReturnGoodsOrderState waitForReceiveReturnGoodsOrderState;

    /**
     * 完成退货入库状态
     */
    @Autowired
    private FinishInputReturnGoodsOrderState finishInputReturnGoodsOrderState;

    /**
     * 完成退货退款状态
     */
    @Autowired
    private FinishReturnGoodsRefundOrderState finishReturnGoodsRefundOrderState;

    /**
     * 默认状态组件
     */
    @Autowired
    private DefaultOrderState defaultOrderState;

    /**
     * 获取订单状态组件
     *
     * @param order 订单
     * @return 订单状态组件
     */
    public OrderState get(OrderInfoDTO order) {
        return getByOrderStatus(order.getOrderStatus());
    }

    /**
     * 根据订单状态常量获取订单状态组件
     *
     * @param orderStatus 订单状态常量
     * @return 订单状态组件
     */
    public OrderState getByOrderStatus(Integer orderStatus) {
        if (OrderStatus.WAIT_FOR_PAY.equals(orderStatus)) {
            return waitForPayOrderState;
        } else if (OrderStatus.CANCELED.equals(orderStatus)) {
            return canceledOrderState;
        } else if ((OrderStatus.WAIT_FOR_DELIVERY.equals(orderStatus))) {
            return waitForDeliveryState;
        } else if ((OrderStatus.WAIT_FOR_RECEIVE.equals(orderStatus))) {
            return waitForReceiveState;
        } else if ((OrderStatus.FINISHED.equals(orderStatus))) {
            return finishOrderState;
        } else if ((OrderStatus.WAIT_FOR_RETURN_GOODS_APPROVE.equals(orderStatus))) {
            return waitForReturnGoodsApproveOrderState;
        } else if ((OrderStatus.RETURN_GOODS_REJECTED.equals(orderStatus))) {
            return returnGoodsRejectedOrderState;
        } else if ((OrderStatus.WAIT_FOR_SEND_OUT_RETURN_GOODS.equals(orderStatus))) {
            return waitForSendOutReturnGoodsOrderState;
        } else if ((OrderStatus.WAIT_FOR_RECEIVE_RETURN_GOODS.equals(orderStatus))) {
            return waitForReceiveReturnGoodsOrderState;
        } else if ((OrderStatus.FINISHED_INPUT_RETURN_GOODS.equals(orderStatus))) {
            return finishInputReturnGoodsOrderState;
        } else if ((OrderStatus.FINISHED_REFUND.equals(orderStatus))) {
            return finishReturnGoodsRefundOrderState;
        }
        return defaultOrderState;
    }
}
