package com.zpl.eshop.wms.stock;

/**
 * wms库存更新组件抽象基类
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 19:40
 **/
public abstract class AbstractWmsStockUpdater implements WmsStockUpdater {

    /**
     * 更新商品库存
     */
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
     * @throws Exception
     */
    protected abstract void updateGoodsStock() throws Exception;

    /**
     * 更新货位库存
     *
     * @throws Exception
     */
    protected abstract void updateGoodsAllocationStock() throws Exception;

    /**
     * 更新货位库存明细
     *
     * @throws Exception
     */
    protected abstract void updateGoodsAllocationStockDetail() throws Exception;

}
