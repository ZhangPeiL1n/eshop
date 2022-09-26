package com.zpl.eshop.promotion.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.promotion.constant.PromotionActivityStatus;
import com.zpl.eshop.promotion.dao.PromotionActivityDAO;
import com.zpl.eshop.promotion.domain.PromotionActivityDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 促销活动状态检查任务
 *
 * @author ZhangPeiL1n
 * @date 2022/8/25 21:33
 **/
@Component
public class PromotionActivityStatusCheckTask {

    private static final Logger logger = LoggerFactory.getLogger(PromotionActivityStatusCheckTask.class);
    /**
     * 促销活动管理DAO组件
     */
    @Autowired
    private PromotionActivityDAO promotionActivityDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    @Scheduled(fixedRate = 60 * 1000)
    public void execute() {
        List<PromotionActivityDO> activities = promotionActivityDAO.listAll();
        activities.forEach(activity -> {
            try {
                if (PromotionActivityStatus.DISABLED.equals(activity.getStatus())) {
                    tryEnableActivity(activity);
                } else if (PromotionActivityStatus.ENABLED.equals(activity.getStatus())) {
                    tryDisableActivity(activity);
                }
            } catch (Exception e) {
                logger.error("error", e);
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 尝试启用促销活动
     *
     * @param activity 促销活动
     * @throws Exception
     */
    private void tryEnableActivity(PromotionActivityDO activity) throws Exception {
        Date currentTime = dateProvider.getCurrentTime();
        if (currentTime.after(activity.getStartTime()) && currentTime.before(activity.getEndTime())) {
            activity.setStatus(PromotionActivityStatus.ENABLED);
            activity.setGmtModified(dateProvider.getCurrentTime());
            promotionActivityDAO.updateStatus(activity);
        }
    }

    /**
     * 尝试启用促销活动
     *
     * @param activity 促销活动
     * @throws Exception
     */
    private void tryDisableActivity(PromotionActivityDO activity) throws Exception {
        Date currentTime = dateProvider.getCurrentTime();
        if (currentTime.after(activity.getEndTime())) {
            activity.setStatus(PromotionActivityStatus.DISABLED);
            activity.setGmtModified(dateProvider.getCurrentTime());
            promotionActivityDAO.updateStatus(activity);
        }
    }
}
