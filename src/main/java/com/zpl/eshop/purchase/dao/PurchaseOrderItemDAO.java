package com.zpl.eshop.purchase.dao;

import com.zpl.eshop.purchase.domain.PurchaseOrderItemDO;

import java.util.List;

/**
 * 采购单条目管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/17 22:28
 **/
public interface PurchaseOrderItemDAO {

    /**
     * 批量新增采购单条目
     *
     * @param purchaseOrderId    采购单id
     * @param purchaseOrderItems 采购单条目
     * @throws Exception
     */
    void batchSave(Long purchaseOrderId, List<PurchaseOrderItemDO> purchaseOrderItems) throws Exception;

    /**
     * 根据采购单id查询采购单条目
     *
     * @param purchaseOrderId 采购单id
     * @return 采购单条目
     */
    List<PurchaseOrderItemDO> listByPurchaseOrderId(Long purchaseOrderId);
}
