package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.PurchaseInputOrderDAO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderQuery;
import com.zpl.eshop.wms.mapper.PurchaseInputOrderMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购入库单管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 16:49
 **/
@Repository
public class PurchaseInputOrderDAOImpl implements PurchaseInputOrderDAO {

    /**
     * 采购入库单管理Mapper组件
     */
    private PurchaseInputOrderMapper purchaseInputOrderMapper;

    /**
     * 新增采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     */
    @Override
    public Long save(PurchaseInputOrderDO purchaseInputOrder) {
        purchaseInputOrderMapper.save(purchaseInputOrder);
        return purchaseInputOrder.getId();
    }

    /**
     * 分页查询采购入库单
     *
     * @param query 查询条件
     * @return 采购入库单
     */
    @Override
    public List<PurchaseInputOrderDO> listByPage(PurchaseInputOrderQuery query) {
        return purchaseInputOrderMapper.listByPage(query);
    }

    /**
     * 根据id查询采购入库单
     *
     * @param id 采购入库单id
     * @return 采购入库单
     */
    @Override
    public PurchaseInputOrderDO getById(Long id) {
        return purchaseInputOrderMapper.getById(id);
    }

    /**
     * 更新采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     */
    @Override
    public void update(PurchaseInputOrderDO purchaseInputOrder) {
        purchaseInputOrderMapper.update(purchaseInputOrder);
    }

    /**
     * 更新采购入库单状态
     *
     * @param purchaseInputOrder 采购入库单
     */
    @Override
    public void updateStatus(PurchaseInputOrderDO purchaseInputOrder) {
        purchaseInputOrderMapper.updateStatus(purchaseInputOrder);
    }
}
