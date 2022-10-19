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

    /**
     * 判断能否确认收货
     *
     * @param order 订单
     * @return 能否确认收货
     */
    Boolean canConfirmReceipt(OrderInfoDTO order);

    /**
     * 确认收货
     *
     * @param order 订单
     */
    void confirmReceipt(OrderInfoDTO order) throws Exception;

    /**
     * 能否申请退货
     *
     * @param order 订单
     * @return 能否申请退货
     */
    Boolean canApplyReturnGoods(OrderInfoDTO order);

    /**
     * 申请退货
     *
     * @param order 订单
     */
    void applyReturnGoods(OrderInfoDTO order) throws Exception;

    /**
     * 拒绝退货申请
     *
     * @param order 订单
     */
    void rejectReturnGoodsApply(OrderInfoDTO order) throws Exception;

    /**
     * 通过退货申请
     *
     * @param order 订单
     * @throws Exception
     */
    void passedReturnGoodsApply(OrderInfoDTO order) throws Exception;

    /**
     * 寄送退货商品
     *
     * @param order 订单
     * @throws Exception
     */
    void sendOutReturnGoods(OrderInfoDTO order) throws Exception;

    /**
     * 确认收到退货商品
     *
     * @param order 订单
     * @throws Exception
     */
    void confirmReceivedReturnGoods(OrderInfoDTO order) throws Exception;

    /**
     * 完成退货入库
     *
     * @param order 订单
     * @throws Exception
     */
    void finishedInputReturnGoods(OrderInfoDTO order) throws Exception;

    /**
     * 完成退款
     *
     * @param order 订单
     * @throws Exception
     */
    void finishedRefund(OrderInfoDTO order) throws Exception;
}
