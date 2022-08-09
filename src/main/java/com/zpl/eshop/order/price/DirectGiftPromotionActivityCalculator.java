package com.zpl.eshop.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.service.CommodityService;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 赠品类型促销活动的计算组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 20:37
 **/
@Component
public class DirectGiftPromotionActivityCalculator extends AbstractGiftPromotionActivityCalculator implements PromotionActivityCalculator {

    /**
     * 商品中心service
     */
    @Autowired
    private CommodityService commodityService;

    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) {
        JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
        JSONArray giftGoodsSkuIds = rule.getJSONArray("giftGoodsSkuIds");

        PromotionActivityResult result = new PromotionActivityResult();
        for (int i = 0; i < giftGoodsSkuIds.size(); i++) {
            Long goodsSkuId = giftGoodsSkuIds.getLong(i);
            GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(goodsSkuId);
            result.getOrderItems().add(createItem(goodsSku));
        }
        return result;
    }
}
