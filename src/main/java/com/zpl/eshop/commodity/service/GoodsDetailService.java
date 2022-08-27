package com.zpl.eshop.commodity.service;

import com.zpl.eshop.commodity.domain.GoodsDetailDTO;

/**
 * 商品详情管理Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 17:17
 **/
public interface GoodsDetailService {

    /**
     * 新增商品详情
     *
     * @param goodsDetail 商品详情
     * @return 商品详情id
     */
    Long save(GoodsDetailDTO goodsDetail) throws Exception;


    /**
     * 更新商品详情
     *
     * @param goodsDetail 商品详情
     */
    void update(GoodsDetailDTO goodsDetail) throws Exception;
}
