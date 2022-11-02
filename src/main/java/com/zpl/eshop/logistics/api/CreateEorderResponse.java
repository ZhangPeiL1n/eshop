package com.zpl.eshop.logistics.api;

import lombok.Data;

/**
 * 创建电子面单接口的响应结果
 *
 * @author ZhangPeiL1n
 * @date 2022/10/22 23:54
 **/
@Data
public class CreateEorderResponse {

    public static final Integer SUCCESS = 1;
    public static final Integer FAILURE = 0;

    /**
     * 物流单号
     */
    private String logisticCode;

    /**
     * 物流单内容
     */
    private String logisticOrderContent;

    /**
     * 响应状态
     */
    private Integer status;
}
