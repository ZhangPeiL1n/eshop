package com.zpl.eshop.promotion.dao;

import com.zpl.eshop.promotion.domain.PromotionActivityGoodsRelationDO;

import java.util.List;

/**
 * 促销活动与商品关联关系管理的DAO组件接口
 *
 * @author ZhangPeiL1n
 */
public interface PromotionActivityGoodsRelationDAO {

    /**
     * 根据促销活动id查询促销活动与商品的关联关系
     *
     * @param promotionActivityId 促销活动id
     * @return 促销活动与商品的关联关系
     * @throws Exception
     */
    List<PromotionActivityGoodsRelationDO> listByActivityId(Long promotionActivityId) throws Exception;

    /**
     * 新增促销活动与商品的关联关系
     *
     * @param relation 促销活动与商品的关联关系
     * @throws Exception
     */
    void save(PromotionActivityGoodsRelationDO relation) throws Exception;

    /**
     * 删除促销活动对应的与商品的关联关系
     *
     * @param promotionActivityId 促销活动id
     * @throws Exception
     */
    void removeByActivityId(Long promotionActivityId) throws Exception;

}
