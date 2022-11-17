package com.zpl.eshop.promotion.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.promotion.dao.CouponDAO;
import com.zpl.eshop.promotion.domain.CouponDO;
import com.zpl.eshop.promotion.domain.CouponQuery;
import com.zpl.eshop.promotion.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券管理DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class CouponDAOImpl implements CouponDAO {

    /**
     * 优惠券管理mapper组件
     */
    @Autowired
    private CouponMapper couponMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询优惠券
     *
     * @param query 查询条件
     * @return 优惠券
     * @throws Exception
     */
    public List<CouponDO> listByPage(CouponQuery query) throws Exception {
        return couponMapper.listByPage(query);
    }

    /**
     * 新增优惠券
     *
     * @param coupon 优惠券
     * @throws Exception
     */
    public void save(CouponDO coupon) throws Exception {
        coupon.setGmtCreate(dateProvider.getCurrentTime());
        coupon.setGmtModified(dateProvider.getCurrentTime());
        couponMapper.save(coupon);
    }

    /**
     * 根据id查询优惠券
     *
     * @param id 优惠券id
     * @return 优惠券
     * @throws Exception
     */
    public CouponDO getById(Long id) throws Exception {
        return couponMapper.getById(id);
    }

    /**
     * 更新优惠券
     *
     * @param coupon 优惠券
     * @throws Exception
     */
    public void update(CouponDO coupon) throws Exception {
        coupon.setGmtModified(dateProvider.getCurrentTime());
        couponMapper.update(coupon);
    }

    /**
     * 删除优惠券
     *
     * @param id 优惠券id
     * @throws Exception
     */
    public void remove(Long id) throws Exception {
        couponMapper.remove(id);
    }

    /**
     * 查询所有优惠券
     *
     * @return 所有优惠券
     * @throws Exception
     */
    public List<CouponDO> listAll() throws Exception {
        return couponMapper.listAll();
    }

    /**
     * 更新优惠券状态
     *
     * @param coupon 优惠券
     * @throws Exception
     */
    public void updateStatus(CouponDO coupon) throws Exception {
        coupon.setGmtModified(dateProvider.getCurrentTime());
        couponMapper.updateStatus(coupon);
    }
}
