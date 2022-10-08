package com.zpl.eshop.wms.service.impl;

import lombok.Data;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/8 18:52
 **/
@Data
public class PurchaseInputOrderHandlerResult {

    /**
     * 处理结果
     */
    private Boolean result;
    /**
     * 是否执行下一个handler
     */
    private Boolean doNext = true;

    public PurchaseInputOrderHandlerResult(Boolean result) {
        this.result = result;
    }

    public PurchaseInputOrderHandlerResult(Boolean result, Boolean doNext) {
        this.result = result;
        this.doNext = doNext;
    }
}
