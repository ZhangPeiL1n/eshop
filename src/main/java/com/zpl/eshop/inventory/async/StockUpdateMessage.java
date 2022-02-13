package com.zpl.eshop.inventory.async;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品库存更新消息
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 13:57
 **/
@Getter
@Setter
public class StockUpdateMessage {

    private String id;

    /**
     * 商品库存更新操作
     */
    private Integer operation;


    /**
     * 核心参数
     */
    private Object parameter;
}
