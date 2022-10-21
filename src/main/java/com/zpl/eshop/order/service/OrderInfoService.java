package com.zpl.eshop.order.service;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderInfoQuery;
import com.zpl.eshop.order.domain.ReturnGoodsApplyDTO;
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
    OrderInfoDTO calculateCouponDiscountPrice(OrderInfoDTO order, CouponDTO coupon) throws Exception;

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

    /**
     * 手动确认收货
     *
     * @param id 订单id
     */
    Boolean manualConfirmReceipt(Long id) throws Exception;

    /**
     * 申请退货
     *
     * @param apply 退货申请
     * @return 处理结果
     */
    Boolean applyReturnGoods(ReturnGoodsApplyDTO apply) throws Exception;

    /**
     * 根据订单id查询退货申请
     *
     * @param orderInfoId 订单id
     * @return 退货申请
     * @throws Exception
     */
    ReturnGoodsApplyDTO getByOrderInfoId(Long orderInfoId) throws Exception;

    /**
     * 更新退货物流单号
     *
     * @param orderInfoId             订单id
     * @param returnGoodsLogisticCode 退货物流单号
     * @throws Exception
     */
    void updateReturnGoodsLogisticCode(Long orderInfoId,
                                       String returnGoodsLogisticCode) throws Exception;

    /**
     * 更新订单
     *
     * @param order 订单
     * @throws Exception
     */
    void update(OrderInfoDTO order) throws Exception;

    /**
     * 查询确认收货时间超过了7天而且还没有发表评论的订单
     *
     * @return 订单
     */
    List<OrderInfoDTO> listNotPublishedCommentOrders() throws Exception;
}
