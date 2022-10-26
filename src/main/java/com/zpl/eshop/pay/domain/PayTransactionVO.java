package com.zpl.eshop.pay.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/25 21:55
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PayTransactionVO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderInfoId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单应付金额
     */
    private Double payableAmount;

    /**
     * 用户账号id
     */
    private Long userAccountId;

    /**
     * 用户支付账号
     */
    private String userPayAccount;

    /**
     * 交易渠道
     */
    private Integer transactionChannel;

    /**
     * 第三方支付交易编号
     */
    private String transactionNumber;

    /**
     * 第三方支付完成支付的时间
     */
    private Date finishPayTime;

    /**
     * 第三方支付的响应状态码
     */
    private String responseCode;

    /**
     * 支付交易状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
