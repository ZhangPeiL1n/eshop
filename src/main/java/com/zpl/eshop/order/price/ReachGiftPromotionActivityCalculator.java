package com.zpl.eshop.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.service.CommodityService;
import com.zpl.eshop.common.json.JsonExtractor;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 满赠类型促销活动的计算组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 20:37
 **/
@Component
public class ReachGiftPromotionActivityCalculator extends AbstractGiftPromotionActivityCalculator implements PromotionActivityCalculator {

    /**
     * json 字段值提取器
     */
    @Autowired
    private JsonExtractor jsonExtractor;

    /**
     * 商品中心service
     */
    @Autowired
    private CommodityService commodityService;

    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) throws Exception {
        double totalAmount = item.getPurchasePrice() * item.getPurchaseQuantity();
        JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
        Double thresholdAmount = jsonExtractor.getDouble(rule, "thresholdAmount");
        JSONArray giftGoodsSkuIds = rule.getJSONArray("giftGoodsSkuIds");

        if (totalAmount > thresholdAmount) {
            PromotionActivityResult result = new PromotionActivityResult();
            for (int i = 0; i < giftGoodsSkuIds.size(); i++) {
                Long goodsSkuId = giftGoodsSkuIds.getLong(i);
                GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(goodsSkuId);
                result.getOrderItems().add(createItem(goodsSku));
            }
            return result;
        }
        return new PromotionActivityResult();
    }
}
