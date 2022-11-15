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
     * @throws Exception
     */
    void save(PurchaseOrderDTO purchaseOrder) throws Exception;

    /**
     * 分页查询采购单
     *
     * @param query 查询条件
     * @return 采购单
     * @throws Exception
     */
    List<PurchaseOrderDTO> listByPage(PurchaseOrderQuery query) throws Exception;

    /**
     * 根据id查询采购单
     *
     * @param id 采购单id
     *
     * @return 采购单
     * @throws Exception
     */
    PurchaseOrderDTO getById(Long id) throws Exception;

    /**
     * 更新采购单
     *
     * @param purchaseOrder 采购单
     *                      @throws Exception
     */
    void update(PurchaseOrderDTO purchaseOrder) throws Exception;


    /**
     * 采购单提交审核
     *
     * @param id 采购单id
     * @throws Exception
     */
    void submitApprove(Long id) throws Exception;

    /**
     * 审核采购单
     *
     * @param id            采购单id
     * @param approveResult 审核结果
     * @throws Exception
     */
    void approve(Long id, Integer approveResult) throws Exception;

    /**
     * 更新采购单的状态
     *
     * @param id     采购单id
     * @param status 采购单状态
     * @throws Exception
     */
    void updateStatus(Long id, Integer status) throws Exception;
}
