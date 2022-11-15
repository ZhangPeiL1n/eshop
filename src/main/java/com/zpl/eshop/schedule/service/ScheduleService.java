package com.zpl.eshop.schedule.service;

import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetDTO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;

/**
 * 调度中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:58
 **/
public interface ScheduleService {
    /**
     * 通知调度中心，“采购入库完成”事件发生了
     *
     * @param purchaseInputOrder 采购入库单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrder) throws Exception;

    /**
     * 通知调度中心，“提交订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean informSubmitOrderEvent(OrderInfoDTO order) throws Exception;

    /**
     * 通知调度中心，“支付订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean informPayOrderEvent(OrderInfoDTO order) throws Exception;

    /**
     * 通知调度中心，“取消订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean informCancelOrderEvent(OrderInfoDTO order) throws Exception;

    /**
     * 通知调度中心，“退货入库”事件发生了
     *
     * @param returnGoodsInputOrderDTO 退货入库DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) throws Exception;

    /**
     * 调度采购入库
     *
     * @param purchaseOrderDTO 采购单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean schedulePurchaseInput(PurchaseOrderDTO purchaseOrderDTO) throws Exception;

    /**
     * 调度销售出库
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean scheduleSaleDelivery(OrderInfoDTO orderDTO) throws Exception;

    /**
     * 调度退货入库
     *
     * @param orderInfo            订单DTO
     * @param returnGoodsWorksheet 退货入库单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean scheduleReturnGoodsInput(OrderInfoDTO orderInfo, ReturnGoodsWorksheetDTO returnGoodsWorksheet) throws Exception;
}
