package com.zpl.eshop.inventory.async;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品库存更新结果
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 21:23
 **/
@Getter
@Setter
public class StockUpdateResult {

    /**
     * 商品库存更新消息id
     */
    private String id;

    /**
     * 商品库存更新结果
     */
    private Boolean result;
}
