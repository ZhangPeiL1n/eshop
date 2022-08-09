package com.zpl.eshop.order.price;

import com.zpl.eshop.order.domain.OrderItemDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 促销活动处理结果
 *
 * @author ZhangPeiL1n
 * @date 2022/8/9 21:28
 **/
@Data
public class PromotionActivityResult {

    /**
     * 促销减免金额
     */
    private Double discountAmount = 0.0;

    /**
     * 赠品对应的订单条目
     */
    private List<OrderItemDTO> orderItems = new ArrayList<>();

    public PromotionActivityResult() {
    }

    public PromotionActivityResult(Double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
