package com.zpl.eshop.schedule.service.impl;

import com.zpl.eshop.common.bean.SpringApplicationContext;
import com.zpl.eshop.schedule.service.SaleDeliveryOrderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 销售出库单builder工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/8/18 16:44
 **/
@Component
public class SaleDeliveryOrderBuilderFactory {

    /**
     * spring 容器
     */
    @Autowired
    private SpringApplicationContext context;

    /**
     * 获取Builder
     *
     * @return builder
     */
    public SaleDeliveryOrderBuilder get() {
        return context.getBean(SaleDeliveryOrderBuilder.class);
    }

}
