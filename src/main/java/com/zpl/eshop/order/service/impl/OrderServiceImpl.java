package com.zpl.eshop.order.service.impl;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.order.price.*;
import com.zpl.eshop.order.service.OrderService;
import com.zpl.eshop.promotion.constant.PromotionActivityType;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import com.zpl.eshop.promotion.service.PromotionService;
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

    /**
     * 折扣减免类型的价格计算工厂
     */
    @Autowired
    private DiscountOrderPriceCalculatorFactory discountOrderPriceCalculatorFactory;

    /**
     * 赠品类型的价格计算工厂
     */
    @Autowired
    private GiftOrderPriceCalculatorFactory giftOrderPriceCalculatorFactory;

    /**
     * 默认价格计算工厂
     */
    @Autowired
    private DefaultOrderPriceCalculatorFactory defaultOrderPriceCalculatorFactory;

    /**
     * 促销中心service组件
     */
    @Autowired
    private PromotionService promotionService;

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
    public Boolean informReturnGoodsWorksheetRejectEvent(Long orderId) {
        return true;
    }

    /**
     * 通知订单中心，“退货工单审核通过”事件发生了
     *
     * @param orderId 订单 id
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsWorksheetApproveEvent(Long orderId) {
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
     * 计算订单价格
     *
     * @param order 订单
     * @return 计算金额后的订单
     */
    @Override
    public OrderInfoDTO calculateOrderPrice(OrderInfoDTO order) throws Exception {
        // 定义各种价格
        Double totalAmount = 0.0;
        Double discountAmount = 0.0;
        Double freightAmount = 0.0;

        for (OrderItemDTO item : order.getOrderItems()) {
            // 查询订单条目使用的促销活动
            PromotionActivityDTO promotionActivity = promotionService.getById(item.getPromotionActivityId());
            // 根据促销活动获取订单计算组件工厂
            OrderPriceCalculatorFactory orderPriceCalculatorFactory = getOrderPriceCalculatorFactory(promotionActivity);
            // 从订单计算组件工厂中获取一套订单的价格计算组件
            TotalPriceCalculator totalPriceCalculator = orderPriceCalculatorFactory.createTotalPriceCalculator();
            PromotionActivityCalculator promotionActivityCalculator = orderPriceCalculatorFactory.createPromotionActivityCalculator(promotionActivity);
            FreightCalculator freightCalculator = orderPriceCalculatorFactory.createFreightCalculator();

            // 计算订单条目总金额
            totalAmount += totalPriceCalculator.calculate(item);
            // 处理促销活动，计算促销活动的减免金额及促销活动的赠品
            PromotionActivityResult result = promotionActivityCalculator.calculate(item, promotionActivity);
            order.getOrderItems().addAll(result.getOrderItems());
            discountAmount += result.getDiscountAmount();
            // 计算订单条目的运费
            freightAmount += freightCalculator.calculate(item, result);
        }

        // 给订单设置计算后的结果（同时已经包含了所有条目的赠品）
        order.setTotalAmount(totalAmount);
        order.setDiscountAmount(discountAmount);
        order.setFreight(freightAmount);
        return order;
    }

    /**
     * 获取订单价格计算工厂
     *
     * @param promotionActivity 促销活动
     * @return 订单价格计算工厂
     */
    private OrderPriceCalculatorFactory getOrderPriceCalculatorFactory(PromotionActivityDTO promotionActivity) {
        Integer promotionActivityType = promotionActivity.getType();

        if (promotionActivityType == null) {
            return defaultOrderPriceCalculatorFactory;
        }
        if (PromotionActivityType.DIRECT_DISCOUNT.equals(promotionActivityType)
                || PromotionActivityType.MULTI_DISCOUNT.equals(promotionActivityType)
                || PromotionActivityType.REACH_DISCOUNT.equals(promotionActivityType)) {
            return discountOrderPriceCalculatorFactory;
        } else {
            return giftOrderPriceCalculatorFactory;
        }
    }
}
