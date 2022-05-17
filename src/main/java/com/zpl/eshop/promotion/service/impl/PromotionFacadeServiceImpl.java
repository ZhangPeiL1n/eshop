package com.zpl.eshop.promotion.service.impl;

import com.zpl.eshop.promotion.constant.PromotionActivityStatus;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import com.zpl.eshop.promotion.service.PromotionFacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/2/8 22:59
 **/
@Service
public class PromotionFacadeServiceImpl implements PromotionFacadeService {

    private final Logger logger = LoggerFactory.getLogger(PromotionFacadeServiceImpl.class);

    /**
     * 根据商品 id 查询促销活动
     *
     * @param goodsId 商品 id
     * @return 促销活动
     */
    @Override
    public List<PromotionActivityDTO> listPromotionActivityByGoodsId(Long goodsId) {
        List<PromotionActivityDTO> promotionActivityDTOs = new ArrayList<>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            PromotionActivityDTO promotionActivityDTO1 = new PromotionActivityDTO();
            promotionActivityDTO1.setId(1L);
            promotionActivityDTO1.setPromotionActivityName("测试促销活动1");
            promotionActivityDTO1.setPromotionActivityComment("测试促销活动");
            promotionActivityDTO1.setPromotionActivityRule("测试促销活动规则");
            promotionActivityDTO1.setPromotionActivityStartTime(formatter.parse("2022-02-08 23:50:00"));
            promotionActivityDTO1.setPromotionActivityEndTime(formatter.parse("2022-11-11 11:11:11"));
            promotionActivityDTO1.setPromotionActivityStatus(PromotionActivityStatus.ENABLED);
            promotionActivityDTO1.setGmtCreate(formatter.parse("2022-02-14 11:11:11"));
            promotionActivityDTO1.setGmtModified(formatter.parse("2022-02-14 11:11:11"));
            promotionActivityDTOs.add(promotionActivityDTO1);

            PromotionActivityDTO promotionActivityDTO2 = new PromotionActivityDTO();
            promotionActivityDTO2.setId(2L);
            promotionActivityDTO2.setPromotionActivityName("测试促销活动2");
            promotionActivityDTO2.setPromotionActivityComment("测试促销活动");
            promotionActivityDTO2.setPromotionActivityRule("测试促销活动规则");
            promotionActivityDTO2.setPromotionActivityStartTime(formatter.parse("2022-02-08 23:50:00"));
            promotionActivityDTO2.setPromotionActivityEndTime(formatter.parse("2022-11-11 11:11:11"));
            promotionActivityDTO2.setPromotionActivityStatus(PromotionActivityStatus.ENABLED);
            promotionActivityDTO2.setGmtCreate(formatter.parse("2022-02-14 11:11:11"));
            promotionActivityDTO2.setGmtModified(formatter.parse("2022-02-14 11:11:11"));
            promotionActivityDTOs.add(promotionActivityDTO2);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
        return promotionActivityDTOs;
    }
}
