package com.zpl.eshop.logistics.service.impl;

import com.zpl.eshop.logistics.service.LogisticsService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.LogisticOrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 物流中心接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 22:22
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class LogisticsServiceImpl implements LogisticsService {

    /**
     * 计算商品 sku 的运费
     *
     * @param order     订单
     * @param orderItem 订单条目
     * @return 商品 sku 的运费
     */
    @Override
    public Double calculateFreight(OrderInfoDTO order, OrderItemDTO orderItem) {
        return 5.5;
    }

    /**
     * 申请物流单
     *
     * @param order 订单
     * @return 物流单
     */
    @Override
    public LogisticOrderDTO applyLogisticOrder(OrderInfoDTO order) {
        return null;
    }
}
