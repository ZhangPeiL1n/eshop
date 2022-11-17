package com.zpl.eshop.promotion.dao;

import com.zpl.eshop.promotion.domain.CouponDO;
import com.zpl.eshop.promotion.domain.CouponQuery;

import java.util.List;

/**
 * 优惠券管理DAO接口
 *
 * @author ZhangPeiL1n
 */
public interface CouponDAO {

    /**
     * 分页查询优惠券
     *
     * @param query 查询条件
     * @return 优惠券
     * @throws Exception
     */
    List<CouponDO> listByPage(CouponQuery query) throws Exception;

    /**
     * 新增优惠券
     *
     * @param coupon 优惠券
     * @throws Exception
     */
    void save(CouponDO coupon) throws Exception;

    /**
     * 根据id查询优惠券
     *
     * @param id 优惠券id
     * @return 优惠券
     * @throws Exception
     */
    CouponDO getById(Long id) throws Exception;

    /**
     * 更新优惠券
     *
     * @param coupon 优惠券
     * @throws Exception
     */
    void update(CouponDO coupon) throws Exception;

    /**
     * 删除优惠券
     *
     * @param id 优惠券id
     * @throws Exception
     */
    void remove(Long id) throws Exception;

    /**
     * 查询所有优惠券
     *
     * @return 所有优惠券
     * @throws Exception
     */
    List<CouponDO> listAll() throws Exception;

    /**
     * 更新优惠券状态
     *
     * @param coupon 优惠券
     * @throws Exception
     */
    void updateStatus(CouponDO coupon) throws Exception;
}
