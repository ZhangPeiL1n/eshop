package com.zpl.eshop.schedule.stock;

/**
 * 商品库存更新命令接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 22:29
 **/
public interface StockUpdater {
    /**
     * 更新商品库存
     *
     * @return 更新成功返回true
     */
    Boolean update();
}
