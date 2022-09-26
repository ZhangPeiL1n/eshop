package com.zpl.eshop.promotion.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.promotion.constant.CouponStatus;
import com.zpl.eshop.promotion.dao.CouponAchieveDAO;
import com.zpl.eshop.promotion.dao.CouponDAO;
import com.zpl.eshop.promotion.dao.PromotionActivityDAO;
import com.zpl.eshop.promotion.domain.CouponAchieveDO;
import com.zpl.eshop.promotion.domain.CouponDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import com.zpl.eshop.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/2/8 22:59
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PromotionServiceImpl implements PromotionService {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 促销活动管理模块DAO组件
     */
    @Autowired
    private PromotionActivityDAO promotionActivityDAO;

    /**
     * 优惠券领取记录DAO
     */
    @Autowired
    private CouponAchieveDAO couponAchieveDAO;

    /**
     * 优惠券管理DAO
     */
    @Autowired
    private CouponDAO couponDAO;

    /**
     * 根据商品 id 查询促销活动
     *
     * @param goodsId 商品 id
     * @return 促销活动
     */
    @Override
    public List<PromotionActivityDTO> listByGoodsId(Long goodsId) throws Exception {
        return ObjectUtils.convertList(promotionActivityDAO.listEnabledByGoodsId(goodsId), PromotionActivityDTO.class);
    }

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     */
    @Override
    public PromotionActivityDTO getById(Long id) throws Exception {
        return promotionActivityDAO.getById(id).clone(PromotionActivityDTO.class);
    }

    /**
     * 查询用户当前可以使用的优惠券
     *
     * @param userAccountId 用户帐号id
     * @return 有效优惠券
     */
    @Override
    public List<CouponDTO> listValidByUserAccount(Long userAccountId) {
        List<CouponDTO> coupons = new ArrayList<>();
        List<CouponAchieveDO> couponAchieves = couponAchieveDAO.listUnusedByUserAccountId(userAccountId);
        couponAchieves.forEach(couponAchieve -> {
            try {
                CouponDTO coupon = couponDAO.getById(couponAchieve.getCouponId()).clone(CouponDTO.class);
                if (CouponStatus.GIVING_OUT.equals(coupon.getStatus())
                        || CouponStatus.GIVEN_OUT.equals(coupon.getStatus())) {
                    coupons.add(coupon);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return coupons;
    }

    /**
     * 使用优惠券
     *
     * @param couponId      优惠券id
     * @param userAccountId 帐号id
     * @return 处理结果
     */
    @Override
    public Boolean useCoupon(Long couponId, Long userAccountId) throws Exception {
        CouponAchieveDO couponAchieve = couponAchieveDAO.getByUserAccountId(couponId, userAccountId);
        if (couponAchieve == null) {
            return false;
        }
        couponAchieve.setUsed(1);
        couponAchieve.setUsedTime(dateProvider.getCurrentTime());
        couponAchieve.setGmtModified(dateProvider.getCurrentTime());
        couponAchieveDAO.update(couponAchieve);
        return true;
    }

    /**
     * 使用优惠券
     *
     * @param couponId      优惠券id
     * @param userAccountId 帐号id
     * @return 处理结果
     */
    @Override
    public Boolean useCoupon(Long couponId, Long userAccountId) throws Exception {
        return true;
    }
}
