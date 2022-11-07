package com.zpl.eshop.promotion.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.promotion.constant.PromotionActivityStatus;
import com.zpl.eshop.promotion.constant.PromotionActivityType;
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
 * @author ZhangPeiL1n
 * @date 2022/2/8 22:59
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PromotionServiceImpl implements PromotionService {

    private final Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);
    @Autowired
    private DateProvider dateProvider;

    /**
     * 根据商品 id 查询促销活动
     *
     * @param goodsId 商品 id
     * @return 促销活动
     */
    @Override
    public List<PromotionActivityDTO> listByGoodsId(Long goodsId) {
        List<PromotionActivityDTO> promotionActivities = new ArrayList<>();
        try {

            PromotionActivityDTO promotionActivity1 = new PromotionActivityDTO();
            promotionActivity1.setId(1L);
            promotionActivity1.setName("测试促销活动1");
            promotionActivity1.setRemark("测试促销活动");
            promotionActivity1.setRule("测试促销活动规则");
            promotionActivity1.setStartTime(dateProvider.parse2Datetime("2022-02-08 23:50:00"));
            promotionActivity1.setEndTime(dateProvider.parse2Datetime("2022-11-11 11:11:11"));
            promotionActivity1.setStatus(PromotionActivityStatus.ENABLED);
            promotionActivity1.setType(PromotionActivityType.REACH_DISCOUNT);
            promotionActivity1.setGmtCreate(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
            promotionActivity1.setGmtModified(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
            promotionActivities.add(promotionActivity1);

            PromotionActivityDTO promotionActivity2 = new PromotionActivityDTO();
            promotionActivity2.setId(2L);
            promotionActivity2.setName("测试促销活动2");
            promotionActivity2.setRemark("测试促销活动2");
            promotionActivity2.setRule("测试促销活动规则2");
            promotionActivity2.setStartTime(dateProvider.parse2Datetime("2022-02-08 23:50:00"));
            promotionActivity2.setEndTime(dateProvider.parse2Datetime("2022-11-11 11:11:11"));
            promotionActivity2.setStatus(PromotionActivityStatus.ENABLED);
            promotionActivity2.setType(PromotionActivityType.DIRECT_GIFT);
            promotionActivity2.setGmtCreate(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
            promotionActivity2.setGmtModified(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
            promotionActivities.add(promotionActivity2);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
        return promotionActivities;
    }

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     */
    @Override
    public PromotionActivityDTO getById(Long id) throws Exception {
        Long id1 = 1L;
        Long id2 = 2L;
        if (id.equals(id1)) {
            return createDiscountPromotionActivity(id);
        } else if (id.equals(id2)) {
            return createGiftPromotionActivity(id);
        }
        return null;
    }

    private PromotionActivityDTO createDiscountPromotionActivity(Long id) throws Exception {
        PromotionActivityDTO promotionActivity = new PromotionActivityDTO();
        promotionActivity.setId(id);
        promotionActivity.setName("测试满减促销活动");
        promotionActivity.setRemark("测试促销活动");
        promotionActivity.setRule("[{'thresholdAmount': 200,'reduceAmount':20},{'thresholdAmount': 100,'reduceAmount':10}]");
        promotionActivity.setStartTime(dateProvider.parse2Datetime("2022-02-08 23:50:00"));
        promotionActivity.setEndTime(dateProvider.parse2Datetime("2022-11-11 11:11:11"));
        promotionActivity.setStatus(PromotionActivityStatus.ENABLED);
        promotionActivity.setType(PromotionActivityType.REACH_DISCOUNT);
        promotionActivity.setGmtCreate(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
        promotionActivity.setGmtModified(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
        return promotionActivity;
    }

    private PromotionActivityDTO createGiftPromotionActivity(Long id) throws Exception {
        PromotionActivityDTO promotionActivity = new PromotionActivityDTO();
        promotionActivity.setId(id);
        promotionActivity.setName("测试满赠促销活动");
        promotionActivity.setRemark("测试促销活动");
        promotionActivity.setRule("{'thresholdAmount': 200,'giftGoodsSkuIds': [2]}");
        promotionActivity.setStartTime(dateProvider.parse2Datetime("2022-02-08 23:50:00"));
        promotionActivity.setEndTime(dateProvider.parse2Datetime("2022-11-11 11:11:11"));
        promotionActivity.setStatus(PromotionActivityStatus.ENABLED);
        promotionActivity.setType(PromotionActivityType.REACH_GIFT);
        promotionActivity.setGmtCreate(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
        promotionActivity.setGmtModified(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
        return promotionActivity;
    }

    /**
     * 查询用户当前可以使用的优惠券
     *
     * @param userAccountId 用户帐号id
     * @return 有效优惠券
     */
    @Override
    public List<CouponDTO> listValidByUserAccount(Long userAccountId) {
        return null;
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
