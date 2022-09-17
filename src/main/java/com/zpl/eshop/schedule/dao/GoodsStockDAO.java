package com.zpl.eshop.schedule.dao;


import com.zpl.eshop.schedule.domain.GoodsStockDO;

/**
 * 调度中心商品库存管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/17 18:50
 **/
public interface GoodsStockDAO {
    /**
     * 根据商品 skuId 获取库存
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO
     */
    GoodsStockDO getBySkuId(Long goodsSkuId);

    /**
     * 新增商品库存
     *
     * @param goodsStockDO 商品库存DO对象
     */
    void save(GoodsStockDO goodsStockDO);

    /**
     * 更新商品库存
     *
     * @param goodsStockDO 商品库存
     */
    void update(GoodsStockDO goodsStockDO);

}
