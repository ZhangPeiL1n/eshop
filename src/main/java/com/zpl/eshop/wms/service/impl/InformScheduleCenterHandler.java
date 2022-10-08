package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.schedule.service.ScheduleService;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通知调度中心的 handler
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 18:42
 **/
@Component
public class InformScheduleCenterHandler extends AbstractPurchaseInputOrderHandler {

    /**
     * 调度中心接口
     */
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 执行处理逻辑
     */
    public PurchaseInputOrderHandlerResult doExecute(
            PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
        scheduleService.informPurchaseInputFinished(purchaseInputOrder);
        return new PurchaseInputOrderHandlerResult(true);
    }

}
