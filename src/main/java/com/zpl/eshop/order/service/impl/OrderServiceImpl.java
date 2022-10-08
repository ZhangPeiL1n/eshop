package com.zpl.eshop.order.service.impl;

import com.zpl.eshop.inventory.service.InventoryService;
import com.zpl.eshop.membership.service.MembershipService;
import com.zpl.eshop.order.constant.OrderOperateType;
import com.zpl.eshop.order.dao.OrderOperateLogDAO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.service.OrderInfoService;
import com.zpl.eshop.order.service.OrderService;
import com.zpl.eshop.order.state.OrderStateManager;
import com.zpl.eshop.schedule.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单中心接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/2 18:54
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * 订单状态管理器
     */
    @Autowired
    private OrderStateManager orderStateManager;

    /**
     * 订单管理service
     */
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 库存中心Service
     */
    @Autowired
    private InventoryService inventoryService;

    /**
     * 调度中心
     */
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 会员中心
     */
    @Autowired
    private MembershipService membershipService;

    /**
     * 订单操作日志管理DAO组件
     */
    @Autowired
    private OrderOperateLogDAO orderOperateLogDAO;

    /**
     * 订单操作日志工厂
     */
    @Autowired
    private OrderOperateLogFactory orderOperateLogFactory;

    /**
     * 通知订单中心，“商品完成发货”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    @Override
    public Boolean informGoodsDeliveryFinishEvent(Long orderId) throws Exception {
        try {
            OrderInfoDTO order = orderInfoService.getById(orderId);
            orderStateManager.finishDelivery(order);

            orderOperateLogDAO.save(orderOperateLogFactory.get(order, OrderOperateType.GOODS_DELIVERY));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
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
    public Boolean informReturnGoodsReceivedEvent(Long orderId) {
        return true;
    }

    /**
     * 通知订单中心，“退货入库单审核通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsInputOrderApprovedEvent(Long orderId) {
        return true;
    }

    /**
     * 通知订单中心，“完成退款”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    @Override
    public Boolean informRefundFinishedEvent(Long orderId) {
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
        try {
            OrderInfoDTO order = orderInfoService.getById(orderId);
            orderStateManager.payOrder(order);
            orderOperateLogDAO.save(orderOperateLogFactory.get(order, OrderOperateType.PAY_ORDER));
            inventoryService.informPayOrderEvent(order);
            scheduleService.scheduleSaleDelivery(order);
            membershipService.informPayOrderEvent(order.getUserAccountId(), order.getPayableAmount());
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
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

    /**
     * 根据id查询订单
     *
     * @param orderInfoId 订单id
     * @return 订单
     */
    @Override
    public OrderInfoDTO getOrderById(Long orderInfoId) {
        return null;
    }
}
