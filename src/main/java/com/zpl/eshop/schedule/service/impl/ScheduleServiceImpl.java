package com.zpl.eshop.schedule.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetDTO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderItemDTO;
import com.zpl.eshop.schedule.service.ScheduleService;
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
     * 通知调度中心，“采购入库完成”事件发生了
     *
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrderDTO) {

        return true;
    }

    /**
     * 通知调度中心，“提交订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderInfoDTO orderDTO) {
        return true;
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
    public Boolean cancelOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 通知调度中心，“退货入库”事件发生了
     *
     * @param returnGoodsInputOrderDTO 退货入库DTO
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) {
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

            purchaseInputOrder.setPurchaseInputOrderItemDTOs(purchaseInputOrderItems);

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
        returnGoodsInputOrder.setReturnGoodsComment(returnGoodsWorksheet.getReturnGoodsComment());

        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItems = ObjectUtils.convertList(order.getOrderItems(), ReturnGoodsInputOrderItemDTO.class);
        returnGoodsInputOrder.setReturnGoodsInputOrderItems(returnGoodsInputOrderItems);

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
        purchaseInputOrder.setPurchaseContactPhoneNumber(purchaseOrder.getContactorPhoneNumber());
        purchaseInputOrder.setPurchaseContactEmail(purchaseOrder.getContactorEmail());
        purchaseInputOrder.setPurchaseOrderComment(purchaseOrder.getRemark());

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
