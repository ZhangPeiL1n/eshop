package com.zpl.eshop.purchase.service;

import com.zpl.eshop.purchase.domain.SupplierDTO;

import java.util.List;

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
     * @throws Exception
     */
    Boolean existRelatedPurchaseOrder(Long goodsSkuId) throws Exception;

    /**
     * 通知采购中心，“创建采购入库单”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informCreatePurchaseInputOrderEvent(Long purchaseOrderId) throws Exception;

    /**
     * 通知采购中心，“完成采购入库”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informFinishedPurchaseInputOrderEvent(Long purchaseOrderId) throws Exception;

    /**
     * 通知采购中心，“创建采购结算单”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informCreatePurchaseSettlementOrderEvent(Long purchaseOrderId) throws Exception;

    /**
     * 通知采购中心，“创建采购结算单”事件发生了
     *
     * @param purchaseOrderId 采购单 id
     * @return 处理结果
     * @throws Exception
     */
    Boolean informFinishedPurchaseSettlementOrderEvent(Long purchaseOrderId) throws Exception;

    /**
     * 根据结算周期查询供应商
     *
     * @param settlementPeriod 结算周期
     * @return 供应商
     * @throws Exception
     */
    List<SupplierDTO> listSuppliersBySettlementPeriod(Integer settlementPeriod) throws Exception;
}
