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
     * 根据商品id查询商品详情
     *
     * @param goodsId 商品id
     * @return 商品详情
     */
    GoodsDetailDTO getByGoodsId(Long goodsId) throws Exception;

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
