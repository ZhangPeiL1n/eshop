package com.zpl.eshop.promotion.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zpl.eshop.common.json.DateJsonDeserializer;
import com.zpl.eshop.common.json.DateJsonSerializer;
import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 优惠券
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CouponVO extends AbstractObject {

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
     * 优惠券使用规则
     */
    private String rule;

    /**
     * 有效期开始时间
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private Date validStartTime;

    /**
     * 有效期结束时间
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private Date validEndTime;

    /**
     * 发行总数量
     */
    private Long giveOutCount;

    /**
     * 已经领取的数量
     */
    private Long receivedCount;

    /**
     * 发行方式
     */
    private Integer giveOutType;

    /**
     * 优惠券状态
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
