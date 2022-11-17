package com.zpl.eshop.promotion.dao;

import com.zpl.eshop.promotion.domain.CouponAchieveDO;

import java.util.List;

/**
 * 优惠券领取记录管理DAO接口
 *
 * @author ZhangPeiL1n
 */
public interface CouponAchieveDAO {

    /**
     * 根据优惠券id和用户账号id查询优惠券的领取记录
     *
     * @param couponId      优惠券id
     * @param userAccountId 用户账号id
     * @return 优惠券领取记录
     * @throws Exception
     */
    CouponAchieveDO getByUserAccountId(Long couponId, Long userAccountId) throws Exception;

    /**
     * 新增优惠券领取记录
     *
     * @param couponAchieve 优惠券领取记录
     * @throws Exception
     */
    void save(CouponAchieveDO couponAchieve) throws Exception;

    /**
     * 查询用户还没有使用过的优惠券领取记录
     *
     * @param userAccountId 用户账号id
     * @return 优惠券领取记录
     * @throws Exception
     */
    List<CouponAchieveDO> listUnusedByUserAccountId(Long userAccountId) throws Exception;

    /**
     * 更新优惠券领取记录
     *
     * @param couponAchieve 优惠券领取记录
     * @throws Exception
     */
    void update(CouponAchieveDO couponAchieve) throws Exception;
}
