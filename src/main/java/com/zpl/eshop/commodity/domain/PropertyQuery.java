package com.zpl.eshop.commodity.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品属性的查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/19 21:14
 **/
@Getter
@Setter
public class PropertyQuery {

    /**
     * 分页的起始位置
     */
    private Integer offset;

    /**
     * 每一页查询多少数据
     */
    private Integer size;

    /**
     * 属性名称
     */
    private String propertyName;
}
