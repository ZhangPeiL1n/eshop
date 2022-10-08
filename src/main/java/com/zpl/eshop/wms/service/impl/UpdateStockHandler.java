package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.wms.constant.WmsStockUpdateEvent;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.stock.WmsStockUpdater;
import com.zpl.eshop.wms.stock.WmsStockUpdaterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 更新自己本地库存的handler
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 16:54
 **/
@Component
public class UpdateStockHandler extends AbstractPurchaseInputOrderHandler {

    /**
     * 库存更新组件工厂
     */
    @Autowired
    private WmsStockUpdaterFactory stockUpdaterFactory;

    /**
     * 执行处理逻辑
     */
    @Override
    public PurchaseInputOrderHandlerResult doExecute(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
        WmsStockUpdater stockUpdater = stockUpdaterFactory.create(
                WmsStockUpdateEvent.PURCHASE_INPUT, purchaseInputOrder);
        stockUpdater.update();
        return new PurchaseInputOrderHandlerResult(true);
    }
}
