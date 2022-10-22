package com.zpl.eshop.inventory.async;

import java.util.List;

/**
 * 离线存储管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 22:25
 **/
public interface OfflineStorageManager {

    /**
     * 离线存储库存更新消息
     *
     * @param message 库存更新消息
     * @throws Exception
     */
    void store(StockUpdateMessage message) throws Exception;

    /**
     * 获取离线标识
     *
     * @return 离线标识
     */
    Boolean getOffline();

    /**
     * 设置离线标识
     *
     * @param offline 离线标识
     */
    void setOffline(Boolean offline);

    OfflineStorageIterator iterator() throws Exception;

    /**
     * 批量删除
     *
     * @param messages 库存更新消息集合
     * @return true 删除成功
     */
    Boolean removeByBatch(List<StockUpdateMessage> messages);

    /**
     * 查询库存消息表的记录数
     *
     * @return 记录数
     */
    Long count();
}