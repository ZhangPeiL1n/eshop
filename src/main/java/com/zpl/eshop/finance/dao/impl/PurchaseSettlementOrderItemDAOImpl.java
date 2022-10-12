package com.zpl.eshop.finance.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.finance.dao.PurchaseSettlementOrderItemDAO;
import com.zpl.eshop.finance.domain.PurchaseSettlementOrderItemDO;
import com.zpl.eshop.finance.mapper.PurchaseSettlementOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购结算单条目管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/12 21:19
 **/
@Repository
public class PurchaseSettlementOrderItemDAOImpl implements PurchaseSettlementOrderItemDAO {

    /**
     * 采购结算单条目管理mapper组件
     */
    @Autowired
    private PurchaseSettlementOrderItemMapper purchaseSettlementOrderItemMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 批量新增采购结算单条目
     *
     * @param purchaseSettlementOrderId    采购结算单id
     * @param purchaseSettlementOrderItems 采购结算单条目
     * @throws Exception
     */
    @Override
    public void batchSave(Long purchaseSettlementOrderId,
                          List<PurchaseSettlementOrderItemDO> purchaseSettlementOrderItems) throws Exception {
        for (PurchaseSettlementOrderItemDO purchaseSettlementOrderItem : purchaseSettlementOrderItems) {
            purchaseSettlementOrderItem.setGmtCreate(dateProvider.getCurrentTime());
            purchaseSettlementOrderItem.setGmtModified(dateProvider.getCurrentTime());
            purchaseSettlementOrderItem.setPurchaseSettlementOrderId(purchaseSettlementOrderId);
            purchaseSettlementOrderItemMapper.save(purchaseSettlementOrderItem);
        }
    }

    /**
     * 根据采购结算单id查询采购结算单条目
     *
     * @param purchaseSettlementOrderId 采购结算单id
     * @return 采购结算单条目
     */
    @Override
    public List<PurchaseSettlementOrderItemDO> listByPurchaseSettlementOrderId(
            Long purchaseSettlementOrderId) throws Exception {
        return purchaseSettlementOrderItemMapper.listByPurchaseSettlementOrderId(
                purchaseSettlementOrderId);
    }
}
