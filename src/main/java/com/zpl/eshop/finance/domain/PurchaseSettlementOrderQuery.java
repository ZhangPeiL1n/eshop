package com.zpl.eshop.finance.domain;

import lombok.Data;

/**
 * 采购结算单查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/12 21:13
 **/
@Data
public class PurchaseSettlementOrderQuery {

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
     * 采购结算单状态
     */
    private Integer status;
}
