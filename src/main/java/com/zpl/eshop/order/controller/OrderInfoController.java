package com.zpl.eshop.order.controller;

import com.zpl.eshop.common.util.CloneDirection;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.order.domain.CalculateCouponDiscountPriceVO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderInfoQuery;
import com.zpl.eshop.order.domain.OrderInfoVO;
import com.zpl.eshop.order.service.OrderInfoService;
import com.zpl.eshop.promotion.domain.CouponDTO;
import com.zpl.eshop.promotion.domain.CouponVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @PostMapping("/price")
    public OrderInfoVO calculateOrderPrice(@RequestBody OrderInfoVO order) throws Exception {
        try {
            return orderInfoService.calculateOrderPrice(order.clone(OrderInfoDTO.class, CloneDirection.FORWARD)).clone(OrderInfoVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return order;
    }

    /**
     * 计算优惠券减免价格
     *
     * @param vo 计算优惠券抵扣金额VO
     * @return 计算金额后的订单
     */
    @PostMapping("/coupon")
    public OrderInfoVO calculateCouponDiscountPrice(@RequestBody CalculateCouponDiscountPriceVO vo) {
        try {
            OrderInfoVO order = vo.getOrder();
            CouponVO coupon = vo.getCoupon();
            return orderInfoService.calculateCouponDiscountPrice(order.clone(OrderInfoDTO.class, CloneDirection.FORWARD), coupon.clone(CouponDTO.class)).clone(OrderInfoVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
            return vo.getOrder();
        }
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

    /**
     * 分页查询订单
     *
     * @param query 查询条件
     * @return 订单集合
     * @throws Exception
     */
    @GetMapping("/")
    public List<OrderInfoVO> listByPage(OrderInfoQuery query) {
        try {
            List<OrderInfoDTO> orders = orderInfoService.listByPage(query);
            return ObjectUtils.convertList(orders, OrderInfoVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询订单
     *
     * @param id 订单id
     * @return 订单
     * @throws Exception
     */
    @GetMapping("/{id}")
    public OrderInfoVO getById(@PathVariable("id") Long id) {
        try {
            return orderInfoService.getById(id).clone(OrderInfoVO.class, CloneDirection.OPPOSITE);
        } catch (Exception e) {
            logger.error("error", e);
            return new OrderInfoVO();
        }
    }

    /**
     * 取消订单
     *
     * @param id 订单id
     * @return
     */
    @PutMapping("/cancel/{id}")
    public Boolean cancel(@PathVariable("id") Long id) {
        try {
            return orderInfoService.cancel(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 支付订单
     *
     * @param id 订单id
     * @return 支付二维码
     */
    @PutMapping("/pay/{id}")
    public String pay(@PathVariable("id") Long id) {
        try {
            return orderInfoService.pay(id);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
