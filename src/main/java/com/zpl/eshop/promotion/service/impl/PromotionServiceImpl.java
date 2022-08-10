package com.zpl.eshop.promotion.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.promotion.constant.PromotionActivityStatus;
import com.zpl.eshop.promotion.constant.PromotionActivityType;
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
        List<PromotionActivityDTO> promotionActivityDTOs = new ArrayList<>();
        try {

            PromotionActivityDTO promotionActivityDTO1 = new PromotionActivityDTO();
            promotionActivityDTO1.setId(1L);
            promotionActivityDTO1.setName("测试促销活动1");
            promotionActivityDTO1.setRemark("测试促销活动");
            promotionActivityDTO1.setRule("测试促销活动规则");
            promotionActivityDTO1.setStartTime(dateProvider.parse2Datetime("2022-02-08 23:50:00"));
            promotionActivityDTO1.setEndTime(dateProvider.parse2Datetime("2022-11-11 11:11:11"));
            promotionActivityDTO1.setStatus(PromotionActivityStatus.ENABLED);
            promotionActivityDTO1.setType(PromotionActivityType.REACH_DISCOUNT);
            promotionActivityDTO1.setGmtCreate(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
            promotionActivityDTO1.setGmtModified(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
            promotionActivityDTOs.add(promotionActivityDTO1);

            PromotionActivityDTO promotionActivityDTO2 = new PromotionActivityDTO();
            promotionActivityDTO2.setId(2L);
            promotionActivityDTO2.setName("测试促销活动2");
            promotionActivityDTO2.setRemark("测试促销活动2");
            promotionActivityDTO2.setRule("测试促销活动规则2");
            promotionActivityDTO2.setStartTime(dateProvider.parse2Datetime("2022-02-08 23:50:00"));
            promotionActivityDTO2.setEndTime(dateProvider.parse2Datetime("2022-11-11 11:11:11"));
            promotionActivityDTO2.setStatus(PromotionActivityStatus.ENABLED);
            promotionActivityDTO2.setType(PromotionActivityType.DIRECT_GIFT);
            promotionActivityDTO2.setGmtCreate(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
            promotionActivityDTO2.setGmtModified(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
            promotionActivityDTOs.add(promotionActivityDTO2);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
        return promotionActivityDTOs;
    }

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     */
    @Override
    public PromotionActivityDTO getById(Long id) throws Exception {
        PromotionActivityDTO promotionActivity = new PromotionActivityDTO();
        promotionActivity.setId(1L);
        promotionActivity.setName("测试促销活动1");
        promotionActivity.setRemark("测试促销活动");
        promotionActivity.setRule("测试促销活动规则");
        promotionActivity.setStartTime(dateProvider.parse2Datetime("2022-02-08 23:50:00"));
        promotionActivity.setEndTime(dateProvider.parse2Datetime("2022-11-11 11:11:11"));
        promotionActivity.setStatus(PromotionActivityStatus.ENABLED);
        promotionActivity.setType(PromotionActivityType.REACH_DISCOUNT);
        promotionActivity.setGmtCreate(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
        promotionActivity.setGmtModified(dateProvider.parse2Datetime("2022-02-14 11:11:11"));
        return promotionActivity;
    }
}
