package com.zpl.eshop.pay.domain;

import lombok.Data;

/**
 * 支付流水查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/11/1 21:19
 **/
@Data
public class PayTransactionQuery {
    /**
     * 分页查询起始位置
     */
    private Integer offset;
    /**
     * 每页显示的数据条数
     */
    private Integer size;
}
