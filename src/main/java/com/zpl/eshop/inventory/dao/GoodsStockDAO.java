package com.zpl.eshop.inventory.dao;

import com.zpl.eshop.inventory.domain.GoodsStockDO;

/**
 * 库存管理系统DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 22:09
 **/
public interface GoodsStockDAO {
    /**
     * 根据商品 skuId 获取库存
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO
     */
    GoodsStockDO getGoodsStockBySkuId(Long goodsSkuId);

    /**
     * 新增商品库存
     *
     * @param goodsStockDO 商品库存DO对象
     * @return 新增成功返回true
     */
    Boolean saveGoodsStock(GoodsStockDO goodsStockDO);

    /**
     * 更新商品库存
     *
     * @param goodsStockDO 商品库存
     * @return 新增成功返回true
     */
    Boolean updateGoodsStock(GoodsStockDO goodsStockDO);

}
