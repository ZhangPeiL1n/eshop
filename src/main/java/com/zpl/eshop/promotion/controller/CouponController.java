package com.zpl.eshop.promotion.controller;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.promotion.domain.CouponDTO;
import com.zpl.eshop.promotion.domain.CouponQuery;
import com.zpl.eshop.promotion.domain.CouponVO;
import com.zpl.eshop.promotion.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券管理 controller 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 14:12
 **/
@RestController
@RequestMapping("/promotion/coupon")
public class CouponController {

    private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

    /**
     * 优惠券管理service组件
     */
    @Autowired
    private CouponService couponService;

    /**
     * 查询用户可以使用的有效优惠券
     *
     * @param userAccountId 帐号id
     * @return 有效优惠券
     */
    @GetMapping("/user/{userAccountId}")
    public List<CouponVO> listValidByUserAccountId(@PathVariable("userAccountId") Long userAccountId) {
        return null;
    }

    /**
     * 分页查询优惠券
     *
     * @param query 查询条件
     * @return 优惠券
     */
    @GetMapping("/")
    public List<CouponVO> listByPage(CouponQuery query) {
        try {
            return ObjectUtils.convertList(couponService.listByPage(query), CouponVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 新增优惠券
     *
     * @param coupon 优惠券
     */
    @PostMapping("/")
    public Boolean save(@RequestBody CouponVO coupon) {
        try {
            couponService.save(coupon.clone(CouponDTO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 根据id查询优惠券
     *
     * @param id 优惠券id
     * @return 优惠券
     */
    @GetMapping("/{id}")
    public CouponVO getById(@PathVariable("id") Long id) {
        try {
            return couponService.getById(id).clone(CouponVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new CouponVO();
        }
    }

    /**
     * 更新优惠券
     *
     * @param coupon 优惠券
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody CouponVO coupon) {
        try {
            return couponService.update(coupon.clone(CouponDTO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除优惠券
     *
     * @param id 优惠券id
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            return couponService.remove(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
