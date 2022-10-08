package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.finance.service.FinanceService;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/8 18:44
 **/
@Component
public class InformFinanceCenterHandler extends AbstractPurchaseInputOrderHandler {

    /**
     * 财务中心接口
     */
    @Autowired
    private FinanceService financeService;

    /**
     * 执行处理结果
     */
    @Override
    public PurchaseInputOrderHandlerResult doExecute(
            PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
        financeService.createPurchaseSettlementOrder(purchaseInputOrder);
        return new PurchaseInputOrderHandlerResult(true);
    }
}
