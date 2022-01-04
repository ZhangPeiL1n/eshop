package com.zpl.eshop.order.service;

/**
 * 订单中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:08
 **/
public interface OrderFacadeService {
    /**
     * 通知订单中心，“商品完成发货”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    Boolean informGoodsDeliveryFinishEvent(Long orderId);

    /**
     * 通知订单中心，“退货工单审核不通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    Boolean informReturnGoodsWorksheetRejectEvent(Long orderId);

    /**
     * 通知订单中心，“退货工单审核通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    Boolean informReturnGoodsWorksheetApproveEvent(Long orderId);

    /**
     * 通知订单中心，“确认收到退货商品”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    Boolean informReturnGoodsReceiveEvent(Long orderId);

    /**
     * 通知订单中心，“退货入库单审核通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    Boolean informReturnGoodsInputOrderApproveEvent(Long orderId);

    /**
     * 通知订单中心，“完成退款”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    Boolean informRefundFinishEvent(Long orderId);
}
