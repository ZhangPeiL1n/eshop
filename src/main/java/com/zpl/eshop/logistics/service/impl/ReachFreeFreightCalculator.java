package com.zpl.eshop.logistics.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.common.json.JsonExtractor;
import com.zpl.eshop.logistics.domain.FreightTemplateDTO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 满x元包邮运费计算器
 *
 * @author ZhangPeiL1n
 * @date 2022/10/18 10:12
 **/
@Component
public class ReachFreeFreightCalculator implements FreightCalculator {

    /**
     * json 字段值提取器
     */
    @Autowired
    private JsonExtractor jsonExtractor;

    /**
     * 计算订单条目的运费
     *
     * @param freightTemplate 运费模版
     * @param order           订单
     * @param orderItem       订单条目
     * @return 运费
     * @throws Exception
     */
    @Override
    public Double calculate(FreightTemplateDTO freightTemplate, OrderInfoDTO order, OrderItemDTO orderItem) throws Exception {
        JSONObject rule = JSONObject.parseObject(freightTemplate.getRule());
        Double threshold = jsonExtractor.getDouble(rule, "threshold");
        Double lessThanThresholdFreight = jsonExtractor.getDouble(rule, "less_than_threshold_freight");
        double amount = orderItem.getPurchasePrice() * orderItem.getPurchaseQuantity();
        return amount >= threshold ? 0.0 : lessThanThresholdFreight;
    }
}
