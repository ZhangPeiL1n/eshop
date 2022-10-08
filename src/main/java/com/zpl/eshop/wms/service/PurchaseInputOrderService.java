package com.zpl.eshop.wms.service;

import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderPutOnItemDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderQuery;

import java.util.List;

/**
 * 采购入库单管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 16:53
 **/
public interface PurchaseInputOrderService {

    /**
     * 新增采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     */
    void save(PurchaseInputOrderDTO purchaseInputOrder) throws Exception;

    /**
     * 分页查询采购入库单
     *
     * @return 采购入库单
     * @throws Exception
     */
    List<PurchaseInputOrderDTO> listByPage(PurchaseInputOrderQuery query) throws Exception;

    /**
     * 根据id查询采购入库单
     *
     * @return 采购入库单
     * @throws Exception
     */
    PurchaseInputOrderDTO getById(Long id) throws Exception;

    /**
     * 更新采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     * @throws Exception
     */
    void update(PurchaseInputOrderDTO purchaseInputOrder) throws Exception;

    /**
     * 批量新增采购入库单的上架条目
     *
     * @param putOnItems 上架条目
     * @throws Exception
     */
    void batchSavePutOnItems(List<PurchaseInputOrderPutOnItemDTO> putOnItems) throws Exception;

    /**
     * 对采购入库单提交审核
     *
     * @param id 采购入库单id
     * @throws Exception
     */
    void submitApprove(Long id) throws Exception;

    /**
     * 审核采购入库单
     *
     * @param id            采购入库单id
     * @param approveResult 审核结果
     * @throws Exception
     */
    void approve(Long id, Integer approveResult) throws Exception;

}
