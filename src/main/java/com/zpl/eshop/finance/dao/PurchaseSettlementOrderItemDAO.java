package com.zpl.eshop.finance.dao;

import com.zpl.eshop.finance.domain.PurchaseSettlementOrderItemDO;

import java.util.List;

/**
 * 采购结算单条目管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/12 21:18
 **/
public interface PurchaseSettlementOrderItemDAO {

    /**
     * 批量新增采购结算单条目
     *
     * @param purchaseSettlementOrderId    采购结算单id
     * @param purchaseSettlementOrderItems 采购结算单条目
     * @throws Exception
     */
    void batchSave(Long purchaseSettlementOrderId,
                   List<PurchaseSettlementOrderItemDO> purchaseSettlementOrderItems) throws Exception;

    /**
     * 根据采购结算单id查询采购结算单条目
     *
     * @param purchaseSettlementOrderId 采购结算单id
     * @return 采购结算单条目
     */
    List<PurchaseSettlementOrderItemDO> listByPurchaseSettlementOrderId(
            Long purchaseSettlementOrderId) throws Exception;
}
