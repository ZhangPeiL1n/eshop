package com.zpl.eshop.order.service;

import com.zpl.eshop.order.domain.OrderInfoDTO;

import java.util.List;

/**
 * 订单中心接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:08
 **/
public interface OrderService {
    /**
     * 通知订单中心，“商品完成发货”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informGoodsDeliveryFinishedEvent(Long orderId) throws Exception;

    /**
     * 通知订单中心，“退货工单审核不通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informReturnGoodsWorksheetRejectedEvent(Long orderId) throws Exception;

    /**
     * 通知订单中心，“退货工单审核通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informReturnGoodsWorksheetApprovedEvent(Long orderId) throws Exception;

    /**
     * 通知订单中心，“确认收到退货商品”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informReturnGoodsReceivedEvent(Long orderId) throws Exception;

    /**
     * 通知订单中心，“退货入库单审核通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informReturnGoodsInputOrderApprovedEvent(Long orderId) throws Exception;

    /**
     * 通知订单中心，“完成退款”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informRefundFinishedEvent(Long orderId) throws Exception;

    /**
     * 通知订单中心，“发表评论”事件发生了
     *
     * @param orderId 订单id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informPublishCommentEvent(Long orderId) throws Exception;

    /**
     * 通知订单中心，“支付成功”事件发生了
     *
     * @param orderId 订单id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informPayOrderSucceed(Long orderId) throws Exception;

    /**
     * 从订单中心获取，确认收货时间超过了 7天而且没有发表评论的订单
     *
     * @return 订单信息 DTO集合
     * @throws Exception
     */
    List<OrderInfoDTO> listNotPublishCommentOrders() throws Exception;

    /**
     * 通知订单中心，“批量发表评论”事件发生了
     *
     * @param orderIds 订单 id 集合
     * @return 处理结果
     * @throws Exception
     */
    Boolean informBatchPublishCommentEvent(List<Long> orderIds) throws Exception;

    /**
     * 根据id查询订单
     *
     * @param orderInfoId 订单id
     * @return 订单
     * @throws Exception
     */
    OrderInfoDTO getOrderById(Long orderInfoId) throws Exception;
}
