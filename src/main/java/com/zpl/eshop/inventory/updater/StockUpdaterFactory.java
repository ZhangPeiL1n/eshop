package com.zpl.eshop.inventory.updater;

/**
 * 库存更新命令的创建工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 22:05
 **/
public interface StockUpdaterFactory<T> {

    /**
     * 创建库存更新命令
     *
     * @param parameter 参数
     * @return 库存更新密令
     */
    StockUpdater create(T parameter);
}
