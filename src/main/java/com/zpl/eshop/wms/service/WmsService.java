package com.zpl.eshop.wms.service;

import com.zpl.eshop.schedule.domain.SaleDeliveryScheduleResult;
import com.zpl.eshop.wms.domain.GoodsAllocationStockDetailDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;

import java.util.List;

/**
 * wms中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 17:41
 **/
public interface WmsService {


    /**
     * 创建采购入库单
     *
     * @param purchaseInputOrder 采购入库单 DTO
     * @return 处理结果
     */
    Boolean createPurchaseInputOrder(PurchaseInputOrderDTO purchaseInputOrder);

    /**
     * 创建销售出库单
     *
     * @param saleDeliveryOrder 销售出库单 DTO
     * @return 处理结果
     */
    Boolean createSaleDeliveryOrder(SaleDeliveryOrderDTO saleDeliveryOrder);

    /**
     * 创建退货入库单
     *
     * @param returnGoodsInputOrder 退货入库单 DTO
     * @return 处理结果
     */
    Boolean createReturnGoodsInputOrder(ReturnGoodsInputOrderDTO returnGoodsInputOrder);

    /**
     * 通知 wms中心，“提交订单“事件发生了
     *
     * @param scheduleResult 调度结果
     * @return 处理结果
     */
    Boolean informSubmitOrderEvent(SaleDeliveryScheduleResult scheduleResult);

    /**
     * 通知 wms中心，“支付订单”事件发生了
     *
     * @param scheduleResult 调度结果
     * @return 处理结果
     */
    Boolean informPayOrderEvent(SaleDeliveryScheduleResult scheduleResult);

    /**
     * 通知 wms中心，“取消订单”事件发生了
     *
     * @param scheduleResult 调度结果
     * @return 处理结果
     */
    Boolean informCancelOrderEvent(SaleDeliveryScheduleResult scheduleResult);

    /**
     * 根据商品 skuId 查询货位库存明细
     *
     * @param goodsSkuId 商品sku
     * @return 货位库存明细
     */
    List<GoodsAllocationStockDetailDTO> listStockDetailsByGoodsSkuId(Long goodsSkuId) throws Exception;

    /**
     * 获取订单对应的物流单号
     *
     * @param orderId 订单id
     * @return 物流单号
     */
    String getLogisticCode(Long orderId);

    /**
     * 通知wms中心，“创建采购结算单”事件发生了
     *
     * @param purchaseInputOrderId 采购入库单id
     * @return 处理结果
     */
    Boolean informCreatePurchaseSettlementOrderEvent(Long purchaseInputOrderId);

    /**
     * 通知wms中心，“完成采购结算单”事件发生了
     *
     * @param purchaseInputOrderId 采购入库单id
     * @return 处理结果
     */
    Boolean informFinishedPurchaseSettlementOrderEvent(Long purchaseInputOrderId);

}
