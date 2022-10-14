package com.zpl.eshop.wms.domain;

import lombok.Data;

/**
 * 销售出库单查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:05
 **/
@Data
public class SaleDeliveryOrderQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 每页显示的数据条数
     */
    private Integer size;

    /**
     * 销售出库单状态
     */
    private Integer status;
}
