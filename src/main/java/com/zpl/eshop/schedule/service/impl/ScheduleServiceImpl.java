package com.zpl.eshop.schedule.service.impl;

import com.zpl.eshop.customer.domain.ReturnGoodsWorksheetDTO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderDTO;
import com.zpl.eshop.schedule.service.ScheduleService;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 调度中心对外接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 13:42
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ScheduleServiceImpl implements ScheduleService {
    /**
     * 通知调度中心，“采购入库完成”事件发生了
     *
     * @param purchaseInputOrder 采购入库单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
        return true;
    }

    /**
     * 通知调度中心，“提交订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderInfoDTO order) throws Exception {
        return true;
    }

    /**
     * 通知调度中心，“支付订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informPayOrderEvent(OrderInfoDTO order) throws Exception {
        return true;
    }

    /**
     * 通知调度中心，“取消订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean cancelOrderEvent(OrderInfoDTO order) throws Exception {
        return true;
    }

    /**
     * 通知调度中心，“退货入库”事件发生了
     *
     * @param returnGoodsInputOrderDTO 退货入库DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) throws Exception {
        return true;
    }

    /**
     * 调度采购入库
     *
     * @param purchaseOrderDTO 采购单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean schedulePurchaseInput(PurchaseOrderDTO purchaseOrderDTO) throws Exception {
        return true;
    }

    /**
     * 调度销售出库
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean scheduleSaleDelivery(OrderInfoDTO orderDTO) throws Exception {
        return true;
    }

    /**
     * 调度退货入库
     *
     * @param orderInfo            订单DTO
     * @param returnGoodsWorksheet 退货入库单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean scheduleReturnGoodsInput(OrderInfoDTO orderInfo, ReturnGoodsWorksheetDTO returnGoodsWorksheet) throws Exception {
        return true;
    }
}
