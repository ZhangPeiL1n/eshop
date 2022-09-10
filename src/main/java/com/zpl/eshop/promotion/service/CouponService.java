package com.zpl.eshop.promotion.service;

import com.zpl.eshop.promotion.domain.CouponDTO;
import com.zpl.eshop.promotion.domain.CouponQuery;

import java.util.List;

/**
 * 优惠券管理模块Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/11 1:09
 **/
public interface CouponService {

    /**
     * 分页查询优惠券
     *
     * @param query 查询条件
     * @return 优惠券
     */
    List<CouponDTO> listByPage(CouponQuery query) throws Exception;

    /**
     * 新增优惠券
     *
     * @param coupon 优惠券
     */
    void save(CouponDTO coupon) throws Exception;

    /**
     * 根据id查询优惠券
     *
     * @param id 优惠券id
     * @return 优惠券
     */
    CouponDTO getById(Long id) throws Exception;

    /**
     * 更新优惠券
     *
     * @param coupon 优惠券
     */
    Boolean update(CouponDTO coupon) throws Exception;

    /**
     * 删除优惠券
     *
     * @param id 优惠券id
     */
    Boolean remove(Long id);
}
