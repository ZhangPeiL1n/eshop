package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.dao.PurchaseInputOrderDAO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderQuery;
import com.zpl.eshop.wms.mapper.PurchaseInputOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PurchaseInputOrderMapper purchaseInputOrderMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     * @throws Exception
     */
    @Override
    public Long save(PurchaseInputOrderDO purchaseInputOrder) throws Exception {
        purchaseInputOrder.setGmtCreate(dateProvider.getCurrentTime());
        purchaseInputOrder.setGmtModified(dateProvider.getCurrentTime());
        purchaseInputOrderMapper.save(purchaseInputOrder);
        return purchaseInputOrder.getId();
    }

    /**
     * 分页查询采购入库单
     *
     * @param query 查询条件
     * @return 采购入库单
     * @throws Exception
     */
    @Override
    public List<PurchaseInputOrderDO> listByPage(PurchaseInputOrderQuery query) throws Exception {
        return purchaseInputOrderMapper.listByPage(query);
    }

    /**
     * 根据id查询采购入库单
     *
     * @param id 采购入库单id
     * @return 采购入库单
     * @throws Exception
     */
    @Override
    public PurchaseInputOrderDO getById(Long id) throws Exception {
        return purchaseInputOrderMapper.getById(id);
    }

    /**
     * 更新采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     * @throws Exception
     */
    @Override
    public void update(PurchaseInputOrderDO purchaseInputOrder) throws Exception {
        purchaseInputOrder.setGmtModified(dateProvider.getCurrentTime());
        purchaseInputOrderMapper.update(purchaseInputOrder);
    }

    /**
     * 更新采购入库单状态
     *
     * @param purchaseInputOrder 采购入库单
     * @throws Exception
     */
    @Override
    public void updateStatus(PurchaseInputOrderDO purchaseInputOrder) throws Exception {
        purchaseInputOrder.setGmtModified(dateProvider.getCurrentTime());
        purchaseInputOrderMapper.updateStatus(purchaseInputOrder);
    }

    /**
     * 更新采购入库单状态
     *
     * @param id     采购入库单id
     * @param status 采购入库单状态
     * @throws Exception
     */
    @Override
    public void updateStatus(Long id, Integer status) throws Exception {
        PurchaseInputOrderDO purchaseInputOrder = getById(id);
        purchaseInputOrder.setStatus(status);
        updateStatus(purchaseInputOrder);
    }
}
