package com.zpl.eshop.schedule.service.impl;

import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetDTO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderDTO;
import com.zpl.eshop.schedule.service.ScheduleFacadeService;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import org.springframework.stereotype.Service;

/**
 * 调度中心对外接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 13:42
 **/
@Service
public class ScheduleFacadeServiceImpl implements ScheduleFacadeService {
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
     * @param purchaseOrderDTO 采购单DTO
     * @return 处理结果
     */
    @Override
    public Boolean schedulePurchaseInput(PurchaseOrderDTO purchaseOrderDTO) {
        return true;
    }

    /**
     * 调度销售出库
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean scheduleSaleDelivery(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 调度退货入库
     *
     * @param orderInfoDTO            订单DTO
     * @param returnGoodsWorksheetDTO 退货入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean scheduleReturnGoodsInput(OrderInfoDTO orderInfoDTO, ReturnGoodsWorksheetDTO returnGoodsWorksheetDTO) {
        return true;
    }
}
