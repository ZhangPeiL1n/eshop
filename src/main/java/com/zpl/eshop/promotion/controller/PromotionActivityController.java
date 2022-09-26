package com.zpl.eshop.promotion.controller;

import com.zpl.eshop.common.util.CloneDirection;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityQuery;
import com.zpl.eshop.promotion.domain.PromotionActivityVO;
import com.zpl.eshop.promotion.service.PromotionActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 促销活动管理Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/25 21:05
 **/
@RestController
@RequestMapping("/promotion/activity")
public class PromotionActivityController {

    private static final Logger logger = LoggerFactory.getLogger(PromotionActivityController.class);
    /**
     * 促销活动管理模块Service组件
     */
    @Autowired
    private PromotionActivityService promotionActivityService;

    /**
     * 分页查询促销活动
     *
     * @return 促销活动集合
     */
    @GetMapping("/")
    public List<PromotionActivityVO> listByPage(@RequestBody PromotionActivityQuery query) {
        try {
            return ObjectUtils.convertList(promotionActivityService.listByPage(query), PromotionActivityVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     */
    @GetMapping("/{id}")
    public PromotionActivityVO getById(@PathVariable("id") Long id) {
        try {
            return promotionActivityService.getById(id).clone(PromotionActivityVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
            return new PromotionActivityVO();
        }
    }

    /**
     * 新增促销活动
     *
     * @param activity 促销活动
     */
    @PostMapping("/")
    public Boolean save(@RequestBody PromotionActivityVO activity) {
        try {
            promotionActivityService.save(activity.clone(PromotionActivityDTO.class, CloneDirection.FORWARD));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新促销活动
     *
     * @param activity 促销活动
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody PromotionActivityVO activity) throws Exception {
        try {
            promotionActivityService.update(activity.clone(PromotionActivityDTO.class, CloneDirection.FORWARD));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除促销活动
     *
     * @param id 促销活动id
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            promotionActivityService.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
