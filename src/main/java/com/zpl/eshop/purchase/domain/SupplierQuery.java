package com.zpl.eshop.purchase.domain;

import lombok.Data;

/**
 * 供应商查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:34
 **/
@Data
public class SupplierQuery {
    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 每页显示的数据条数
     */
    private Integer size;

    /**
     * 供应商名称
     */
    private String name;
}
