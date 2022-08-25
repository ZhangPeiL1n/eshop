package com.zpl.eshop.promotion.dao;

import com.zpl.eshop.promotion.domain.PromotionActivityDO;
import com.zpl.eshop.promotion.domain.PromotionActivityQuery;

import java.util.List;

/**
 * 促销活动管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/22 21:33
 **/
public interface PromotionActivityDAO {

    /**
     * 分页查询促销活动
     *
     * @return 促销活动集合
     */
    List<PromotionActivityDO> listByPage(PromotionActivityQuery query);

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     */
    PromotionActivityDO getById(Long id);

    /**
     * 查询全部促销活动
     *
     * @return 促销活动
     */
    List<PromotionActivityDO> listAll();

    /**
     * 查询指定商品目前可用的启用状态的促销活动
     *
     * @param goodsId 商品id
     * @return 促销活动列表
     */
    List<PromotionActivityDO> listEnabledByGoodsId(Long goodsId);

    /**
     * 新增促销活动
     *
     * @param activity 促销活动
     */
    Long save(PromotionActivityDO activity);

    /**
     * 更新促销活动
     *
     * @param activity 促销活动
     */
    void update(PromotionActivityDO activity);

    /**
     * 更新促销活动状态
     *
     * @param id     促销活动id
     * @param status 促销活动状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 删除促销活动
     *
     * @param id 促销活动id
     */
    void remove(Long id);
}
