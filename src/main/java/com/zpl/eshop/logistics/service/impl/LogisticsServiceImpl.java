package com.zpl.eshop.logistics.service.impl;

import com.zpl.eshop.commodity.domain.GoodsDTO;
import com.zpl.eshop.commodity.service.CommodityService;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.logistics.api.CreateEOrderRequest;
import com.zpl.eshop.logistics.api.CreateEOrderRequestBuilder;
import com.zpl.eshop.logistics.api.CreateEOrderResponse;
import com.zpl.eshop.logistics.api.LogisticApi;
import com.zpl.eshop.logistics.domain.FreightTemplateDTO;
import com.zpl.eshop.logistics.service.FreightTemplateService;
import com.zpl.eshop.logistics.service.LogisticsService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.LogisticOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(LogisticsServiceImpl.class);

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 商品中心接口
     */
    @Autowired
    private CommodityService commodityService;

    /**
     * 运费模版管理Service组件
     */
    @Autowired
    private FreightTemplateService freightTemplateService;

    /**
     * 运费模版工厂
     */
    @Autowired
    private FreightCalculatorFactory freightCalculatorFactory;

    /**
     * 物流api接口
     */
    @Autowired
    private LogisticApi logisticApi;

    /**
     * 计算商品 sku 的运费
     *
     * @param order     订单
     * @param orderItem 订单条目
     * @return 商品 sku 的运费
     */
    @Override
    public Double calculateFreight(OrderInfoDTO order, OrderItemDTO orderItem) throws Exception {
        try {
            // 获取商品条目对应的运费模版
            Long goodsId = orderItem.getGoodsId();
            GoodsDTO goods = commodityService.getGoodsById(goodsId);
            FreightTemplateDTO freightTemplate = freightTemplateService.getById(goods.getFreightTemplateId());

            // 获取运费计算器
            FreightCalculator freightCalculator = freightCalculatorFactory.get(freightTemplate);

            return freightCalculator.calculate(freightTemplate, order, orderItem);
        } catch (Exception e) {
            logger.error("error", e);
            return 0.0;
        }
    }

    /**
     * 申请物流单
     *
     * @param order 订单
     * @return 物流单
     */
    @Override
    public LogisticOrderDTO applyLogisticOrder(OrderInfoDTO order) {
        try {
            CreateEOrderRequest request = CreateEOrderRequestBuilder.get()
                    .buildOrderRelatedInfo(order)
                    .buildReceiver(order)
                    .buildGoodsList(order)
                    .buildTotalDataMetric(order)
                    .create();

            CreateEOrderResponse response = logisticApi.createEOrder(request);

            LogisticOrderDTO logisticOrder = new LogisticOrderDTO();
            logisticOrder.setLogisticCode(response.getLogisticCode());
            logisticOrder.setContent(response.getLogisticOrderContent());
            logisticOrder.setGmtCreate(dateProvider.getCurrentTime());
            logisticOrder.setGmtModified(dateProvider.getCurrentTime());

            return logisticOrder;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
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
        try {
            return dateProvider.getCurrentTime();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
