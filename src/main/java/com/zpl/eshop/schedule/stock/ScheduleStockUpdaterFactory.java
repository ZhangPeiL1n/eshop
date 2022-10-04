package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.common.bean.SpringApplicationContext;
import com.zpl.eshop.schedule.constant.StockUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 库存更新命令的创建工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 22:05
 **/
@Component
public class ScheduleStockUpdaterFactory {


    /**
     * spring容器
     */
    @Autowired
    private SpringApplicationContext context;

    /**
     * 创建库存更新命令
     *
     * @param parameter 参数·
     * @return 库存更新密令
     */
    public ScheduleStockUpdater create(Integer stockUpdateEvent, Object parameter) {
        ScheduleStockUpdater stockUpdater = null;
        if (StockUpdateEvent.SUBMIT_ORDER.equals(stockUpdateEvent)) {
            stockUpdater = context.getBean(SubmitOrderScheduleStockUpdater.class);
        } else if (StockUpdateEvent.CANCEL_ORDER.equals(stockUpdateEvent)) {
            stockUpdater = context.getBean(CancelOrderScheduleStockUpdater.class);
        } else if (StockUpdateEvent.PAY_ORDER.equals(stockUpdateEvent)) {
            stockUpdater = context.getBean(PayOrderScheduleStockUpdater.class);
        } else if (StockUpdateEvent.PURCHASE_INPUT.equals(stockUpdateEvent)) {
            stockUpdater = context.getBean(PurchaseInputScheduleStockUpdater.class);
        }
        stockUpdater.setParameter(parameter);
        return stockUpdater;
    }
}
