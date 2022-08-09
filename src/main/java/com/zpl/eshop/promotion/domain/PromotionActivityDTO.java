package com.zpl.eshop.promotion.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 促销活动 DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:25
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionActivityDTO extends AbstractObject {
    /**
     * id
     */
    private Long id;

    /**
     * 促销活动名称
     */
    private String name;

    /**
     * 促销活动开始时间
     */
    private Date startTime;

    /**
     * 促销活动结束时间
     */
    private Date endTime;

    /**
     * 促销活动备注
     */
    private String remark;

    /**
     * 促销活动状态
     */
    private Integer status;

    /**
     * 促销活动规则
     */
    private String rule;

    /**
     * 促销活动类型
     */
    private Integer type;

    /**
     * 促销活动创建时间
     */
    private Date gmtCreate;

    /**
     * 促销活动修改时间
     */
    private Date gmtModified;
}
