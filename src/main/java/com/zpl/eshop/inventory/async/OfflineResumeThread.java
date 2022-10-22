package com.zpl.eshop.inventory.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 离线数据恢复线程
 *
 * @author ZhangPeiL1n
 * @date 2022/2/13 11:35
 **/
public class OfflineResumeThread extends Thread {

    private final Logger logger = LoggerFactory.getLogger(OfflineResumeThread.class);

    /**
     * 离线存储管理组件
     */
    private final OfflineStorageManager offlineStorageManager;

    /**
     * 库存更新消息队列
     */
    private final StockUpdateQueue stockUpdateQueue;

    /**
     * 构造函数
     *
     * @param offlineStorageManager 离线存储管理组件
     */
    public OfflineResumeThread(OfflineStorageManager offlineStorageManager, StockUpdateQueue stockUpdateQueue) {
        this.offlineStorageManager = offlineStorageManager;
        this.stockUpdateQueue = stockUpdateQueue;
    }


    @Override
    public void run() {
        try {
            OfflineStorageIterator offlineStorageIterator = offlineStorageManager.iterator();
            while (offlineStorageIterator.hasNext()) {

                // 每次从 mysql 中查询 50 条数据，批量查询，批量处理，批量删除
                try {
                    List<StockUpdateMessage> stockUpdateMessages = offlineStorageIterator.next();
                    // 将这批数据入队
                    for (StockUpdateMessage stockUpdateMessage : stockUpdateMessages) {
                        stockUpdateQueue.putDirect(stockUpdateMessage);
                    }
                    // 批量删除
                    offlineStorageManager.removeByBatch(stockUpdateMessages);
                } catch (Exception e) {
                    logger.error("error", e);
                }
            }
            // 此时mysql中所有数据被删除，修改内存标识
            offlineStorageManager.setOffline(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
