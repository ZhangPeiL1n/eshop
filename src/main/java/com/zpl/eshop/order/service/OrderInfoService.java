package com.zpl.eshop.order.service;

import com.zpl.eshop.membership.domain.DeliveryAddressDTO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.promotion.domain.CouponDTO;

/**
 * 订单管理Service
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 15:21
 **/
public interface OrderInfoService {

    /**
     * 计算订单价格
     *
     * @param order 订单
     * @return 计算金额后的订单
     */
    OrderInfoDTO calculateOrderPrice(OrderInfoDTO order, DeliveryAddressDTO deliveryAddress) throws Exception;

    /**
     * 计算优惠券减免价格
     *
     * @param order 订单
     * @return 计算金额后的订单
     */
    OrderInfoDTO calculateCouponDiscountPrice(OrderInfoDTO order, CouponDTO coupon);

    /**
     * 保存订单
     *
     * @param order 订单
     */
    OrderInfoDTO save(OrderInfoDTO order) throws Exception;
}
