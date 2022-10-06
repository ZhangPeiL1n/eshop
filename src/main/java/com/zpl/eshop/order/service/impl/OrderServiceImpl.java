package com.zpl.eshop.order.service.impl;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单中心接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/2 18:54
 **/
@Service
public class OrderServiceImpl implements OrderService {
    /**
     * 通知订单中心，“商品完成发货”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    @Override
    public Boolean informGoodsDeliveryFinishEvent(Long orderId) {
        return true;
    }

    /**
     * 通知订单中心，“退货工单审核不通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsWorksheetRejectedEvent(Long orderId) {
        return true;
    }

    /**
     * 通知订单中心，“退货工单审核通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsWorksheetApprovedEvent(Long orderId) {
        return true;
    }

    /**
     * 通知订单中心，“确认收到退货商品”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsReceiveEvent(Long orderId) {
        return true;
    }

    /**
     * 通知订单中心，“退货入库单审核通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsInputOrderApproveEvent(Long orderId) {
        return true;
    }

    /**
     * 通知订单中心，“完成退款”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    @Override
    public Boolean informRefundFinishEvent(Long orderId) {
        return true;
    }

    /**
     * 通知订单中心，“发表评论”事件发生了
     *
     * @param orderId 订单id
     * @return 处理结果
     */
    @Override
    public Boolean informPublishCommentEvent(Long orderId) {
        return true;
    }

    /**
     * 通知订单中心，“支付成功”事件发生了
     *
     * @param orderId 订单id
     * @return 处理结果
     */
    @Override
    public Boolean informPaySucceed(Long orderId) {
        return true;
    }

    /**
     * 从订单中心获取，确认收货时间超过了 7天而且没有发表评论的订单
     *
     * @return 订单信息 DTO集合
     */
    @Override
    public List<OrderInfoDTO> listNotPublishCommentOrders() {
        return new ArrayList<>();
    }

    /**
     * 通知订单中心，“批量发表评论”事件发生了
     *
     * @param orderId 订单 id 集合
     * @return 处理结果
     */
    @Override
    public Boolean informBatchPublishCommentEvent(List<Long> orderId) {
        return true;
    }
}
