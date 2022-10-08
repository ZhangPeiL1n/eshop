package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.purchase.service.PurchaseService;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通知采购中心的Handler
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 16:49
 **/
@Component
public class InformPurchaseCenterHandler extends AbstractPurchaseInputOrderHandler {

    @Autowired
    private PurchaseService purchaseService;


    @Override
    public PurchaseInputOrderHandlerResult doExecute(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
        purchaseService.informFinishedPurchaseInputOrderEvent(purchaseInputOrder.getPurchaseOrderId());
        return new PurchaseInputOrderHandlerResult(true);
    }
}
