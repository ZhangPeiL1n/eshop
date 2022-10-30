package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.domain.SaleDeliveryScheduleResult;
import com.zpl.eshop.wms.domain.GoodsAllocationStockDetailDTO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;
import com.zpl.eshop.wms.service.PurchaseInputOrderService;
import com.zpl.eshop.wms.service.ReturnGoodsInputOrderService;
import com.zpl.eshop.wms.service.SaleDeliveryOrderService;
import com.zpl.eshop.wms.service.WmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangPeiL1n
 * @date 2022/5/28 12:39
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class WmsServiceImpl implements WmsService {

    private static final Logger logger = LoggerFactory.getLogger(WmsServiceImpl.class);

    /**
     * 采购入库单管理service组件
     */
    @Autowired
    private PurchaseInputOrderService purchaseInputOrderService;

    /**
     * 销售出库单管理Service组件
     */
    @Autowired
    private SaleDeliveryOrderService saleDeliveryOrderService;

    /**
     * 退货入库单管理service组件
     */
    @Autowired
    private ReturnGoodsInputOrderService returnGoodsInputOrderService;

    /**
     * 创建采购入库单
     *
     * @param purchaseInputOrder 采购入库单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean createPurchaseInputOrder(PurchaseInputOrderDTO purchaseInputOrder) {
        try {
            purchaseInputOrderService.save(purchaseInputOrder);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 创建销售出库单
     *
     * @param saleDeliveryOrder 销售出库单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean createSaleDeliveryOrder(SaleDeliveryOrderDTO saleDeliveryOrder) {
        try {
            saleDeliveryOrderService.save(saleDeliveryOrder);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return true;
    }

    /**
     * 创建退货入库单
     *
     * @param returnGoodsInputOrder 退货入库单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean createReturnGoodsInputOrder(ReturnGoodsInputOrderDTO returnGoodsInputOrder) {
        try {
            returnGoodsInputOrderService.save(returnGoodsInputOrder);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 通知 wms中心，“提交订单“事件发生了
     *
     * @param scheduleResult 调度结果
     * @return 处理结果
     */
    @Override
    public Boolean informSubmitOrderEvent(SaleDeliveryScheduleResult scheduleResult) {
        return true;
    }

    /**
     * 通知 wms中心，“支付订单”事件发生了
     *
     * @param scheduleResult 调度结果
     * @return 处理结果
     */
    @Override
    public Boolean informPayOrderEvent(SaleDeliveryScheduleResult scheduleResult) {
        return true;
    }

    /**
     * 通知 wms中心，“取消订单”事件发生了
     *
     * @param scheduleResult 调度结果
     * @return 处理结果
     */
    @Override
    public Boolean informCancelOrderEvent(SaleDeliveryScheduleResult scheduleResult) {
        return true;
    }


    /**
     * 获取订单对应的物流单号
     *
     * @param orderId 订单id
     * @return 物流单号
     */
    @Override
    public String getLogisticCode(Long orderId) {
        return null;
    }

    /**
     * 通知wms中心，“创建采购结算单”事件发生了
     *
     * @param purchaseInputOrderId 采购入库单id
     * @return 处理结果
     */
    @Override
    public Boolean informCreatePurchaseSettlementOrderEvent(Long purchaseInputOrderId) {
        return true;
    }

    /**
     * 通知wms中心，“完成采购结算单”事件发生了
     *
     * @param purchaseInputOrderId 采购入库单id
     * @return 处理结果
     */
    @Override
    public Boolean informFinishedPurchaseSettlementOrderEvent(Long purchaseInputOrderId) {
        return true;
    }

}
