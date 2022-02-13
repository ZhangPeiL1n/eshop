package com.zpl.eshop.inventory.async;

/**
 * 商品库存更新消息的队列接口
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 13:53
 **/
public interface StockUpdateQueue {

    /**
     * 将一个消息放入队列
     *
     * @param message 消息
     * @throws Exception
     */
    void put(StockUpdateMessage message) throws Exception;

    /**
     * 将一个消息取出队列
     *
     * @return 消息
     * @throws Exception
     */
    StockUpdateMessage get() throws Exception;

    /**
     * 直接将消息入队
     *
     * @param message 消息
     * @throws Exception
     */
    void putDirect(StockUpdateMessage message) throws Exception;
}

