package com.zpl.eshop.customer.domain;

import lombok.Data;

/**
 * 退货工单查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 21:53
 **/
@Data
public class ReturnGoodsWorksheetQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 每页显示的数据条数
     */
    private Integer size;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 退货工单状态
     */
    private Integer status;

    /**
     * 退货原因
     */
    private Integer returnGoodsReason;

    /**
     * 退货快递单号
     */
    private String returnGoodsLogisticsCode;
}
