package com.zpl.eshop.commodity.service;

import com.zpl.eshop.commodity.domain.GoodsDTO;
import com.zpl.eshop.commodity.domain.GoodsSkuDTO;

/**
 * 商品中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 17:20
 **/
public interface CommodityService {

    /**
     * 根据 id 查询商品 sku
     *
     * @param goodsSkuId 商品 sku id
     * @return 商品 sku dto
     */
    GoodsSkuDTO getGoodsSkuById(Long goodsSkuId);

    /**
     * 根据id查商品
     *
     * @param goodsId 商品id
     * @return 商品
     */
    GoodsDTO getGoodsById(Long goodsId);
}
