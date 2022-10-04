package com.zpl.eshop.order.service;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderInfoQuery;
import com.zpl.eshop.promotion.domain.CouponDTO;

import java.util.List;

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
    OrderInfoDTO calculateOrderPrice(OrderInfoDTO order) throws Exception;

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

    /**
     * 分页查询订单
     *
     * @param query 分页查询条件
     * @return 订单
     */
    List<OrderInfoDTO> listByPage(OrderInfoQuery query) throws Exception;

    /**
     * 获取订单详情
     *
     * @param id 订单id
     * @return 订单
     */
    OrderInfoDTO getById(Long id) throws Exception;

    /**
     * 取消订单
     *
     * @param id 订单id
     * @return 处理结果
     * @throws Exception
     */
    Boolean cancel(Long id) throws Exception;

    /**
     * 支付订单
     *
     * @param id 订单id
     * @return 处理结果
     * @throws Exception
     */
    String pay(Long id) throws Exception;

}
