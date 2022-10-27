package com.zpl.eshop.wms.stock;

import com.zpl.eshop.common.bean.SpringApplicationContext;
import com.zpl.eshop.wms.constant.WmsStockUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/7 19:48
 **/
@Component
public class WmsStockUpdaterFactory {

    /**
     * spring容器
     */
    @Autowired
    private SpringApplicationContext context;

    /**
     * 创建一个库存更新命令
     *
     * @param parameter 参数对象
     * @return 库存更新命令
     */
    public WmsStockUpdater create(Integer stockUpdateEvent, Object parameter) {
        WmsStockUpdater stockUpdater = null;

        if (WmsStockUpdateEvent.PURCHASE_INPUT.equals(stockUpdateEvent)) {
            stockUpdater = context.getBean(PurchaseInputWmsStockUpdater.class);
        } else if (WmsStockUpdateEvent.RETURN_GOODS_INPUT.equals(stockUpdateEvent)) {
            stockUpdater = context.getBean(ReturnGoodsInputWmsStockUpdater.class);
        }

        stockUpdater.setParameter(parameter);

        return stockUpdater;
    }
}
