package com.zpl.eshop.order.domain;

import lombok.Data;

/**
 * 订单查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/9/4 15:39
 **/
@Data
public class OrderInfoQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 每页条数
     */
    private Integer size;

    /**
     * 用户帐号id
     */
    private Long userAccountId;
}
