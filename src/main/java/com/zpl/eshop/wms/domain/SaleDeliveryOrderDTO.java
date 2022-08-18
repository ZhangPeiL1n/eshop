package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 销售出库单DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 17:52
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryOrderDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 订单 id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户账号 id
     */
    private Long userAccountId;

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
     * 销售出库单状态
     */
    private Integer saleDeliveryOrderStatus;

    /**
     * 实际发货时间
     */
    private Date deliveryTime;

    /**
     * 销售出库单创建时间
     */
    private Date gmtCreate;

    /**
     * 销售出库单修改时间
     */
    private Date gmtModified;

    /**
     * 销售出库单条目
     */
    private List<SaleDeliveryOrderItemDTO> saleDeliveryOrderItems;

    /**
     * 发货单
     */
    private SendOutOrderDTO sendOutOrder;

    /**
     * 物流单
     */
    private LogisticOrderDTO logisticOrder;

}
