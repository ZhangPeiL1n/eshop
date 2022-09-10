package com.zpl.eshop.promotion.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.promotion.dao.CouponDAO;
import com.zpl.eshop.promotion.domain.CouponDO;
import com.zpl.eshop.promotion.domain.CouponDTO;
import com.zpl.eshop.promotion.domain.CouponQuery;
import com.zpl.eshop.promotion.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 优惠券管理模块Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/9/11 1:10
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CouponServiceImpl implements CouponService {

    /**
     * 优惠券管理DAO组件
     */
    @Autowired
    private CouponDAO couponDAO;

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
     */
    @Override
    public List<CouponDTO> listByPage(CouponQuery query) throws Exception {
        return ObjectUtils.convertList(couponDAO.listByPage(query), CouponDTO.class);
    }

    /**
     * 新增优惠券
     *
     * @param coupon 优惠券
     */
    @Override
    public void save(CouponDTO coupon) throws Exception {
        coupon.setGmtCreate(dateProvider.getCurrentTime());
        coupon.setGmtModified(dateProvider.getCurrentTime());
        couponDAO.save(coupon.clone(CouponDO.class));
    }

    /**
     * 根据id查询优惠券
     *
     * @param id 优惠券id
     * @return 优惠券
     */
    @Override
    public CouponDTO getById(Long id) throws Exception {
        return couponDAO.getById(id).clone(CouponDTO.class);
    }

    /**
     * 更新优惠券
     *
     * @param coupon 优惠券
     */
    @Override
    public Boolean update(CouponDTO coupon) throws Exception {
        if (coupon.getReceivedCount() > 0) {
            return false;
        }
        couponDAO.update(coupon.clone(CouponDO.class));
        return true;
    }

    /**
     * 删除优惠券
     *
     * @param id 优惠券id
     */
    @Override
    public Boolean remove(Long id) {
        CouponDO coupon = couponDAO.getById(id);
        if (coupon.getReceivedCount() > 0) {
            return false;
        }
        couponDAO.remove(id);
        return true;
    }
}
