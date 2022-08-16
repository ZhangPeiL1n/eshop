package com.zpl.eshop.order.controller;

import com.zpl.eshop.common.util.CloneDirection;
import com.zpl.eshop.membership.domain.DeliveryAddressDTO;
import com.zpl.eshop.membership.domain.DeliveryAddressVO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderInfoVO;
import com.zpl.eshop.order.service.OrderInfoService;
import com.zpl.eshop.promotion.domain.CouponDTO;
import com.zpl.eshop.promotion.domain.CouponVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理 controller 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 15:16
 **/
@RestController
@RequestMapping("/order")
public class OrderInfoController {


    private static final Logger logger = LoggerFactory.getLogger(OrderInfoController.class);

    /**
     * 订单管理 service 组件
     */
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 计算订单价格
     *
     * @param order 订单
     * @return 计算金额后的订单
     */
    @GetMapping("/price")
    public OrderInfoVO calculateOrderPrice(OrderInfoVO order, DeliveryAddressVO deliveryAddress) throws Exception {
        try {
            return orderInfoService.calculateOrderPrice(order.clone(OrderInfoDTO.class, CloneDirection.FORWARD), deliveryAddress.clone(DeliveryAddressDTO.class)).clone(OrderInfoVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return order;
    }

    /**
     * 计算优惠券减免价格
     *
     * @param order 订单
     * @return 计算金额后的订单
     */
    @GetMapping("/coupon")
    public OrderInfoVO calculateCouponDiscountPrice(OrderInfoVO order, CouponVO coupon) {
        try {
            return orderInfoService.calculateCouponDiscountPrice(order.clone(OrderInfoDTO.class, CloneDirection.FORWARD), coupon.clone(CouponDTO.class)).clone(OrderInfoVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return order;
    }

    /**
     * 新增订单
     *
     * @param order 订单
     * @return
     */
    @PostMapping("/")
    public OrderInfoVO save(@RequestBody OrderInfoVO order) {
        try {
            OrderInfoDTO targetOrder = orderInfoService.save(order.clone(OrderInfoDTO.class, CloneDirection.FORWARD));
            return targetOrder.clone(OrderInfoVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
            return new OrderInfoVO();
        }
    }
}
