package com.zpl.eshop.schedule.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetDTO;
import com.zpl.eshop.inventory.service.InventoryService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderItemDTO;
import com.zpl.eshop.schedule.dao.ScheduleOrderPickingItemDAO;
import com.zpl.eshop.schedule.dao.ScheduleOrderSendOutDetailDAO;
import com.zpl.eshop.schedule.service.ScheduleService;
import com.zpl.eshop.schedule.stock.PurchaseInputScheduleStockUpdaterFactory;
import com.zpl.eshop.schedule.stock.ReturnInputScheduleStockUpdaterFactory;
import com.zpl.eshop.schedule.stock.ScheduleStockUpdater;
import com.zpl.eshop.wms.domain.*;
import com.zpl.eshop.wms.service.WmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 调度中心对外接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 13:42
 **/
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    /**
     * wms 对外接口service组件
     */
    @Autowired
    private WmsService wmsService;

    /**
     * 销售出库单构造工厂
     */
    @Autowired
    private SaleDeliveryOrderBuilderFactory saleDeliveryOrderBuilderFactory;

    /**
     * 采购入库单工厂
     */
    @Autowired
    private PurchaseInputScheduleStockUpdaterFactory purchaseInputStockUpdaterFactory;

    /**
     * 退货入库单工厂
     */
    @Autowired
    private ReturnInputScheduleStockUpdaterFactory returnInputStockUpdaterFactory;

    /**
     * 库存中心
     */
    @Autowired
    private InventoryService inventoryService;

    /**
     * 销售出库调度器
     */
    @Autowired
    private SaleDeliveryScheduler saleDeliveryScheduler;

    /**
     * 拣货条目管理DAO组件
     */
    @Autowired
    private ScheduleOrderPickingItemDAO pickingItemDAO;

    /**
     * 发货明细管理DAO组件
     */
    @Autowired
    private ScheduleOrderSendOutDetailDAO sendOutDetailDAO;

    /**
     * 通知调度中心，“采购入库完成”事件发生了
     *
     * @param purchaseInputOrder 采购入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrder) {
        ScheduleStockUpdater stockUpdater = purchaseInputStockUpdaterFactory.create(purchaseInputOrder);
        stockUpdater.update();
        inventoryService.informPurchaseInputFinished(purchaseInputOrder);
        return true;
    }

    /**
     * 通知调度中心，“提交订单”事件发生了
     *
     * @param order 订单
     * @return 处理结果
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderInfoDTO order) {
        try {
            for (OrderItemDTO orderItem : order.getOrderItems()) {
                SaleDeliveryOrderItemDTO saleDeliveryOrderItem = saleDeliveryScheduler.schedule(orderItem);

                pickingItemDAO.batchSave(saleDeliveryOrderItem.getPickingItems(), orderItem);
                sendOutDetailDAO.batchSave(saleDeliveryOrderItem.getSendOutItems(), orderItem);
            }

            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 通知调度中心，“支付订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPayOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 通知调度中心，“取消订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informCancelOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 通知调度中心，“退货入库”事件发生了
     *
     * @param returnGoodsInputOrder 退货入库DTO
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrder) {
        ScheduleStockUpdater stockUpdater = returnInputStockUpdaterFactory.create(returnGoodsInputOrder);
        stockUpdater.update();
        inventoryService.informReturnGoodsInputFinished(returnGoodsInputOrder);
        return true;
    }

    /**
     * 调度采购入库
     *
     * @param purchaseOrder 采购单DTO
     * @return 处理结果
     */
    @Override
    public Boolean schedulePurchaseInput(PurchaseOrderDTO purchaseOrder) {
        try {
            // 拷贝采购单中基本信息到采购入库单中
            PurchaseInputOrderDTO purchaseInputOrder = createPurchaseInputOrder(purchaseOrder);

            // 拷贝采购单条目到采购入库单条目
            List<PurchaseInputOrderItemDTO> purchaseInputOrderItems = new ArrayList<>(purchaseOrder.getItems().size());
            for (PurchaseOrderItemDTO purchaseOrderItem : purchaseOrder.getItems()) {
                PurchaseInputOrderItemDTO purchaseInputOrderItem = createPurchaseInputOrderItem(purchaseOrderItem);
                purchaseInputOrderItems.add(purchaseInputOrderItem);
            }

            purchaseInputOrder.setItems(purchaseInputOrderItems);

            // 调用wms中心接口
            wmsService.createPurchaseInputOrder(purchaseInputOrder);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 调度销售出库
     *
     * @param order 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean scheduleSaleDelivery(OrderInfoDTO order) {
        try {
            SaleDeliveryOrderBuilder saleDeliveryOrderBuilder = saleDeliveryOrderBuilderFactory.get();
            SaleDeliveryOrderDTO saleDeliveryOrder = saleDeliveryOrderBuilder
                    // 创建销售出库单相关数据
                    .setOrderRelatedData(order)
                    // 创建销售出库单条目数据，这个是重点
                    .createSaleDeliveryOrderItems(order.getOrderItems())
                    .createSendOutOrder(order)
                    .createLogisticOrder(order)
                    .initStatus()
                    .initTimes()
                    .create();

            wmsService.createSaleDeliveryOrder(saleDeliveryOrder);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 调度退货入库
     *
     * @param order                订单DTO
     * @param returnGoodsWorksheet 退货入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean scheduleReturnGoodsInput(OrderInfoDTO order, ReturnGoodsWorksheetDTO returnGoodsWorksheet) throws Exception {
        ReturnGoodsInputOrderDTO returnGoodsInputOrder = order.clone(ReturnGoodsInputOrderDTO.class);
        returnGoodsInputOrder.setOrderId(order.getId());
        returnGoodsInputOrder.setReturnGoodsReason(returnGoodsWorksheet.getReturnGoodsReason());
        returnGoodsInputOrder.setReturnGoodsRemark(returnGoodsWorksheet.getReturnGoodsRemark());

        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItems = ObjectUtils.convertList(order.getOrderItems(), ReturnGoodsInputOrderItemDTO.class);
        returnGoodsInputOrder.setItems(returnGoodsInputOrderItems);

        return true;
    }

    /**
     * 创建一个采购入库单
     *
     * @param purchaseOrder 采购单
     * @return 采购入库单
     * @throws Exception
     */
    private PurchaseInputOrderDTO createPurchaseInputOrder(PurchaseOrderDTO purchaseOrder) throws Exception {

        PurchaseInputOrderDTO purchaseInputOrder = purchaseOrder.clone(PurchaseInputOrderDTO.class);
        purchaseInputOrder.setId(null);
        purchaseInputOrder.setGmtCreate(null);
        purchaseInputOrder.setGmtModified(null);

        purchaseInputOrder.setPurchaseContactor(purchaseOrder.getContactor());
        purchaseInputOrder.setPurchaseContactorPhoneNumber(purchaseOrder.getContactorPhoneNumber());
        purchaseInputOrder.setPurchaseContactorEmail(purchaseOrder.getContactorEmail());
        purchaseInputOrder.setPurchaseOrderRemark(purchaseOrder.getRemark());

        return purchaseInputOrder;
    }

    /**
     * 创建采购入库单条目
     *
     * @param purchaseOrderItem 采购单条目
     * @return 采购入库单条目
     * @throws Exception
     */
    private PurchaseInputOrderItemDTO createPurchaseInputOrderItem(PurchaseOrderItemDTO purchaseOrderItem) throws Exception {
        PurchaseInputOrderItemDTO purchaseInputOrderItem = purchaseOrderItem.clone(PurchaseInputOrderItemDTO.class);
        purchaseInputOrderItem.setId(null);
        purchaseInputOrderItem.setGmtCreate(null);
        purchaseInputOrderItem.setGmtModified(null);
        return purchaseInputOrderItem;
    }
}
