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

    /**
     * 根据结算周期查询供应商
     *
     * @param settlementPeriod 结算周期
     * @return 供应商
     */
    List<SupplierDTO> listSuppliersBySettlementPeriod(Integer settlementPeriod);
}
