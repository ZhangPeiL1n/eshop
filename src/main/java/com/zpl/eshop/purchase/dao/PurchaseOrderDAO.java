package com.zpl.eshop.purchase.dao;

import com.zpl.eshop.purchase.domain.PurchaseOrderDO;
import com.zpl.eshop.purchase.domain.PurchaseOrderQuery;

import java.util.List;

/**
 * 采购单管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/17 22:14
 **/
public interface PurchaseOrderDAO {

    /**
     * 新增采购单
     *
     * @param purchaseOrder 采购单
     * @return
     */
    Long save(PurchaseOrderDO purchaseOrder) throws Exception;

    /**
     * 分页查询采购单
     *
     * @param query 查询条件
     * @return 采购单
     */
    List<PurchaseOrderDO> listByPage(PurchaseOrderQuery query) throws Exception;

    /**
     * 根据id查询采购单
     *
     * @param id 采购单id
     * @return 采购单
     */
    PurchaseOrderDO getById(Long id) throws Exception;
}