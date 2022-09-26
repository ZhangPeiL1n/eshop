package com.zpl.eshop.promotion.controller;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.promotion.domain.PromotionActivityVO;
import com.zpl.eshop.promotion.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 促销中心接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/25 21:04
 **/
@RestController
@RequestMapping("/promotion")
public class PromotionController {

    private static final Logger logger = LoggerFactory.getLogger(PromotionController.class);

    /**
     * 促销中心Service
     */
    @Autowired
    private PromotionService promotionService;

    /**
     * 根据商品 id 查询促销活动
     *
     * @param goodsId 商品 id
     * @return 促销活动
     */
    @GetMapping("/activity/enabled/{goodsId}")
    List<PromotionActivityVO> listByGoodsId(@PathVariable("goodsId") Long goodsId) throws Exception {
        try {
            return ObjectUtils.convertList(promotionService.listByGoodsId(goodsId), PromotionActivityVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }
}
