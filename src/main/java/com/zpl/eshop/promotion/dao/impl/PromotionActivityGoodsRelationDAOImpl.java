package com.zpl.eshop.promotion.dao.impl;

import com.zpl.eshop.promotion.dao.PromotionActivityGoodsRelationDAO;
import com.zpl.eshop.promotion.domain.PromotionActivityGoodsRelationDO;
import com.zpl.eshop.promotion.mapper.PromotionActivityGoodsRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 促销活动商品关系DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/22 21:59
 **/
@Repository
public class PromotionActivityGoodsRelationDAOImpl implements PromotionActivityGoodsRelationDAO {

    /**
     * 促销活动商品关系管理模块 Mapper
     */
    @Autowired
    private PromotionActivityGoodsRelationMapper promotionActivityGoodsRelationMapper;

    /**
     * 根据促销活动id查询关联关系
     *
     * @param activityId 促销活动id
     * @return 关系
     */
    @Override
    public List<PromotionActivityGoodsRelationDO> listByActivityId(Long activityId) {
        return promotionActivityGoodsRelationMapper.listByActivityId(activityId);
    }

    /**
     * 新增促销活动与商品关联关系
     *
     * @param relation 关联关系
     */
    @Override
    public void save(PromotionActivityGoodsRelationDO relation) {
        promotionActivityGoodsRelationMapper.save(relation);
    }

    /**
     * 根据促销活动id删除
     *
     * @param promotionActivityId 促销活动id
     */
    @Override
    public void removeByActivityId(Long promotionActivityId) {
        promotionActivityGoodsRelationMapper.removeByActivityId(promotionActivityId);
    }
}
