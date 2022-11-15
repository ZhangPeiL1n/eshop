package com.zpl.eshop.logistics.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.logistics.service.LogisticsService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.LogisticOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;


    /**
     * 计算商品 sku 的运费
     *
     * @param order     订单
     * @param orderItem 订单条目
     * @return 商品 sku 的运费
     * @throws Exception
     */
    @Override
    public Double calculateFreight(OrderInfoDTO order, OrderItemDTO orderItem) throws Exception {
        return 5.5;
    }

    /**
     * 申请物流单
     *
     * @param order 订单
     * @return 物流单
     * @throws Exception
     */
    @Override
    public LogisticOrderDTO applyLogisticOrder(OrderInfoDTO order) throws Exception {
        return null;
    }

    /**
     * 获取订单签收时间
     *
     * @param orderId 订单id
     * @param orderNo 订单编号
     * @return 签收时间
     */
    @Override
    public Date getSignedTime(Long orderId, String orderNo) throws Exception {
        return dateProvider.getCurrentTime();
    }
}
