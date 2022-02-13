package com.zpl.eshop.inventory.async;

import lombok.Getter;

import java.util.Observable;

/**
 * 商品库存更新结果的观察目标
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 14:28
 **/
@Getter
public class StockUpdateObservable extends Observable {


    /**
     * 消息id
     */
    private final String messageId;

    /**
     * 构造函数
     *
     * @param messageId 消息id
     */
    public StockUpdateObservable(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 设置商品更新结果
     *
     * @param result 更新结果
     */
    public void setResult(Boolean result) {
        StockUpdateResult stockUpdateResult = new StockUpdateResult();
        stockUpdateResult.setId(messageId);
        stockUpdateResult.setResult(result);
        this.setChanged();
        this.notifyObservers(stockUpdateResult);
    }
}
