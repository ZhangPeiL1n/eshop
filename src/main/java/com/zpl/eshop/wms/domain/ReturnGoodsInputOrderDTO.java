package com.zpl.eshop.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 退货入库单 DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 18:10
 **/
@Getter
@Setter
@ToString
public class ReturnGoodsInputOrderDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 用户帐号 id
     */
    private String userAccountId;

    /**
     * 订单 id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 退货入库单状态
     */
    private Integer returnGoodsInputOrderStatus;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 收货地址
     */
    private String deliveryAddress;

    /**
     * 收货人电话号码
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
     * 退货原因
     */
    private Integer returnGoodsReason;

    /**
     * 退货备注
     */
    private String returnGoodsComment;

    /**
     * 退货的实际到货时间
     */
    private Date arrivalTime;

    /**
     * 退货入库单创建时间
     */
    private Date gmtCreateTime;

    /**
     * 退货入库单修改时间
     */
    private Date gmtModified;

    /**
     * 退货入库单条目DTO集合
     */
    private List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOList;

    /**
     * 退货入库单商品上架条目DTO集合
     */
    private List<ReturnGoodsInputOrderPutOnItemDTO> returnGoodsInputOrderPutOnItemDTOList;

}
