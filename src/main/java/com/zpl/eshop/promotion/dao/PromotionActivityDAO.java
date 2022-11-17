package com.zpl.eshop.promotion.dao;

import com.zpl.eshop.promotion.domain.PromotionActivityDO;
import com.zpl.eshop.promotion.domain.PromotionActivityQuery;

import java.util.List;

/**
 * 促销活动管理DAO组件接口
 *
 * @author ZhangPeiL1n
 */
public interface PromotionActivityDAO {

    /**
     * 分页查询促销活动
     *
     * @param query 查询条件
     * @return 促销活动
     * @throws Exception
     */
    List<PromotionActivityDO> listByPage(PromotionActivityQuery query) throws Exception;

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     * @throws Exception
     */
    PromotionActivityDO getById(Long id) throws Exception;

    /**
     * 查询全部促销活动
     *
     * @return 促销活动
     * @throws Exception
     */
    List<PromotionActivityDO> listAll() throws Exception;

    /**
     * 查询指定商品目前可以使用的启用状态的促销活动
     *
     * @param goodsId 商品id
     * @return 促销活动
     * @throws Exception
     */
    List<PromotionActivityDO> listEnabledByGoodsId(Long goodsId) throws Exception;

    /**
     * 新增促销活动
     *
     * @param activity 促销活动
     * @return id
     * @throws Exception
     */
    Long save(PromotionActivityDO activity) throws Exception;

    /**
     * 更新促销活动
     *
     * @param activity 促销活动
     * @throws Exception
     */
    void update(PromotionActivityDO activity) throws Exception;

    /**
     * 更新促销活动的状态
     *
     * @param activity 活动
     * @throws Exception
     */
    void updateStatus(PromotionActivityDO activity) throws Exception;

    /**
     * 删除促销活动
     *
     * @param id 促销活动id
     * @throws Exception
     */
    void remove(Long id) throws Exception;
}
