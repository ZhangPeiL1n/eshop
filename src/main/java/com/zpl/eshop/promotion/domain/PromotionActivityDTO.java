package com.zpl.eshop.promotion.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 促销活动DTO
 *
 * @author ZhangPeiL1n
 */
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
     * 促销活动的创建时间
     */
    private Date gmtCreate;

    /**
     * 促销活动的修改时间
     */
    private Date gmtModified;

    /**
     * 促销活动与商品的关联关系
     */
    private List<PromotionActivityGoodsRelationDTO> relations;
}
