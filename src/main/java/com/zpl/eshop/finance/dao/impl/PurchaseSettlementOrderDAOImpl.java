package com.zpl.eshop.finance.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.finance.dao.PurchaseSettlementOrderDAO;
import com.zpl.eshop.finance.domain.PurchaseSettlementOrderDO;
import com.zpl.eshop.finance.domain.PurchaseSettlementOrderQuery;
import com.zpl.eshop.finance.mapper.PurchaseSettlementOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购结算单管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/12 21:20
 **/
@Repository
public class PurchaseSettlementOrderDAOImpl implements PurchaseSettlementOrderDAO {

    /**
     * 采购结算单管理Mapper
     */
    @Autowired
    private PurchaseSettlementOrderMapper purchaseSettlementOrderMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增采购结算单
     *
     * @param purchaseSettlementOrder 采购结算单
     */
    @Override
    public Long save(PurchaseSettlementOrderDO purchaseSettlementOrder) throws Exception {
        purchaseSettlementOrder.setGmtCreate(dateProvider.getCurrentTime());
        purchaseSettlementOrder.setGmtModified(dateProvider.getCurrentTime());
        purchaseSettlementOrderMapper.save(purchaseSettlementOrder);
        return purchaseSettlementOrder.getId();
    }

    /**
     * 分页查询采购结算单
     *
     * @param query 查询条件
     * @return 采购结算单
     */
    @Override
    public List<PurchaseSettlementOrderDO> listByPage(PurchaseSettlementOrderQuery query) {
        return purchaseSettlementOrderMapper.listByPage(query);
    }

    /**
     * 根据id查询采购结算单
     *
     * @param id 采购结算单id
     * @return 采购结算单
     */
    @Override
    public PurchaseSettlementOrderDO getById(Long id) {
        return purchaseSettlementOrderMapper.getById(id);
    }

    /**
     * 更新采购结算单
     *
     * @param purchaseSettlementOrder 采购结算单
     */
    @Override
    public void update(PurchaseSettlementOrderDO purchaseSettlementOrder) throws Exception {
        purchaseSettlementOrder.setGmtModified(dateProvider.getCurrentTime());
        purchaseSettlementOrderMapper.update(purchaseSettlementOrder);
    }

    /**
     * 更新采购结算单状态
     *
     * @param purchaseSettlementOrder 采购结算单
     */
    @Override
    public void updateStatus(PurchaseSettlementOrderDO purchaseSettlementOrder) throws Exception {
        purchaseSettlementOrder.setGmtModified(dateProvider.getCurrentTime());
        purchaseSettlementOrderMapper.updateStatus(purchaseSettlementOrder);
    }

    /**
     * 更新采购结算单状态
     *
     * @param id     采购结算单id
     * @param status 采购结算单状态
     * @throws Exception
     */
    @Override
    public void updateStatus(Long id, Integer status) throws Exception {
        PurchaseSettlementOrderDO purchaseSettlementOrder = getById(id);
        purchaseSettlementOrder.setStatus(status);
        purchaseSettlementOrder.setGmtModified(dateProvider.getCurrentTime());
        purchaseSettlementOrderMapper.updateStatus(purchaseSettlementOrder);
    }
}
