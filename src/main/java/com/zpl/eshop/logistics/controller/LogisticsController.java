package com.zpl.eshop.logistics.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 物流中心Controller
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:21
 **/
@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    /**
     * 获取订单的物流进度
     *
     * @param orderId 订单id
     * @param orderNo 订单编号
     * @return 物流进度
     */
    public String getLogisticsProgress(Long orderId, String orderNo) {
        return null;
    }
}
