package com.zpl.eshop.order.price;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.service.CommodityService;
import com.zpl.eshop.logistics.service.LogisticsService;
import com.zpl.eshop.membership.domain.DeliveryAddressDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 含赠品的运费计算器
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 17:15
 **/
@Component
public class GiftIncludedFreightCalculator implements FreightCalculator {

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
     * @param item   订单条目
     * @param result
     * @return
     */
    @Override
    public Double calculate(OrderItemDTO item, DeliveryAddressDTO deliveryAddress, PromotionActivityResult result) {
        Double freight = 0.0;
        GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(item.getGoodsSkuId());
        freight += logisticsService.calculateFreight(goodsSku, deliveryAddress);
        List<OrderItemDTO> gifts = result.getOrderItems();
        for (OrderItemDTO gift : gifts) {
            GoodsSkuDTO giftGoodsSku = commodityService.getGoodsSkuById(gift.getGoodsSkuId());
            freight += logisticsService.calculateFreight(giftGoodsSku, deliveryAddress);
        }
        return freight;
    }
}
