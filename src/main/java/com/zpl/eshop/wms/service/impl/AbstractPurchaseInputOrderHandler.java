package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import lombok.Data;

/**
 * 采购入库单处理handler的抽象基类
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 16:37
 **/
@Data
public abstract class AbstractPurchaseInputOrderHandler implements PurchaseInputOrderHandler {

    /**
     * 下一个采购入库单处理handler
     */
    protected PurchaseInputOrderHandler successor;

    /**
     * 执行下一个handler
     *
     * @param purchaseInputOrder 采购入库单
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean execute(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
        PurchaseInputOrderHandlerResult result = doExecute(purchaseInputOrder);
        if (successor != null && result.getDoNext()) {
            return successor.execute(purchaseInputOrder);
        } else {
            return result.getResult();
        }
    }

    /**
     * 执行当前handler的处理逻辑
     *
     * @param purchaseInputOrder 采购入库单
     * @return 处理结果
     * @throws Exception
     */
    protected abstract PurchaseInputOrderHandlerResult doExecute(
            PurchaseInputOrderDTO purchaseInputOrder) throws Exception;

}
