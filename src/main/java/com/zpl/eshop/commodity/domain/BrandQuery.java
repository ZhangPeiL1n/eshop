package com.zpl.eshop.commodity.domain;

import lombok.Data;

/**
 * 品牌查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/8 21:33
 **/
@Data
public class BrandQuery {

    /**
     * 分页起始位置
     */
    private Integer offset;

    /**
     * 每页记录数
     */
    private Integer size;


    /**
     * 品牌中文名
     */
    private String chineseName;

    /**
     * 品牌英文名
     */
    private String englishName;

    /**
     * 品牌别名
     */
    private String aliasName;
}
