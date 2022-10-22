package com.zpl.eshop.logistics.controller;

import com.zpl.eshop.logistics.api.LogisticApi;
import com.zpl.eshop.logistics.api.QueryProgressRequest;
import com.zpl.eshop.logistics.api.QueryProgressRequestBuilder;
import com.zpl.eshop.logistics.api.QueryProgressResponse;
import com.zpl.eshop.wms.service.WmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 物流中心Controller
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:21
 **/
@RestController
@RequestMapping("/logistics")
public class LogisticsController {
    private static final Logger logger = LoggerFactory.getLogger(LogisticsController.class);

    /**
     * wms中心接口
     */
    @Autowired
    private WmsService wmsService;

    /**
     * 物流api接口
     */
    @Autowired
    private LogisticApi logisticApi;

    /**
     * 获取订单的物流进度
     *
     * @param orderId 订单id
     * @param orderNo 订单编号
     * @return 物流进度
     */
    @GetMapping("/progress/{orderId}")
    public List<QueryProgressResponse.TraceEvent> getLogisticsProgress(@PathVariable("orderId") Long orderId, String orderNo) {
        try {
            QueryProgressRequest request = QueryProgressRequestBuilder.get()
                    .buildOrderNo(orderNo)
                    .buildLogisticCode(wmsService.getLogisticCode(orderId))
                    .create();

            QueryProgressResponse response = logisticApi.queryProgress(request);

            return response.getTraceEvents();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
