package com.zpl.eshop.order.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 订单 VO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 15:23
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderInfoVO extends AbstractObject {

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
    private Long userAccountId;

    /**
     * 用户名
     */
    private String username;

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
     * 促销活动减免金额
     */
    private Double discountAmount;

    /**
     * 优惠券减免金额
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
     * 是否发表评论
     */
    private Integer publishComment;

    /**
     * 确认收货时间
     */
    private Date confirmReceiptTime;

    /**
     * 订单使用的优惠券id
     */
    private Long couponId;

    /**
     * 销售出库单创建时间
     */
    private Date gmtCreate;

    /**
     * 销售出库单修改时间
     */
    private Date gmtModified;

    /**
     * 订单包含的订单条目
     */
    private List<OrderItemVO> orderItems;

    /**
     * 订单操作日志
     */
    private List<OrderOperateLogVO> logs;

}
