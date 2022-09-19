package com.zpl.eshop.promotion.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 优惠券领取记录
 *
 * @author ZhangPeiL1n
 * @date 2022/9/18 18:41
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class CouponAchieveVO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 用户账号id
     */
    private Long userAccountId;

    /**
     * 是否使用过这个优惠券
     */
    private Integer used;

    /**
     * 使用优惠券的时间
     */
    private Date usedTime;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
