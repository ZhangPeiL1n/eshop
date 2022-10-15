package com.zpl.eshop.order.constant;

/**
 * 订单状态
 *
 * @author ZhangPeiL1n
 * @date 2022/8/28 19:53
 **/
public class OrderStatus {

    /**
     * 未知状态
     */
    public static final Integer UNKNOWN = -999;

    /**
     * 待支付
     */
    public static final Integer WAIT_FOR_PAY = 1;

    /**
     * 已取消
     */
    public static final Integer CANCELED = 2;

    /**
     * 待发货
     */
    public static final Integer WAIT_FOR_DELIVERY = 3;

    /**
     * 待收货
     */
    public static final Integer WAIT_FOR_RECEIVE = 4;

    /**
     * 已完成
     */
    public static final Integer FINISHED = 5;

    /**
     * 退货申请待审核
     */
    public static final Integer WAIT_FOR_RETURN_GOODS_APPROVE = 6;

    /**
     * 退货审核不通过
     */
    public static final Integer RETURN_GOODS_REJECTED = 7;

    /**
     * 退货商品待寄送
     */
    public static final Integer WAIT_FOR_SEND_OUT_RETURN_GOODS = 8;

    /**
     * 退货商品待收货
     */
    public static final Integer WAIT_FOR_RECEIVE_RETURN_GOODS = 9;

    /**
     * 退货商品待入库
     */
    public static final Integer WAIT_FOR_INPUT_RETURN_GOODS = 10;

    /**
     * 退货商品已入库
     */
    public static final Integer FINISHED_INPUT_RETURN_GOODS = 11;

    /**
     * 完成退货商品退款
     */
    public static final Integer FINISHED_RETURN_GOODS_REFUND = 12;

    private OrderStatus() {
    }
}
