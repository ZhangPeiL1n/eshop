package com.zpl.eshop.purchase.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.purchase.dao.PurchaseOrderDAO;
import com.zpl.eshop.purchase.dao.PurchaseOrderItemDAO;
import com.zpl.eshop.purchase.domain.*;
import com.zpl.eshop.purchase.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购单管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/17 22:34
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    /**
     * 采购单管理DAO组件
     */
    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;

    /**
     * 采购单条目管理DAO组件
     */
    @Autowired
    private PurchaseOrderItemDAO purchaseOrderItemDAO;

    /**
     * 新增采购单
     *
     * @param purchaseOrder 采购单
     */
    @Override
    public void save(PurchaseOrderDTO purchaseOrder) throws Exception {
        Long purchaseOrderId = purchaseOrderDAO.save(
                purchaseOrder.clone(PurchaseOrderDO.class));

        List<PurchaseOrderItemDO> purchaseOrderItems = ObjectUtils.convertList(
                purchaseOrder.getItems(), PurchaseOrderItemDO.class);
        purchaseOrderItemDAO.batchSave(purchaseOrderId, purchaseOrderItems);
    }

    /**
     * 分页查询采购单
     *
     * @return 采购单
     * @throws Exception
     */
    public List<PurchaseOrderDTO> listByPage(
            PurchaseOrderQuery query) throws Exception {
        return ObjectUtils.convertList(
                purchaseOrderDAO.listByPage(query),
                PurchaseOrderDTO.class);
    }

    /**
     * 根据id查询采购单
     *
     * @return 采购单
     * @throws Exception
     */
    @Override
    public PurchaseOrderDTO getById(Long id) throws Exception {
        PurchaseOrderDTO purchaseOrder = purchaseOrderDAO.getById(id)
                .clone(PurchaseOrderDTO.class);

        List<PurchaseOrderItemDTO> purchaseOrderItems = ObjectUtils.convertList(
                purchaseOrderItemDAO.listByPurchaseOrderId(id),
                PurchaseOrderItemDTO.class);
        purchaseOrder.setItems(purchaseOrderItems);

        return purchaseOrder;
    }
}
