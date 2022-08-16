package com.zpl.eshop.promotion.controller;

import com.zpl.eshop.promotion.domain.CouponVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
