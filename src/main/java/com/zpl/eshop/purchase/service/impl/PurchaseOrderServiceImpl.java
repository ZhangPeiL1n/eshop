package com.zpl.eshop.purchase.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.purchase.constant.PurchaseOrderApproveResult;
import com.zpl.eshop.purchase.constant.PurchaseOrderStatus;
import com.zpl.eshop.purchase.dao.PurchaseOrderDAO;
import com.zpl.eshop.purchase.dao.PurchaseOrderItemDAO;
import com.zpl.eshop.purchase.domain.*;
import com.zpl.eshop.purchase.service.PurchaseOrderService;
import com.zpl.eshop.schedule.service.ScheduleService;
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
     * 调度中心接口
     */
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 新增采购单
     *
     * @param purchaseOrder 采购单
     */
    @Override
    public void save(PurchaseOrderDTO purchaseOrder) throws Exception {
        Long purchaseOrderId = purchaseOrderDAO.save(purchaseOrder.clone(PurchaseOrderDO.class));
        purchaseOrder.setId(purchaseOrderId);
        batchSavePurchaseOrderItems(purchaseOrder);
    }

    /**
     * 分页查询采购单
     *
     * @return 采购单
     * @throws Exception
     */
    @Override
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

    /**
     * 更新采购单
     *
     * @param purchaseOrder 采购单
     */
    @Override
    public void update(PurchaseOrderDTO purchaseOrder) throws Exception {
        purchaseOrderDAO.update(purchaseOrder.clone(PurchaseOrderDO.class));

        purchaseOrderItemDAO.removeByPurchaseOrderId(purchaseOrder.getId());
        batchSavePurchaseOrderItems(purchaseOrder);
    }

    /**
     * 批量新增采购单条目
     *
     * @param purchaseOrder
     * @throws Exception
     */
    private void batchSavePurchaseOrderItems(PurchaseOrderDTO purchaseOrder) throws Exception {
        List<PurchaseOrderItemDO> purchaseOrderItems = ObjectUtils.convertList(purchaseOrder.getItems(), PurchaseOrderItemDO.class);
        purchaseOrderItemDAO.batchSave(purchaseOrder.getId(), purchaseOrderItems);
    }

    /**
     * 提交审核
     *
     * @param id 采购单id
     * @throws Exception
     */
    @Override
    public void submitApprove(Long id) throws Exception {
        purchaseOrderDAO.updateStatus(id, PurchaseOrderStatus.WAIT_FOR_APPROVE);
    }

    /**
     * 审核采购单
     *
     * @param id            采购单id
     * @param approveResult 审核结果
     * @throws Exception
     */
    @Override
    public void approve(Long id, Integer approveResult) throws Exception {
        if (PurchaseOrderApproveResult.REJECTED.equals(approveResult)) {
            purchaseOrderDAO.updateStatus(id, PurchaseOrderStatus.EDITING);
            return;
        }
        purchaseOrderDAO.updateStatus(id, PurchaseOrderStatus.APPROVED);
        scheduleService.schedulePurchaseInput(getById(id));
    }

    /**
     * 更新采购单的状态
     *
     * @param id     采购单id
     * @param status 采购单状态
     * @throws Exception
     */
    @Override
    public void updateStatus(Long id, Integer status) throws Exception {
        purchaseOrderDAO.updateStatus(id, status);
    }
}