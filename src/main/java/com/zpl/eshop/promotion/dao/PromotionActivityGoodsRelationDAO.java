package com.zpl.eshop.promotion.dao;

import com.zpl.eshop.promotion.domain.PromotionActivityGoodsRelationDO;

import java.util.List;

/**
 * 促销活动与商品关联关系DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/22 21:57
 **/
public interface PromotionActivityGoodsRelationDAO {

    /**
     * 根据促销活动id查询关联关系
     *
     * @param activityId 促销活动id
     * @return 关系
     */
    List<PromotionActivityGoodsRelationDO> listByActivityId(Long activityId);

    /**
     * 新增促销活动与商品关联关系
     *
     * @param relation 关联关系
     */
    void save(PromotionActivityGoodsRelationDO relation);

    /**
     * 根据促销活动id删除
     *
     * @param promotionActivityId 促销活动id
     */
    void removeByActivityId(Long promotionActivityId);
}
