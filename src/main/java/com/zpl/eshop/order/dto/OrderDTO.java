package com.zpl.eshop.order.dto;

import java.util.Date;

/**
 * 订单 DTO
 * @author ZhangPeiL1n
 * @date 2022/1/3 18:19
 **/
public class OrderDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户账号 id
     */
    private String userAccountId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 收货地址
     */
    private String deliveryAddress;

    /**
     * 收货人手机号码
     */
    private String consigneeCellPhoneNumber;

    /**
     * 运费
     */
    private Double freight;

    /**
     * 支付方式
     */
    private Integer payType;

    /**
     * 订单总金额
     */
    private Double totalAmount;

    /**
     * 折扣金额
     */
    private Double discountAmount;

    /**
     * 优惠券抵扣金额
     */
    private Double couponAmount;

    /**
     * 应付金额
     */
    private Double payableAmount;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 纳税人识别号
     */
    private String taxpayerId;

    /**
     * 订单备注
     */
    private String orderComment;

    /**
     * 销售出库单创建时间
     */
    private Date gmtCreate;

    /**
     * 销售出库单修改时间
     */
    private Date gmtModified;

}
