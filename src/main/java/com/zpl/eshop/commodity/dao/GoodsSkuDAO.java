package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsSkuDO;

/**
 * 商品sku管理DAO接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:44
 **/
public interface GoodsSkuDAO {

    /**
     * 新增商品SKU
     *
     * @param goodsSku 商品sku
     */
    Long save(GoodsSkuDO goodsSku);
}
