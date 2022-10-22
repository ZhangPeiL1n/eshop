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
    private FinishedOrderState finishedOrderState;

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
    private FinishedInputReturnGoodsOrderState finishedInputReturnGoodsOrderState;

    /**
     * 完成退货退款状态
     */
    @Autowired
    private FinishedRefundOrderState finishedRefundOrderState;

    /**
     * 默认状态组件
     */
    @Autowired
    private DefaultOrderState defaultOrderState;

    /**
     * 创建订单
     *
     * @param order 订单
     */
    @Override
    public void create(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.WAIT_FOR_PAY).doTransition(order);
    }

    /**
     * 能否取消订单
     *
     * @param order 订单
     * @return
     */
    @Override
    public Boolean canCancel(OrderInfoDTO order) throws Exception {
        return getOrderState(order).canCancel(order);
    }

    /**
     * 取消订单
     *
     * @param order 订单
     */
    @Override
    public void cancel(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.CANCELED).doTransition(order);
    }

    /**
     * 能否支付订单
     *
     * @param order 订单
     * @return
     */
    @Override
    public Boolean canPay(OrderInfoDTO order) throws Exception {
        return getOrderState(order).canPay(order);
    }

    /**
     * 支付订单
     *
     * @param order
     */
    @Override
    public void pay(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.WAIT_FOR_DELIVERY).doTransition(order);
    }

    /**
     * 完成商品发货
     *
     * @param order 订单
     * @throws Exception
     */
    @Override
    public void finishDelivery(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.WAIT_FOR_RECEIVE).doTransition(order);
    }

    /**
     * 判断能否确认收货
     *
     * @param order 订单
     * @return 能否手动确认收货
     */
    @Override
    public Boolean canConfirmReceipt(OrderInfoDTO order) {
        return getOrderState(order).canConfirmReceipt(order);
    }

    /**
     * 确认收货
     *
     * @param order 订单
     */

    @Override
    public void confirmReceipt(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.FINISHED).doTransition(order);
    }

    /**
     * 能否申请退货
     *
     * @param order 订单
     * @return 能否申请退货
     */

    @Override
    public Boolean canApplyReturnGoods(OrderInfoDTO order) {
        return getOrderState(order).canApplyReturnGoods(order);
    }

    /**
     * 申请退货
     *
     * @param order 订单
     */

    @Override
    public void applyReturnGoods(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.WAIT_FOR_RETURN_GOODS_APPROVE).doTransition(order);
    }

    /**
     * 拒绝退货申请
     *
     * @param order 订单
     */

    @Override
    public void rejectReturnGoodsApply(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.RETURN_GOODS_REJECTED).doTransition(order);
    }

    /**
     * 通过退货申请
     *
     * @param order 订单
     * @throws Exception
     */

    @Override
    public void passedReturnGoodsApply(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.WAIT_FOR_SEND_OUT_RETURN_GOODS).doTransition(order);
    }

    /**
     * 寄送退货商品
     *
     * @param order 订单
     * @throws Exception
     */

    @Override
    public void sendOutReturnGoods(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.WAIT_FOR_RECEIVE_RETURN_GOODS).doTransition(order);
    }


    /**
     * 确认收到退货商品
     *
     * @param order 订单
     * @throws Exception
     */

    @Override
    public void confirmReceivedReturnGoods(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.WAIT_FOR_INPUT_RETURN_GOODS).doTransition(order);
    }

    /**
     * 完成退货入库
     *
     * @param order 订单
     * @throws Exception
     */

    @Override
    public void finishedInputReturnGoods(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.FINISHED_INPUT_RETURN_GOODS).doTransition(order);
    }

    /**
     * 完成退款
     *
     * @param order 订单
     * @throws Exception
     */

    @Override
    public void finishedRefund(OrderInfoDTO order) throws Exception {
        getOrderState(OrderStatus.FINISHED_REFUND).doTransition(order);
    }

    /**
     * 获取订单状态组件
     *
     * @param order 订单
     * @return 订单状态组件
     */
    private OrderState getOrderState(OrderInfoDTO order) {
        return getOrderState(order.getOrderStatus());
    }

    /**
     * 根据订单状态常量获取订单状态组件
     *
     * @param orderStatus 订单状态常量
     * @return 订单状态组件
     */
    private OrderState getOrderState(Integer orderStatus) {
        if (OrderStatus.WAIT_FOR_PAY.equals(orderStatus)) {
            return waitForPayOrderState;
        } else if (OrderStatus.CANCELED.equals(orderStatus)) {
            return canceledOrderState;
        } else if ((OrderStatus.WAIT_FOR_DELIVERY.equals(orderStatus))) {
            return waitForDeliveryState;
        } else if ((OrderStatus.WAIT_FOR_RECEIVE.equals(orderStatus))) {
            return waitForReceiveState;
        } else if ((OrderStatus.FINISHED.equals(orderStatus))) {
            return finishedOrderState;
        } else if ((OrderStatus.WAIT_FOR_RETURN_GOODS_APPROVE.equals(orderStatus))) {
            return waitForReturnGoodsApproveOrderState;
        } else if ((OrderStatus.RETURN_GOODS_REJECTED.equals(orderStatus))) {
            return returnGoodsRejectedOrderState;
        } else if ((OrderStatus.WAIT_FOR_SEND_OUT_RETURN_GOODS.equals(orderStatus))) {
            return waitForSendOutReturnGoodsOrderState;
        } else if ((OrderStatus.WAIT_FOR_RECEIVE_RETURN_GOODS.equals(orderStatus))) {
            return waitForReceiveReturnGoodsOrderState;
        } else if ((OrderStatus.FINISHED_INPUT_RETURN_GOODS.equals(orderStatus))) {
            return finishedInputReturnGoodsOrderState;
        } else if ((OrderStatus.FINISHED_REFUND.equals(orderStatus))) {
            return finishedRefundOrderState;
        }
        return defaultOrderState;
    }
}
