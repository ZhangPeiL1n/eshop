package com.zpl.eshop.order.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 订单 DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 18:19
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderInfoDTO extends AbstractObject {
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
     * 是否发表评论
     */
    private Integer publishedComment;

    /**
     * 确认收货时间
     */
    private Date confirmReceiptTime;

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
    private List<OrderItemDTO> orderItems;

}
