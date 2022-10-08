package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.WmsGoodsAllocationStockDO;

/**
 * wms中心商品货位库存DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 19:25
 **/
public interface WmsGoodsAllocationStockDAO {

    /**
     * 根据商品sku id查询商品库存
     *
     * @param goodsSkuId 商品sku id
     * @return 商品库存
     */

    WmsGoodsAllocationStockDO getBySkuId(Long goodsAllocationId, Long goodsSkuId);

    /**
     * 新增商品库存
     *
     * @param goodsAllocationStock 商品库存DO对象
     */
    void save(WmsGoodsAllocationStockDO goodsAllocationStock);

    /**
     * 更新商品库存
     *
     * @param goodsAllocationStock 商品库存DO对象
     */
    void update(WmsGoodsAllocationStockDO goodsAllocationStock);
}
