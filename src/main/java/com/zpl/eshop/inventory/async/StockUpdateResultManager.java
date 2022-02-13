package com.zpl.eshop.inventory.async;

/**
 * 商品库存更新结果管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 14:26
 **/
public interface StockUpdateResultManager {

    /**
     * 设置对商品库存更新结果的观察
     *
     * @param messageId 商品库存更新消息id
     */
    void observe(String messageId);

    /**
     * 获取商品库存更新结果的观察目标
     *
     * @param messageId 商品库存更新消息id
     * @param result    处理结果
     */
    void inform(String messageId, Boolean result);
}
