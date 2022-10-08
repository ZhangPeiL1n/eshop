package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;

/**
 * 采购入库单处理的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 16:31
 **/
public interface PurchaseInputOrderHandler {

    /**
     * 执行处理逻辑
     *
     * @param purchaseInputOrder 采购入库单
     * @return 处理结果
     * @throws Exception
     */
    public Boolean execute(PurchaseInputOrderDTO purchaseInputOrder) throws Exception;

}
