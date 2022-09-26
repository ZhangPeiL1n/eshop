package com.zpl.eshop.promotion.dao;

import com.zpl.eshop.promotion.domain.CouponDO;
import com.zpl.eshop.promotion.domain.CouponQuery;

import java.util.List;

/**
 * 优惠券管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/11 1:05
 **/
public interface CouponDAO {

    /**
     * 分页查询优惠券
     *
     * @param query 查询条件
     * @return 优惠券
     */
    List<CouponDO> listByPage(CouponQuery query);

    /**
     * 查询所有优惠券
     *
     * @param query 查询条件
     * @return 优惠券
     */
    List<CouponDO> listAll();

    /**
     * 新增优惠券
     *
     * @param coupon 优惠券
     */
    void save(CouponDO coupon);

    /**
     * 根据id查询优惠券
     *
     * @param id 优惠券id
     * @return 优惠券
     */
    CouponDO getById(Long id);

    /**
     * 更新优惠券
     *
     * @param coupon 优惠券
     */
    void update(CouponDO coupon);

    /**
     * 删除优惠券
     *
     * @param id 优惠券id
     */
    void remove(Long id);
}
