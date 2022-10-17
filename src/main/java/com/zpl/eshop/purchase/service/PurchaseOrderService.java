package com.zpl.eshop.purchase.service;

import com.zpl.eshop.purchase.domain.PurchaseOrderDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderQuery;

import java.util.List;

/**
 * 采购单管理Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/17 22:33
 **/
public interface PurchaseOrderService {

    /**
     * 新增采购单
     *
     * @param purchaseOrder 采购单
     */
    void save(PurchaseOrderDTO purchaseOrder) throws Exception;

    /**
     * 分页查询采购单
     *
     * @return 采购单
     * @throws Exception
     */
    List<PurchaseOrderDTO> listByPage(PurchaseOrderQuery query) throws Exception;

    /**
     * 根据id查询采购单
     *
     * @return 采购单
     * @throws Exception
     */
    PurchaseOrderDTO getById(Long id) throws Exception;
}
