package com.zpl.eshop.logistics.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.logistics.domain.FreightTemplateDTO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import org.springframework.stereotype.Component;

/**
 * 自定义规则运费计算器
 *
 * @author ZhangPeiL1n
 * @date 2022/10/18 10:47
 **/
@Component
public class CustomRuleFreightCalculator implements FreightCalculator {

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
        JSONArray rules = JSONObject.parseArray(freightTemplate.getRule());
        String province = getProvinceFromAddress(order.getDeliveryAddress());
        double totalGrossWeight = orderItem.getPurchaseQuantity() * orderItem.getGoodsGrossWeight();

        for (int i = 0; i < rules.size(); i++) {
            JSONObject rule = rules.getJSONObject(i);
            String provinces = rule.getString("provinces");
            if (!provinces.contains(province)) {
                continue;
            }
            Double threshold = rule.getDouble("threshold");
            Double thresholdFreight = rule.getDouble("threshold_freight");
            Double incrStep = rule.getDouble("incr_step");
            Double incrFreight = rule.getDouble("incr_freight");

            if (totalGrossWeight <= threshold) {
                return thresholdFreight;
            } else {
                return thresholdFreight + (totalGrossWeight - threshold) / incrStep * incrFreight;
            }
        }
        return 0.0;
    }

    /**
     * 从地址中提取省份
     *
     * @param address 地址
     * @return 省份
     */
    private String getProvinceFromAddress(String address) {
        return address.substring(0, address.indexOf("省"));
    }
}
