package com.zpl.eshop.promotion.service;

import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityQuery;

import java.util.List;

/**
 * 促销活动管理模块Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/22 22:04
 **/
public interface PromotionActivityService {

    /**
     * 分页查询促销活动
     *
     * @return 促销活动集合
     */
    List<PromotionActivityDTO> listByPage(PromotionActivityQuery query) throws Exception;

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     */
    PromotionActivityDTO getById(Long id) throws Exception;

    /**
     * 新增促销活动
     *
     * @param activity 促销活动
     */
    void save(PromotionActivityDTO activity) throws Exception;

    /**
     * 更新促销活动
     *
     * @param activity 促销活动
     */
    void update(PromotionActivityDTO activity) throws Exception;

    /**
     * 删除促销活动
     *
     * @param id 促销活动id
     */
    void remove(Long id);
}
