package com.zpl.eshop.logistics.api;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/22 23:49
 **/
public interface LogisticApi {

    /**
     * 创建电子面单
     *
     * @param request 请求
     * @return 响应
     * @throws Exception
     */
    CreateEOrderResponse createEOrder(CreateEOrderRequest request) throws Exception;

    /**
     * 查询物流进度
     *
     * @param request 请求
     * @return 响应
     * @throws Exception
     */
    QueryProgressResponse queryProgress(QueryProgressRequest request) throws Exception;
}
