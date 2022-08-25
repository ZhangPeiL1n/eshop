package com.zpl.eshop.promotion.domain;

import lombok.Data;

/**
 * 促销活动查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/21 17:56
 **/
@Data
public class PromotionActivityQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 分页查询记录数
     */
    private Integer size;

    /**
     * 促销活动名称
     */
    private String name;

    /**
     * 促销活动起始时间
     */
    private String startTime;

    /**
     * 促销活动结束时间
     */
    private String endTime;

    /**
     * 促销活动状态
     */
    private Integer status;

    /**
     * 促销活动类型
     */
    private Integer type;
}
