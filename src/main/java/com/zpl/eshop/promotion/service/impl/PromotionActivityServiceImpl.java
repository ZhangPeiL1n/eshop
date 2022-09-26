package com.zpl.eshop.promotion.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.promotion.constant.PromotionActivityStatus;
import com.zpl.eshop.promotion.dao.PromotionActivityDAO;
import com.zpl.eshop.promotion.dao.PromotionActivityGoodsRelationDAO;
import com.zpl.eshop.promotion.domain.*;
import com.zpl.eshop.promotion.service.PromotionActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 促销活动管理模块Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/25 20:34
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PromotionActivityServiceImpl implements PromotionActivityService {

    /**
     * 促销活动DAO组件
     */
    @Autowired
    private PromotionActivityDAO promotionActivityDAO;

    /**
     * 促销活动与商品关联关系DAO组件
     */
    @Autowired
    private PromotionActivityGoodsRelationDAO relationDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询促销活动
     *
     * @return 促销活动集合
     */
    @Override
    public List<PromotionActivityDTO> listByPage(PromotionActivityQuery query) throws Exception {
        return ObjectUtils.convertList(promotionActivityDAO.listByPage(query), PromotionActivityDTO.class);
    }

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     */
    @Override
    public PromotionActivityDTO getById(Long id) throws Exception {
        PromotionActivityDTO activity = promotionActivityDAO.getById(id).clone(PromotionActivityDTO.class);

        List<PromotionActivityGoodsRelationDO> relations = relationDAO.listByActivityId(activity.getId());
        activity.setRelations(ObjectUtils.convertList(relations, PromotionActivityGoodsRelationDTO.class));
        return activity;
    }

    /**
     * 新增促销活动
     *
     * @param activity 促销活动
     */
    @Override
    public void save(PromotionActivityDTO activity) throws Exception {
        activity.setGmtCreate(dateProvider.getCurrentTime());
        activity.setGmtModified(dateProvider.getCurrentTime());
        activity.setStatus(PromotionActivityStatus.DISABLED);
        Long id = promotionActivityDAO.save(activity.clone(PromotionActivityDO.class));
        activity.setId(id);
        saveRelations(activity);
    }

    /**
     * 更新促销活动
     *
     * @param activity 促销活动
     */
    @Override
    public void update(PromotionActivityDTO activity) throws Exception {
        activity.setGmtModified(dateProvider.getCurrentTime());
        List<PromotionActivityGoodsRelationDTO> relations = activity.getRelations();
        promotionActivityDAO.update(activity.clone(PromotionActivityDO.class));
        relationDAO.removeByActivityId(activity.getId());
        saveRelations(activity);
    }

    /**
     * 删除促销活动
     *
     * @param id 促销活动id
     */
    @Override
    public void remove(Long id) {
        relationDAO.removeByActivityId(id);
        promotionActivityDAO.remove(id);
    }

    /**
     * 保存促销活动与商品的关联关系
     *
     * @param activity 促销活动
     */
    private void saveRelations(PromotionActivityDTO activity) {
        activity.getRelations().forEach(relation -> {
            try {
                relation.setPromotionActivityId(activity.getId());
                relation.setGmtModified(dateProvider.getCurrentTime());
                relationDAO.save(relation.clone(PromotionActivityGoodsRelationDO.class));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
