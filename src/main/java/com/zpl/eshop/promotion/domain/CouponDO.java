package com.zpl.eshop.promotion.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 优惠券DO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 13:54
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class CouponDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券类型
     */
    private Integer type;

    /**
     * 优惠券规则
     */
    private String rule;

    /**
     * 有效期开始时间
     */
    private Date validStartTime;

    /**
     * 有效期结束时间
     */
    private Date validEndTime;

    /**
     * 优惠券发行数量
     */
    private Long giveOutCount;

    /**
     * 优惠券已被领取数量
     */
    private Long receivedCount;

    /**
     * 领取方式
     */
    private Integer giveOutType;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
