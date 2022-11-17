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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 促销中心service组件
 *
 * @author ZhangPeiL1n
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PromotionServiceImpl implements PromotionService {

	private static final Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

	/**
	 * 促销活动管理DAO组件
	 */
	@Autowired
	private PromotionActivityDAO promotionActivityDAO;

	/**
	 * 优惠券领取记录管理DAO组件
	 */
	@Autowired
	private CouponAchieveDAO couponAchieveDAO;

	/**
	 * 优惠券管理DAO组件
	 */
	@Autowired
	private CouponDAO couponDAO;

	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;

	/**
	 * 根据商品id查询促销活动
	 *
	 * @param goodsId 商品id
	 * @return 促销活动
	 */
	public List<PromotionActivityDTO> listByGoodsId(Long goodsId) {
		try {
			return ObjectUtils.convertList(promotionActivityDAO.listEnabledByGoodsId(goodsId),
					PromotionActivityDTO.class);
		} catch (Exception e) {
			logger.error("error", e);
			return new ArrayList<PromotionActivityDTO>();
		}
	}

	/**
	 * 根据id查询促销活动
	 *
	 * @param id 促销活动id
	 * @return 促销活动
	 */
	public PromotionActivityDTO getById(Long id) {
		try {
			return promotionActivityDAO.getById(id).clone(PromotionActivityDTO.class);
		} catch (Exception e) {
			logger.error("Error", e);
			return null;
		}
	}

	/**
	 * 查询用户当前可以使用的有效优惠券
	 *
	 * @param userAccountId 用户账号id
	 * @return 有效优惠券
	 */
	public List<CouponDTO> listValidByUserAccountId(Long userAccountId) {
		List<CouponDTO> coupons = new ArrayList<>();

		try {
			List<CouponAchieveDO> couponAchieves =
					couponAchieveDAO.listUnusedByUserAccountId(userAccountId);
			for (CouponAchieveDO couponAchieve : couponAchieves) {
				CouponDTO coupon =
						couponDAO.getById(couponAchieve.getCouponId()).clone(CouponDTO.class);
				if (CouponStatus.GIVING_OUT.equals(coupon.getStatus()) ||
						CouponStatus.GIVEN_OUT.equals(coupon.getStatus())) {
					coupons.add(coupon);
				}
			}
		} catch (Exception e) {
			logger.error("error", e);
		}
		return coupons;
	}

	/**
	 * 使用优惠券
	 *
	 * @param couponId      优惠券id
	 * @param userAccountId 用户账号id
	 * @return 处理结果
	 */
	public Boolean useCoupon(Long couponId, Long userAccountId) {
		try {
			CouponAchieveDO couponAchieve = new CouponAchieveDO();
			couponAchieve.setCouponId(couponId);
			couponAchieve.setUserAccountId(userAccountId);
			couponAchieve.setUsed(1);
			couponAchieve.setUsedTime(dateProvider.getCurrentTime());
			couponAchieveDAO.update(couponAchieve);

			return true;
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
	}

}
