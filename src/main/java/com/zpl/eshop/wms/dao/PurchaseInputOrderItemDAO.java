package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDO;

import java.util.List;

/**
 * 采购入库单条目管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 16:59
 **/
public interface PurchaseInputOrderItemDAO {

    /**
     * 批量新增采购入库单条目
     *
     * @param purchaseInputOrderId    采购入库单id
     * @param purchaseInputOrderItems 采购入库单条目
     * @throws Exception
     */
    void batchSave(Long purchaseInputOrderId,
                   List<PurchaseInputOrderItemDO> purchaseInputOrderItems) throws Exception;

    /**
     * 根据采购入库单id查询采购入库单条目
     *
     * @param purchaseInputOrderId 采购入库单id
     * @return 采购入库单条目
     * @throws Exception
     */
    List<PurchaseInputOrderItemDO> listByPurchaseInputOrderId(Long purchaseInputOrderId) throws Exception;

    /**
     * 更新采购入库单条目
     *
     * @param purchaseInputOrderItem 采购入库单条目
     * @throws Exception
     */
    void update(PurchaseInputOrderItemDO purchaseInputOrderItem) throws Exception;
}
