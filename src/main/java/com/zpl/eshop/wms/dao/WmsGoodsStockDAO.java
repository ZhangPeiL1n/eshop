package com.zpl.eshop.wms.dao;

import com.zpl.eshop.wms.domain.WmsGoodsStockDO;

/**
 * wms中心商品库存管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 17:06
 **/
public interface WmsGoodsStockDAO {

    /**
     * 根据商品sku id查询商品库存
     *
     * @param goodsSkuId 商品sku id
     * @return 商品库存
     */
    WmsGoodsStockDO getBySkuId(Long goodsSkuId);

    /**
     * 新增商品库存
     *
     * @param goodsStockDO 商品库存DO对象
     */
    void save(WmsGoodsStockDO goodsStockDO);

    /**
     * 更新商品库存
     *
     * @param goodsStockDO 商品库存DO对象
     */
    void update(WmsGoodsStockDO goodsStockDO);
}
