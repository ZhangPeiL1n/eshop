package com.zpl.eshop.order.price;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.service.CommodityService;
import com.zpl.eshop.logistics.service.LogisticsService;
import com.zpl.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 运费默认计算器
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 17:14
 **/
@Component
public class DefaultFreightCalculator implements FreightCalculator {

    /**
     * 物流中心接口
     */
    @Autowired
    private LogisticsService logisticsService;

    /**
     * 商品中心接口
     */
    @Autowired
    private CommodityService commodityService;

    /**
     * 计算运费
     *
     * @param item 订单条目
     * @return
     */
    @Override
    public Double calculate(OrderItemDTO item, PromotionActivityResult result) {
        GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(item.getGoodsSkuId());
        return logisticsService.calculateFreight(goodsSku);
    }
}
