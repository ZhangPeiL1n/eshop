package com.zpl.eshop.commodity.service;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;

import java.util.List;

/**
 * 商品Sku管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 18:23
 **/
public interface GoodsSkuService {

    /**
     * 新增商品SKU
     *
     * @param goodsSkus 商品sku
     */
    void batchSave(List<GoodsSkuDTO> goodsSkus) throws Exception;
}
