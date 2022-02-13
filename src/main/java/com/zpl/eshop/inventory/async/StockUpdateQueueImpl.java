package com.zpl.eshop.inventory.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 商品库存更新队列实现类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 13:55
 **/
@Component
public class StockUpdateQueueImpl implements StockUpdateQueue {

    public static final Integer QUEUE_MAX_SIZE = 1000;

    /**
     * 商品库存更新队列
     */
    private final ArrayBlockingQueue<StockUpdateMessage> queue = new ArrayBlockingQueue<>(QUEUE_MAX_SIZE);

    /**
     * 离线存储管理器
     */
    @Autowired
    private OfflineStorageManager offlineStorageManager;

    /**
     * 将一个消息放入队列
     *
     * @param message 消息
     * @throws Exception
     */
    @Override
    public void put(StockUpdateMessage message) throws Exception {
        // 每次往消息队列 put 消息之前，先判断一下离线存储标识
        // 如果开启了离线存储，就走直接走离线存储，向 mysql 存储，不走后面入队逻辑
        // 并且判断一下阻塞队列长度，如果长度清零，则启动一个后台线程
        // 让后台线程去查询 mysql 中数据放入队列中
        if (offlineStorageManager.getOffline()) {
            offlineStorageManager.store(message);
            if (queue.size() == 0) {
                new OfflineResumeThread(offlineStorageManager, this).start();
            }
            return;
        }
        // 如果内存队列已满，此时就触发离线存储
        if (QUEUE_MAX_SIZE.equals(queue.size())) {
            offlineStorageManager.store(message);
            offlineStorageManager.setOffline(true);
            return;
        }
        queue.put(message);
    }

    /**
     * 将一个消息取出队列
     *
     * @return 消息
     * @throws Exception
     */
    @Override
    public StockUpdateMessage get() throws Exception {
        return queue.take();
    }


    /**
     * 直接将消息入队
     *
     * @param message 消息
     * @throws Exception
     */
    @Override
    public void putDirect(StockUpdateMessage message) throws Exception {
        queue.put(message);
    }
}
