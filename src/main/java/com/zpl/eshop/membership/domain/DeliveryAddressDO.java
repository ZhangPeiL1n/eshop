package com.zpl.eshop.membership.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 收货地址DO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 12:31
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryAddressDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 用户帐号id
     */
    private Long userAccountId;

    /**
     * 省份
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 电话
     */
    private String phoneNumber;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
