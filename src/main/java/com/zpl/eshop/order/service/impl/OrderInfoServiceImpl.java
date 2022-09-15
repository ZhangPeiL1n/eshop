package com.zpl.eshop.order.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.inventory.service.InventoryService;
import com.zpl.eshop.order.constant.OrderOperateType;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.constant.PublishedComment;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.dao.OrderItemDAO;
import com.zpl.eshop.order.dao.OrderOperateLogDAO;
import com.zpl.eshop.order.domain.*;
import com.zpl.eshop.order.price.*;
import com.zpl.eshop.order.service.OrderInfoService;
import com.zpl.eshop.order.state.OrderStateManager;
import com.zpl.eshop.promotion.constant.PromotionActivityType;
import com.zpl.eshop.promotion.domain.CouponDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import com.zpl.eshop.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
     * 库存中心接口
     */
    @Autowired
    private InventoryService inventoryService;

    /**
     * 订单操作日志DAO
     */
    @Autowired
    private OrderOperateLogDAO orderOperateLogDAO;

    /**
     * 订单操作内容工厂
     */
    @Autowired
    private OrderOperateLogFactory orderOperateLogFactory;

    /**
     * 订单状态管理组件
     */
    @Autowired
    private OrderStateManager orderStateManager;

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

        ArrayList<OrderItemDTO> giftOrderItems = new ArrayList<>();

        for (OrderItemDTO orderItem : order.getOrderItems()) {
            // 查询订单条目使用的促销活动
            PromotionActivityDTO promotionActivity = promotionService.getById(orderItem.getPromotionActivityId());
            // 根据促销活动获取订单计算组件工厂
            OrderPriceCalculatorFactory orderPriceCalculatorFactory = getOrderPriceCalculatorFactory(promotionActivity);
            // 从订单计算组件工厂中获取一套订单的价格计算组件
            TotalPriceCalculator totalPriceCalculator = orderPriceCalculatorFactory.createTotalPriceCalculator();
            PromotionActivityCalculator promotionActivityCalculator = orderPriceCalculatorFactory.createPromotionActivityCalculator(promotionActivity);
            FreightCalculator freightCalculator = orderPriceCalculatorFactory.createFreightCalculator();

            // 计算订单条目总金额
            totalAmount += totalPriceCalculator.calculate(orderItem);
            // 处理促销活动，计算促销活动的减免金额及促销活动的赠品
            PromotionActivityResult result = promotionActivityCalculator.calculate(orderItem, promotionActivity);
            giftOrderItems.addAll(result.getOrderItems());
            discountAmount += result.getDiscountAmount();
            // 计算订单条目的运费
            freightAmount += freightCalculator.calculate(order, orderItem, result);
        }

        // 给订单设置计算后的结果（同时已经包含了所有条目的赠品）
        order.setTotalAmount(totalAmount);
        order.setDiscountAmount(discountAmount);
        order.setFreight(freightAmount);
        order.setPayableAmount(totalAmount + freightAmount - discountAmount);
        order.getOrderItems().addAll(giftOrderItems);
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
        if (!isStockEnough(order)) {
            return order;
        }
        saveOrder(order);
        orderStateManager.createOrder(order);
        orderOperateLogDAO.save(orderOperateLogFactory.get(order, OrderOperateType.CREATE_ORDER));
        inventoryService.informSubmitOrderEvent(order);
        return order;
    }

    /**
     * 分页查询订单
     *
     * @param query 分页查询条件
     * @return 订单
     */
    @Override
    public List<OrderInfoDTO> listByPage(OrderInfoQuery query) throws Exception {
        List<OrderInfoDTO> orders = ObjectUtils.convertList(orderInfoDAO.listByPage(query), OrderInfoDTO.class);
        orders.forEach(order -> {
            try {
                List<OrderItemDTO> items = ObjectUtils.convertList(orderItemDAO.listByOrderInfoId(order.getId()), OrderItemDTO.class);
                List<OrderOperateLogDTO> logs = ObjectUtils.convertList(orderOperateLogDAO.listByOrderInfoId(order.getId()), OrderOperateLogDTO.class);

                order.setOrderItems(items);
                order.setLogs(logs);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return orders;
    }

    /**
     * 获取订单详情
     *
     * @param id 订单id
     * @return 订单
     */
    @Override
    public OrderInfoDTO getById(Long id) throws Exception {
        OrderInfoDTO order = orderInfoDAO.getById(id).clone(OrderInfoDTO.class);
        List<OrderItemDTO> items = ObjectUtils.convertList(orderItemDAO.listByOrderInfoId(order.getId()), OrderItemDTO.class);
        List<OrderOperateLogDTO> logs = ObjectUtils.convertList(orderOperateLogDAO.listByOrderInfoId(order.getId()), OrderOperateLogDTO.class);

        order.setOrderItems(items);
        order.setLogs(logs);
        return order;
    }

    /**
     * 取消订单
     *
     * @param id 订单id
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean cancel(Long id) throws Exception {
        OrderInfoDTO order = getOrderInfoDTO(id);
        if (order == null) {
            return false;
        }
        if (!orderStateManager.canCancel(order)) {
            return false;
        }
        orderStateManager.cancelOrder(order);
        inventoryService.informCancelOrderEvent(order);
        orderOperateLogDAO.save(orderOperateLogFactory.get(order, OrderOperateType.MANUAL_CANCEL_ORDER));
        return true;
    }

    /**
     * 获取订单DTO
     *
     * @param id 订单id
     * @return
     */
    private OrderInfoDTO getOrderInfoDTO(Long id) throws Exception {
        OrderInfoDTO orderInfoDTO = orderInfoDAO.getById(id).clone(OrderInfoDTO.class);
        List<OrderItemDTO> orderItemDTOList = ObjectUtils.convertList(orderItemDAO.listByOrderInfoId(orderInfoDTO.getId()), OrderItemDTO.class);
        orderInfoDTO.setOrderItems(orderItemDTOList);
        return orderInfoDTO;
    }

    /**
     * 判断库存是否充足
     *
     * @param order 订单
     * @return 库存是否充足
     */
    private Boolean isStockEnough(OrderInfoDTO order) {
        for (OrderItemDTO item : order.getOrderItems()) {
            Long saleStockQuantity = inventoryService.getSaleStockQuantity(item.getGoodsSkuId());
            if (saleStockQuantity < item.getPurchaseQuantity()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 新增订单
     *
     * @param order 订单
     * @return
     * @throws Exception
     */
    private OrderInfoDTO saveOrder(OrderInfoDTO order) throws Exception {
        order.setOrderNo(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setPublishComment(PublishedComment.NO);
        order.setOrderStatus(OrderStatus.UNKNOWN);
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
