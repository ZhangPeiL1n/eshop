package com.zpl.eshop.commodity.domain;

import lombok.Data;

/**
 * 商品sku查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/9/27 17:59
 **/
@Data
public class GoodsSkuQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 每页显示数据条数
     */
    private Integer size;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品sku编号
     */
    private String goodsSkuCode;
}
