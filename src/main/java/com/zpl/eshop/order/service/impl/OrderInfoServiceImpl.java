package com.zpl.eshop.order.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.membership.domain.DeliveryAddressDTO;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.dao.OrderItemDAO;
import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.order.price.*;
import com.zpl.eshop.order.service.OrderInfoService;
import com.zpl.eshop.promotion.constant.PromotionActivityType;
import com.zpl.eshop.promotion.domain.CouponDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import com.zpl.eshop.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author ZhangPeiL1n
 * @date 2022/8/16 15:21
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderInfoServiceImpl implements OrderInfoService {

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
     * 优惠券计算组件工厂
     */
    @Autowired
    private CouponCalculatorFactory couponCalculatorFactory;

    /**
     * 促销中心service组件
     */
    @Autowired
    private PromotionService promotionService;

    /**
     * 订单管理模块 DAO 组件
     */
    @Autowired
    private OrderInfoDAO orderInfoDAO;

    /**
     * 订单条目管理模块 DAO 组件
     */
    @Autowired
    private OrderItemDAO orderItemDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 计算订单价格
     *
     * @param order 订单
     * @return 计算金额后的订单
     */
    @Override
    public OrderInfoDTO calculateOrderPrice(OrderInfoDTO order, DeliveryAddressDTO deliveryAddress) throws Exception {
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
            freightAmount += freightCalculator.calculate(item, deliveryAddress, result);
        }

        // 给订单设置计算后的结果（同时已经包含了所有条目的赠品）
        order.setTotalAmount(totalAmount);
        order.setDiscountAmount(discountAmount);
        order.setFreight(freightAmount);
        order.setPayableAmount(totalAmount + freightAmount - discountAmount);
        return order;
    }

    /**
     * 计算优惠券抵扣价格
     *
     * @param order  订单
     * @param coupon 优惠券
     * @return 计算金额后的订单
     */
    @Override
    public OrderInfoDTO calculateCouponDiscountPrice(OrderInfoDTO order, CouponDTO coupon) {
        CouponCalculator couponCalculator = couponCalculatorFactory.create(coupon);
        Double couponAmount = couponCalculator.calculate(order, coupon);
        order.setCouponAmount(couponAmount);
        order.setPayableAmount(order.getPayableAmount() - couponAmount);
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

    /**
     * 保存订单
     *
     * @param order 订单
     */
    @Override
    public OrderInfoDTO save(OrderInfoDTO order) throws Exception {
        order.setOrderNo(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setGmtCreate(dateProvider.getCurrentTime());
        order.setGmtModified(dateProvider.getCurrentTime());
        Long id = orderInfoDAO.save(order.clone(OrderInfoDO.class));
        order.setId(id);

        order.getOrderItems().forEach(item -> {
            try {
                item.setOrderInfoId(id);
                item.setGmtCreate(dateProvider.getCurrentTime());
                item.setGmtModified(dateProvider.getCurrentTime());
                Long itemId = orderItemDAO.save(item.clone(OrderItemDO.class));
                item.setId(itemId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return order;
    }
}
