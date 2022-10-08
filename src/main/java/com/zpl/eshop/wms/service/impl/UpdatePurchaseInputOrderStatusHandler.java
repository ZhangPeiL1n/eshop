package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.wms.constant.PurchaseInputOrderStatus;
import com.zpl.eshop.wms.dao.PurchaseInputOrderDAO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 更新采购入库单状态的handler
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 16:30
 **/
@Component
public class UpdatePurchaseInputOrderStatusHandler extends AbstractPurchaseInputOrderHandler {

    /**
     * 采购入库单管理DAO组件
     */
    @Autowired
    private PurchaseInputOrderDAO purchaseInputOrderDAO;

    /**
     * @param purchaseInputOrder
     * @return
     * @throws Exception
     */
    @Override
    public PurchaseInputOrderHandlerResult doExecute(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
        purchaseInputOrderDAO.updateStatus(purchaseInputOrder.getId(), PurchaseInputOrderStatus.FINISH_INPUT);
        return new PurchaseInputOrderHandlerResult(true);
    }
}
