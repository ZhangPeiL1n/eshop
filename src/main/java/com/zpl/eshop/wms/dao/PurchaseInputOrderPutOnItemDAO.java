package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.PurchaseInputOrderPutOnItemDO;

import java.util.List;

/**
 * 采购入库单上架条目DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 17:29
 **/
public interface PurchaseInputOrderPutOnItemDAO {

    /**
     * 批量新增采购入库单上架条目
     *
     * @param putOnItems 上架条目
     * @throws Exception
     */
    void batchSave(List<PurchaseInputOrderPutOnItemDO> putOnItems) throws Exception;

    /**
     * 根据采购入库单条目id查询采购入库单上架条目
     *
     * @param purchaseInputOrderItemId 采购入库单id
     * @return 采购入库单上架条目
     * @throws Exception
     */
    List<PurchaseInputOrderPutOnItemDO> listByPurchaseInputOrderItemId(Long purchaseInputOrderItemId) throws Exception;
}
