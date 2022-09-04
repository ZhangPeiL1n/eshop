package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsSkuDO;

import java.util.List;

/**
 * 商品sku管理DAO接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:44
 **/
public interface GoodsSkuDAO {

    /**
     * 根据商品id查询商品sku
     *
     * @param goodsId 商品id
     * @return 商品sku
     */
    List<GoodsSkuDO> listByGoodsId(Long goodsId);

    /**
     * 新增商品SKU
     *
     * @param goodsSku 商品sku
     */
    Long save(GoodsSkuDO goodsSku);

    /**
     * 根据商品id删除sku
     *
     * @param goodsId 商品id
     */
    void removeByGoodsId(Long goodsId);
}
