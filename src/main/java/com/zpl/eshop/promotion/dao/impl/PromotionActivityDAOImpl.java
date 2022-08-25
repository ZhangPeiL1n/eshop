package com.zpl.eshop.promotion.dao.impl;

import com.zpl.eshop.promotion.dao.PromotionActivityDAO;
import com.zpl.eshop.promotion.domain.PromotionActivityDO;
import com.zpl.eshop.promotion.domain.PromotionActivityQuery;
import com.zpl.eshop.promotion.mapper.PromotionActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 促销活动管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/22 21:37
 **/
@Repository
public class PromotionActivityDAOImpl implements PromotionActivityDAO {

    /**
     * 促销活动管理模块Mapper组件
     */
    @Autowired
    private PromotionActivityMapper promotionActivityMapper;

    /**
     * 分页查询促销活动
     *
     * @return 促销活动集合
     */
    @Override
    public List<PromotionActivityDO> listByPage(PromotionActivityQuery query) {
        return promotionActivityMapper.listByPage(query);
    }

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     */
    @Override
    public PromotionActivityDO getById(Long id) {
        return promotionActivityMapper.getById(id);
    }

    /**
     * 查询全部促销活动
     *
     * @return 促销活动
     */
    @Override
    public List<PromotionActivityDO> listAll() {
        return listAll();
    }

    /**
     * 查询指定商品目前可用的启用状态的促销活动
     *
     * @param goodsId 商品id
     * @return 促销活动列表
     */
    @Override
    public List<PromotionActivityDO> listEnabledByGoodsId(Long goodsId) {
        return promotionActivityMapper.listEnabledByGoodsId(goodsId);
    }

    /**
     * 新增促销活动
     *
     * @param activity 促销活动
     */
    @Override
    public Long save(PromotionActivityDO activity) {
        promotionActivityMapper.save(activity);
        return activity.getId();
    }

    /**
     * 更新促销活动
     *
     * @param activity 促销活动
     */
    @Override
    public void update(PromotionActivityDO activity) {
        promotionActivityMapper.update(activity);
    }

    /**
     * 更新促销活动状态
     *
     * @param id     促销活动id
     * @param status 促销活动状态
     */
    @Override
    public void updateStatus(Long id, Integer status) {
        promotionActivityMapper.updateStatus(id, status);
    }

    /**
     * 删除促销活动
     *
     * @param id 促销活动id
     */
    @Override
    public void remove(Long id) {
        promotionActivityMapper.remove(id);
    }
}
