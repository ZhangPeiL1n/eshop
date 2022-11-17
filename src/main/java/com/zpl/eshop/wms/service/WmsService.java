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
     * @throws Exception
     */
    Boolean createPurchaseInputOrder(PurchaseInputOrderDTO purchaseInputOrder) throws Exception;

    /**
     * 创建销售出库单
     *
     * @param saleDeliveryOrder 销售出库单 DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean createSaleDeliveryOrder(SaleDeliveryOrderDTO saleDeliveryOrder) throws Exception;

    /**
     * 创建退货入库单
     *
     * @param returnGoodsInputOrder 退货入库单 DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean createReturnGoodsInputOrder(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception;

    /**
     * 通知 wms中心，“提交订单“事件发生了
     *
     * @param scheduleResult 调度结果
     * @return 处理结果
     * @throws Exception
     */
    Boolean informSubmitOrderEvent(SaleDeliveryScheduleResult scheduleResult) throws Exception;

    /**
     * 通知 wms中心，“支付订单”事件发生了
     *
     * @param scheduleResult 调度结果
     * @return 处理结果
     * @throws Exception
     */
    Boolean informPayOrderEvent(SaleDeliveryScheduleResult scheduleResult) throws Exception;

    /**
     * 通知 wms中心，“取消订单”事件发生了
     *
     * @param scheduleResult 调度结果
     * @return 处理结果
     * @throws Exception
     */
    Boolean informCancelOrderEvent(SaleDeliveryScheduleResult scheduleResult) throws Exception;

    /**
     * 根据商品 skuId 查询货位库存明细
     *
     * @param goodsSkuId 商品sku
     * @return 货位库存明细
     * @throws Exception
     */
    List<GoodsAllocationStockDetailDTO> listStockDetailsByGoodsSkuId(Long goodsSkuId) throws Exception;

    /**
     * 获取订单对应的物流单号
     *
     * @param orderId 订单id
     * @return 物流单号
     * @throws Exception
     */
    String getLogisticCode(Long orderId) throws Exception;

    /**
     * 通知wms中心，“创建采购结算单”事件发生了
     *
     * @param purchaseInputOrderId 采购入库单id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informCreatePurchaseSettlementOrderEvent(Long purchaseInputOrderId) throws Exception;

    /**
     * 通知wms中心，“完成采购结算单”事件发生了
     *
     * @param purchaseInputOrderId 采购入库单id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informFinishedPurchaseSettlementOrderEvent(Long purchaseInputOrderId) throws Exception;

}
