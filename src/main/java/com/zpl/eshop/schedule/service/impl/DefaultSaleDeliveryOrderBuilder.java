package com.zpl.eshop.schedule.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.logistics.service.LogisticsService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.schedule.domain.SaleDeliveryScheduleResult;
import com.zpl.eshop.schedule.service.SaleDeliveryOrderBuilder;
import com.zpl.eshop.schedule.service.SaleDeliveryScheduler;
import com.zpl.eshop.wms.constant.SaleDeliveryOrderStatus;
import com.zpl.eshop.wms.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/8/18 17:03
 **/
@Component
@Scope("prototype")
public class DefaultSaleDeliveryOrderBuilder implements SaleDeliveryOrderBuilder {

    /**
     * 销售出库单
     */
    private final SaleDeliveryOrderDTO saleDeliveryOrder = new SaleDeliveryOrderDTO();

    /**
     * 销售出库调度器
     */
    @Autowired
    private SaleDeliveryScheduler saleDeliveryScheduler;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 物流中心 service 组件
     */
    @Autowired
    private LogisticsService logisticsService;

    /**
     * 设置订单相关数据
     *
     * @param order 订单
     * @return builder
     */
    @Override
    public SaleDeliveryOrderBuilder setOrderRelatedData(OrderInfoDTO order) throws Exception {
        saleDeliveryOrder.setOrderId(order.getId());
        order.clone(saleDeliveryOrder);
        return this;
    }

    /**
     * 创建销售出库单条目
     * 针对每一个订单条目去调度销售出库：调度销售出库主要就是创建 销售出库单条目 中的 拣货单明细 和 出库单明细
     *
     * @param orderItems 订单条目
     * @return builder
     */
    @Override
    public SaleDeliveryOrderBuilder createSaleDeliveryOrderItems(List<OrderItemDTO> orderItems) throws Exception {
        List<SaleDeliveryOrderItemDTO> saleDeliveryOrderItems = new ArrayList<>();
        // 针对每一个订单条目
        orderItems.forEach(orderItem -> {
            try {
                SaleDeliveryScheduleResult scheduleResult = saleDeliveryScheduler.getScheduleResult(orderItem);
                // 调度每个条目销售出库
                SaleDeliveryOrderItemDTO saleDeliveryOrderItem = orderItem.clone(SaleDeliveryOrderItemDTO.class);
                saleDeliveryOrderItem.setPickingItems(
                        ObjectUtils.convertList(scheduleResult.getPickingItems(), SaleDeliveryOrderPickingItemDTO.class));
                saleDeliveryOrderItem.setSendOutItems(
                        ObjectUtils.convertList(scheduleResult.getSendOutDetails(), SaleDeliveryOrderSendOutDetailDTO.class));
                saleDeliveryOrderItem.setGmtCreate(dateProvider.getCurrentTime());
                saleDeliveryOrderItem.setGmtModified(dateProvider.getCurrentTime());
                saleDeliveryOrderItems.add(saleDeliveryOrderItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        saleDeliveryOrder.setSaleDeliveryOrderItems(saleDeliveryOrderItems);
        return this;
    }

    /**
     * 创建发货单
     *
     * @param order 订单
     * @return builder
     */
    @Override
    public SaleDeliveryOrderBuilder createSendOutOrder(OrderInfoDTO order) throws Exception {
        SendOutOrderDTO sendOutOrder = new SendOutOrderDTO();
        sendOutOrder.setSendOutOrderItems(new ArrayList<>());
        order.clone(sendOutOrder);
        sendOutOrder.setOrderId(order.getId());
        sendOutOrder.setGmtCreate(dateProvider.getCurrentTime());
        sendOutOrder.setGmtModified(dateProvider.getCurrentTime());
        order.getOrderItems().forEach(item -> {
            try {
                SendOutOrderItemDTO sendOutOrderItem = item.clone(SendOutOrderItemDTO.class);
                sendOutOrderItem.setGmtCreate(dateProvider.getCurrentTime());
                sendOutOrderItem.setGmtModified(dateProvider.getCurrentTime());
                sendOutOrder.getSendOutOrderItems().add(sendOutOrderItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        saleDeliveryOrder.setSendOutOrder(sendOutOrder);
        return this;
    }

    /**
     * 创建物流单
     *
     * @param order 订单
     * @return builder
     */
    @Override
    public SaleDeliveryOrderBuilder createLogisticOrder(OrderInfoDTO order) throws Exception {
        LogisticOrderDTO logisticOrder = logisticsService.applyLogisticOrder(order);
        saleDeliveryOrder.setLogisticOrder(logisticOrder);
        return this;
    }

    /**
     * 初始化销售出库单的状态
     *
     * @return builder
     */
    @Override
    public SaleDeliveryOrderBuilder initStatus() throws Exception {
        saleDeliveryOrder.setSaleDeliveryOrderStatus(SaleDeliveryOrderStatus.EDITING);
        return this;
    }

    /**
     * 初始化时间相关的字段
     *
     * @return builder
     */
    @Override
    public SaleDeliveryOrderBuilder initTimes() throws Exception {
        saleDeliveryOrder.setGmtCreate(dateProvider.getCurrentTime());
        saleDeliveryOrder.setGmtModified(dateProvider.getCurrentTime());
        return this;
    }

    /**
     * 创建最终构造好的销售出库单
     *
     * @return 销售出库单
     */
    @Override
    public SaleDeliveryOrderDTO create() throws Exception {
        return saleDeliveryOrder;
    }
}
