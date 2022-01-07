package com.zpl.eshop.logistics.service;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;

/**
 * 物流中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:20
 **/
public interface LogisticsFacadeService {

    /**
     * 计算商品 sku 的运费
     * @param goodsSkuDTO 商品 sku DTO
     * @return 商品 sku 的运费
     */
    Double calculateFreight(GoodsSkuDTO goodsSkuDTO);
}
