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
     * @param goodsAllocationId 商品货位id
     * @param goodsSkuId        商品skuId
     * @return 商品库存
     * @throws Exception
     */

    WmsGoodsAllocationStockDO getBySkuId(Long goodsAllocationId, Long goodsSkuId) throws Exception;

    /**
     * 新增商品库存
     *
     * @param goodsAllocationStock 商品库存DO对象
     * @throws Exception
     */
    void save(WmsGoodsAllocationStockDO goodsAllocationStock) throws Exception;

    /**
     * 更新商品库存
     *
     * @param goodsAllocationStock 商品库存DO对象
     * @throws Exception
     */
    void update(WmsGoodsAllocationStockDO goodsAllocationStock) throws Exception;
}
