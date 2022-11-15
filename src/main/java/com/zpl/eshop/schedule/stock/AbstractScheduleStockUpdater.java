package com.zpl.eshop.schedule.stock;

/**
 * 商品库存更新命令的抽象基类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 22:31
 **/
public abstract class AbstractScheduleStockUpdater implements ScheduleStockUpdater {

    @Override
    public Boolean update() throws Exception {
        updateGoodsStock();
        updateGoodsAllocationStock();
        updateGoodsAllocationStockDetail();
        return true;
    }

    /**
     * 更新商品库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsStock() throws Exception;

    /**
     * 更新商品货位库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsAllocationStock() throws Exception;

    /**
     * 更新商品货位库存明细
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsAllocationStockDetail() throws Exception;
}
