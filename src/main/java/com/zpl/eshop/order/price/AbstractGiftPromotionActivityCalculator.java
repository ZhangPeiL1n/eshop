package com.zpl.eshop.order.price;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;

/**
 * @author ZhangPeiL1n
 * @date 2022/8/9 22:16
 **/
public class AbstractGiftPromotionActivityCalculator {


    /**
     * 创建订单条目
     *
     * @param goodsSku 商品sku
     * @return 订单条目
     */
    protected OrderItemDTO createItem(GoodsSkuDTO goodsSku) {
        OrderItemDTO item = new OrderItemDTO();
        item.setGoodsId(goodsSku.getGoodsId());
        item.setGoodsSkuId(goodsSku.getId());
        item.setGoodsSkuCode(goodsSku.getGoodsSkuCode());
        item.setGoodsName(goodsSku.getGoodsName());
        item.setSaleProperties(goodsSku.getSaleProperties());
        item.setGoodsGrossWeight(goodsSku.getGrossWeight());
        item.setPurchaseQuantity(1L);
        item.setPurchasePrice(0.0);
        item.setPromotionActivityId(null);
        item.setGoodsLength(goodsSku.getGoodsLength());
        item.setGoodsWidth(goodsSku.getGoodsWidth());
        item.setGoodsHeight(goodsSku.getGoodsHeight());
        return item;
    }

}
