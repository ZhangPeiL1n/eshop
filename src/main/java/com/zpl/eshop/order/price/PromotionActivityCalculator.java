package com.zpl.eshop.order.price;

import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;

/**
 * 促销活动处理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 17:08
 **/
public interface PromotionActivityCalculator {

    /**
     * 处理促销活动
     *
     * @param item              商品条目
     * @param promotionActivity 促销活动
     * @return
     */
    PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) throws Exception;
}
