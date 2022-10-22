package com.zpl.eshop.logistics.api;

import lombok.Data;

/**
 * 查询物流进度的请求
 *
 * @author ZhangPeiL1n
 * @date 2022/10/22 23:56
 **/
@Data
public class QueryProgressRequest {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 物流单号
     */
    private String logisticCode;
}
