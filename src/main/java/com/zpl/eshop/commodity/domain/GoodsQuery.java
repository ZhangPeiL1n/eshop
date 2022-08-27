package com.zpl.eshop.commodity.domain;

import lombok.Data;

/**
 * 商品查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 21:05
 **/
@Data
public class GoodsQuery {

    /**
     * 起始位置
     */
    private Integer offset;

    /**
     * 大小
     */
    private Integer size;

    /**
     * 类目id
     */
    private Long categoryId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 编号
     */
    private String code;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品副名称
     */
    private String subName;

    /**
     * 状态
     */
    private Integer status;
}
