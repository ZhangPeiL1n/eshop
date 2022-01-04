package com.zpl.eshop.promotion.service;

import com.zpl.eshop.promotion.dto.PromotionActivityDTO;

import java.util.List;

/**
 * 促销中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:23
 **/
public interface PromotionFacadeService {

    /**
     * 根据商品 id 查询促销活动
     *
     * @param goodsId 商品 id
     * @return 促销活动
     */
    List<PromotionActivityDTO> listPromotionActivityByGoodsId(Long goodsId);
}
