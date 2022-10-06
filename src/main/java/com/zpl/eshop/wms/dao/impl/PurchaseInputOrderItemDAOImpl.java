package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.PurchaseInputOrderItemDAO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDO;
import com.zpl.eshop.wms.mapper.PurchaseInputOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购入库单条目管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 17:00
 **/
@Repository
public class PurchaseInputOrderItemDAOImpl implements PurchaseInputOrderItemDAO {

    /**
     * 采购入库单条目管理Mapper组件
     */
    @Autowired
    private PurchaseInputOrderItemMapper purchaseInputOrderItemMapper;

    /**
     * 批量新增采购入库单条目
     *
     * @param purchaseInputOrderId    采购入库单id
     * @param purchaseInputOrderItems 采购入库单条目
     * @throws Exception
     */
    @Override
    public void batchSave(Long purchaseInputOrderId,
                          List<PurchaseInputOrderItemDO> purchaseInputOrderItems) throws Exception {
        purchaseInputOrderItems.forEach(item -> {
            item.setPurchaseInputOrderId(purchaseInputOrderId);
            purchaseInputOrderItemMapper.save(item);
        });
    }

    /**
     * 根据采购入库单id查询采购入库单条目
     *
     * @param purchaseInputOrderId 采购入库单id
     * @return 采购入库单条目
     */
    @Override
    public List<PurchaseInputOrderItemDO> listByPurchaseInputOrderId(Long purchaseInputOrderId) {
        return purchaseInputOrderItemMapper.listByPurchaseInputOrderId(purchaseInputOrderId);
    }

    /**
     * 更新采购入库单条目
     *
     * @param purchaseInputOrderItem 采购入库单条目
     */
    @Override
    public void update(PurchaseInputOrderItemDO purchaseInputOrderItem) {
        purchaseInputOrderItemMapper.update(purchaseInputOrderItem);
    }
}
