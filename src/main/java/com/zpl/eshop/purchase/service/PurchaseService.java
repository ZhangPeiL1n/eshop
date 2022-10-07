package com.zpl.eshop.purchase.service;

/**
 * 采购中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 17:25
 **/
public interface PurchaseService {

    /**
     * 判断是否有关联商品 sku 的采购单
     *
     * @param goodsSkuId 商品 sku id
     * @return 是否有采购单关联了商品 sku
     */
    Boolean existRelatedPurchaseOrder(Long goodsSkuId);

    /**
     * 通知采购中心，“创建采购入库单”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     */
    Boolean informCreatePurchaseInputOrderEvent(Long purchaseOrderId);

    /**
     * 通知采购中心，“完成采购入库”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     */
    Boolean informFinishedPurchaseInputOrderEvent(Long purchaseOrderId);

    /**
     * 通知采购中心，“创建采购结算单”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     */
    Boolean informCreatePurchaseSettlementOrderEvent(Long purchaseOrderId);

    /**
     * 通知采购中心，“创建采购结算单”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     */
    Boolean informFinishedPurchaseSettlementOrderEvent(Long purchaseOrderId);
}
