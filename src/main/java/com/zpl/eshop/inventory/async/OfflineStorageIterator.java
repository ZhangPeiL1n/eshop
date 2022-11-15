package com.zpl.eshop.inventory.async;

import java.util.List;

/**
 * 离线村塾数据迭代器
 *
 * @author ZhangPeiL1n
 * @date 2022/10/22 23:15
 **/
public interface OfflineStorageIterator {
    /**
     * 判断是否还有下一批库存更新消息
     *
     * @return true 还有库存更新消息
     */
    Boolean hasNext();

    /**
     * 离线恢复线程使用，每次批量查询50条
     *
     * @return 50条数据
     * @throws Exception
     */
    List<StockUpdateMessage> next() throws Exception;
}
