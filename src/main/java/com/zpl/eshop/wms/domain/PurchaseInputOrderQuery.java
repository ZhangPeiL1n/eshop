package com.zpl.eshop.wms.domain;

import lombok.Data;

/**
 * 采购入库单查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 16:47
 **/
@Data
public class PurchaseInputOrderQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 每页显示的数据条数
     */
    private Integer size;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 采购入库单状态
     */
    private Integer status;
}
