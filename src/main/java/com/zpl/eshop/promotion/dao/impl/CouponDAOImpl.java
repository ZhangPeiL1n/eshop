package com.zpl.eshop.promotion.dao.impl;

import com.zpl.eshop.promotion.dao.CouponDAO;
import com.zpl.eshop.promotion.domain.CouponDO;
import com.zpl.eshop.promotion.domain.CouponQuery;
import com.zpl.eshop.promotion.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/9/11 1:06
 **/
@Repository
public class CouponDAOImpl implements CouponDAO {

    /**
     * 优惠券管理Mapper组件
     */
    @Autowired
    private CouponMapper couponMapper;

    /**
     * 分页查询优惠券
     *
     * @param query 查询条件
     * @return 优惠券
     */
    @Override
    public List<CouponDO> listByPage(CouponQuery query) {
        return couponMapper.listByPage(query);
    }

    /**
     * 新增优惠券
     *
     * @param coupon 优惠券
     */
    @Override
    public void save(CouponDO coupon) {
        couponMapper.save(coupon);
    }

    /**
     * 根据id查询优惠券
     *
     * @param id 优惠券id
     * @return 优惠券
     */
    @Override
    public CouponDO getById(Long id) {
        return couponMapper.getById(id);
    }

    /**
     * 更新优惠券
     *
     * @param coupon 优惠券
     */
    @Override
    public void update(CouponDO coupon) {
        couponMapper.update(coupon);
    }

    /**
     * 删除优惠券
     *
     * @param id 优惠券id
     */
    @Override
    public void remove(Long id) {
        couponMapper.remove(id);
    }
}
