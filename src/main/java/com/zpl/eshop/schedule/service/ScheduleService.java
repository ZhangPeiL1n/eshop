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
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrderDTO);

    /**
     * 通知调度中心，“提交订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informSubmitOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知调度中心，“支付订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informPayOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知调度中心，“取消订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean cancelOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知调度中心，“退货入库”事件发生了
     *
     * @param returnGoodsInputOrderDTO 退货入库DTO
     * @return 处理结果
     */
    Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO);

    /**
     * 调度采购入库
     *
     * @param purchaseOrderDTO 采购单DTO
     * @return 处理结果
     */
    Boolean schedulePurchaseInput(PurchaseOrderDTO purchaseOrderDTO);

    /**
     * 调度销售出库
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean scheduleSaleDelivery(OrderInfoDTO orderDTO);

    /**
     * 调度退货入库
     *
     * @param orderInfoDTO            订单DTO
     * @param returnGoodsWorksheetDTO 退货入库单DTO
     * @return 处理结果
     */
    Boolean scheduleReturnGoodsInput(OrderInfoDTO orderInfoDTO, ReturnGoodsWorksheetDTO returnGoodsWorksheetDTO);
}
