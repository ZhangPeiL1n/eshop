package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.wms.dao.PurchaseInputOrderDAO;
import com.zpl.eshop.wms.dao.PurchaseInputOrderItemDAO;
import com.zpl.eshop.wms.dao.PurchaseInputOrderPutOnItemDAO;
import com.zpl.eshop.wms.domain.*;
import com.zpl.eshop.wms.service.PurchaseInputOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购入库单管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 16:54
 **/
@Service
public class PurchaseInputOrderServiceImpl implements PurchaseInputOrderService {

    /**
     * 采购入库单管理DAO组件
     */
    @Autowired
    private PurchaseInputOrderDAO purchaseInputOrderDAO;
    /**
     * 采购入库单条目管理DAO组件
     */
    @Autowired
    private PurchaseInputOrderItemDAO purchaseInputOrderItemDAO;
    /**
     * 采购入库单上架条目管理的DAO组件
     */
    @Autowired
    private PurchaseInputOrderPutOnItemDAO purchaseInputOrderPutOnItemDAO;

    /**
     * 新增采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     */
    @Override
    public void save(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
        Long purchaseInputOrderId = purchaseInputOrderDAO.save(
                purchaseInputOrder.clone(PurchaseInputOrderDO.class));

        List<PurchaseInputOrderItemDO> purchaseInputOrderItems = ObjectUtils.convertList(
                purchaseInputOrder.getItems(), PurchaseInputOrderItemDO.class);

        purchaseInputOrderItemDAO.batchSave(purchaseInputOrderId, purchaseInputOrderItems);
    }

    /**
     * 分页查询采购入库单
     *
     * @return 采购入库单
     * @throws Exception
     */
    @Override
    public List<PurchaseInputOrderDTO> listByPage(PurchaseInputOrderQuery query) throws Exception {
        return ObjectUtils.convertList(
                purchaseInputOrderDAO.listByPage(query),
                PurchaseInputOrderDTO.class);
    }

    /**
     * 根据id查询采购入库单
     *
     * @return 采购入库单
     * @throws Exception
     */
    @Override
    public PurchaseInputOrderDTO getById(Long id) throws Exception {
        PurchaseInputOrderDTO purchaseInputOrder = purchaseInputOrderDAO.getById(id)
                .clone(PurchaseInputOrderDTO.class);

        List<PurchaseInputOrderItemDTO> purchaseInputOrderItems = ObjectUtils.convertList(
                purchaseInputOrderItemDAO.listByPurchaseInputOrderId(id),
                PurchaseInputOrderItemDTO.class);
        purchaseInputOrder.setItems(purchaseInputOrderItems);

        for (PurchaseInputOrderItemDTO purchaseInputOrderItem : purchaseInputOrderItems) {
            List<PurchaseInputOrderPutOnItemDTO> putOnItems = ObjectUtils.convertList(
                    purchaseInputOrderPutOnItemDAO.listByPurchaseInputOrderItemId(purchaseInputOrderItem.getId()),
                    PurchaseInputOrderPutOnItemDTO.class);
            purchaseInputOrderItem.setPutOnItems(putOnItems);
        }

        return purchaseInputOrder;
    }

    /**
     * 更新采购入库单
     *
     * @param purchaseInputOrder 采购入库单
     * @throws Exception
     */
    @Override
    public void update(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
        purchaseInputOrderDAO.update(purchaseInputOrder.clone(PurchaseInputOrderDO.class));
        purchaseInputOrderDAO.updateStatus(purchaseInputOrder.clone(PurchaseInputOrderDO.class));

        for (PurchaseInputOrderItemDTO purchaseInputOrderItem : purchaseInputOrder.getItems()) {
            purchaseInputOrderItemDAO.update(purchaseInputOrderItem.clone(PurchaseInputOrderItemDO.class));
        }
    }

    /**
     * 批量新增采购入库单的上架条目
     *
     * @param putOnItems 上架条目
     * @throws Exception
     */
    @Override
    public void batchSavePutOnItems(List<PurchaseInputOrderPutOnItemDTO> putOnItems) throws Exception {
        purchaseInputOrderPutOnItemDAO.batchSave(ObjectUtils.convertList(
                putOnItems, PurchaseInputOrderPutOnItemDO.class));
    }
}
