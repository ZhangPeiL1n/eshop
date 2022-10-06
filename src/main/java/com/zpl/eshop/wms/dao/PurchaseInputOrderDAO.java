package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.PurchaseInputOrderDO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderQuery;

import java.util.List;

/**
 * 采购入库单管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 16:48
 **/
public interface PurchaseInputOrderDAO {

    /**
     * 新增采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     */
    Long save(PurchaseInputOrderDO purchaseInputOrder);

    /**
     * 分页查询采购入库单
     *
     * @param query 查询条件
     * @return 采购入库单
     */
    List<PurchaseInputOrderDO> listByPage(PurchaseInputOrderQuery query);

    /**
     * 根据id查询采购入库单
     *
     * @param id 采购入库单id
     * @return 采购入库单
     */
    PurchaseInputOrderDO getById(Long id);

    /**
     * 更新采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     */
    void update(PurchaseInputOrderDO purchaseInputOrder);

    /**
     * 更新采购入库单状态
     *
     * @param purchaseInputOrder 采购入库单
     */
    void updateStatus(PurchaseInputOrderDO purchaseInputOrder);
}
