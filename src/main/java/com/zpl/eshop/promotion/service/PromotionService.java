package com.zpl.eshop.promotion.service;

import com.zpl.eshop.promotion.domain.CouponDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;

import java.util.List;

/**
 * 促销中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:23
 **/
public interface PromotionService {

    /**
     * 根据商品 id 查询促销活动
     *
     * @param goodsId 商品 id
     * @return 促销活动
     */
    List<PromotionActivityDTO> listByGoodsId(Long goodsId);

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     */
    PromotionActivityDTO getById(Long id) throws Exception;

    /**
     * 查询用户当前可以使用的优惠券
     *
     * @param userAccountId 用户帐号id
     * @return 有效优惠券
     */
    List<CouponDTO> listValidByUserAccount(Long userAccountId);

    /**
     * 使用优惠券
     *
     * @param couponId      优惠券id
     * @param userAccountId 帐号id
     * @return 处理结果
     */
    Boolean useCoupon(Long couponId, Long userAccountId) throws Exception;
}
