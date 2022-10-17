package com.zpl.eshop.purchase.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.purchase.dao.PurchaseOrderDAO;
import com.zpl.eshop.purchase.domain.PurchaseOrderDO;
import com.zpl.eshop.purchase.domain.PurchaseOrderQuery;
import com.zpl.eshop.purchase.mapper.PurchaseOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购单管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/17 22:15
 **/
@Repository
public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {

    /**
     * 采购单管理mapper组件
     */
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增采购单
     *
     * @param purchaseOrder 采购单
     * @return 采购单id
     */
    @Override
    public Long save(PurchaseOrderDO purchaseOrder) throws Exception {
        purchaseOrder.setGmtCreate(dateProvider.getCurrentTime());
        purchaseOrder.setGmtModified(dateProvider.getCurrentTime());
        purchaseOrderMapper.save(purchaseOrder);
        return purchaseOrder.getId();
    }

    /**
     * 分页查询采购单
     *
     * @param query 查询条件
     * @return 采购单
     */
    @Override
    public List<PurchaseOrderDO> listByPage(PurchaseOrderQuery query) throws Exception {
        return purchaseOrderMapper.listByPage(query);
    }

    /**
     * 根据id查询采购单
     *
     * @param id 采购单id
     * @return 采购单
     */
    @Override
    public PurchaseOrderDO getById(Long id) throws Exception {
        return purchaseOrderMapper.getById(id);
    }
}