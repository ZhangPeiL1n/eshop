package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.wms.dao.*;
import com.zpl.eshop.wms.domain.*;
import com.zpl.eshop.wms.service.SaleDeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 销售出库单管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:28
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SaleDeliveryOrderServiceImpl implements SaleDeliveryOrderService {

    /**
     * 销售出库单管理DAO组件
     */
    @Autowired
    private SaleDeliveryOrderDAO saleDeliveryOrderDAO;

    /**
     * 销售出库单条目管理DAO组件
     */
    @Autowired
    private SaleDeliveryOrderItemDAO saleDeliveryOrderItemDAO;

    /**
     * 销售出库单拣货条目管理DAO组件
     */
    @Autowired
    private SaleDeliveryOrderPickingItemDAO pickingItemDAO;

    /**
     * 销售出库单发货明细管理DAO组件
     */
    @Autowired
    private SaleDeliveryOrderSendOutDetailDAO sendOutDetailDAO;

    /**
     * 发货单管理DAO组件
     */
    @Autowired
    private SendOutOrderDAO sendOutOrderDAO;

    /**
     * 发货单条目管理DAO组件
     */
    @Autowired
    private SendOutOrderItemDAO sendOutOrderItemDAO;

    /**
     * 物流单管理DAO组件
     */
    @Autowired
    private LogisticOrderDAO logisticOrderDAO;

    /**
     * 新增销售出库单
     *
     * @param saleDeliveryOrder 销售出库单
     * @throws Exception
     */
    @Override
    public void save(SaleDeliveryOrderDTO saleDeliveryOrder) throws Exception {
        // 新增销售出库单
        Long saleDeliveryOrderId = saleDeliveryOrderDAO.save(
                saleDeliveryOrder.clone(SaleDeliveryOrderDO.class));

        // 新增销售出库单条目
        for (SaleDeliveryOrderItemDTO saleDeliveryOrderItem : saleDeliveryOrder.getSaleDeliveryOrderItems()) {
            saleDeliveryOrderItem.setSaleDeliveryOrderId(saleDeliveryOrderId);
            Long saleDeliveryOrderItemId = saleDeliveryOrderItemDAO.save(
                    saleDeliveryOrderItem.clone(SaleDeliveryOrderItemDO.class));

            // 新增销售出库单拣货条目
            for (SaleDeliveryOrderPickingItemDTO pickingItem : saleDeliveryOrderItem.getPickingItems()) {
                pickingItem.setSaleDeliveryOrderItemId(saleDeliveryOrderItemId);
                pickingItemDAO.save(pickingItem.clone(SaleDeliveryOrderPickingItemDO.class));
            }

            // 新增销售出库单发货明细
            for (SaleDeliveryOrderSendOutDetailDTO sendOutDetail : saleDeliveryOrderItem.getSendOutItems()) {
                sendOutDetail.setSaleDeliveryOrderItemId(saleDeliveryOrderItemId);
                sendOutDetailDAO.save(sendOutDetail.clone(SaleDeliveryOrderSendOutDetailDO.class));
            }
        }

        // 新增发货单
        SendOutOrderDTO sendOutOrder = saleDeliveryOrder.getSendOutOrder();
        sendOutOrder.setSaleDeliveryOrderId(saleDeliveryOrderId);
        Long sendOutOrderId = sendOutOrderDAO.save(sendOutOrder.clone(SendOutOrderDO.class));

        // 新增发货单条目
        for (SendOutOrderItemDTO sendOutOrderItem : sendOutOrder.getSendOutOrderItems()) {
            sendOutOrderItem.setSendOutOrderId(sendOutOrderId);
            sendOutOrderItemDAO.save(sendOutOrderItem.clone(SendOutOrderItemDO.class));
        }

        // 新增物流单
        LogisticOrderDTO logisticOrder = saleDeliveryOrder.getLogisticOrder();
        logisticOrder.setSaleDeliveryOrderId(saleDeliveryOrderId);
        logisticOrderDAO.save(logisticOrder.clone(LogisticOrderDO.class));
    }

    /**
     * 分页查询销售出库单
     *
     * @param query 查询条件
     * @return 销售出库单
     */
    @Override
    public List<SaleDeliveryOrderDTO> listByPage(SaleDeliveryOrderQuery query) throws Exception {
        return ObjectUtils.convertList(
                saleDeliveryOrderDAO.listByPage(query),
                SaleDeliveryOrderDTO.class);
    }
}