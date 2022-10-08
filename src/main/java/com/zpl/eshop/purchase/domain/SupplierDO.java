package com.zpl.eshop.purchase.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 供应商DO
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:31
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 联系人
     */
    private String contactor;

    /**
     * 联系人电话号码
     */
    private String contactorPhoneNumber;

    /**
     * 结算周期
     */
    private Integer settlementPeriod;

    /**
     * 开户银行名称
     */
    private String bankName;

    /**
     * 开户银行账号
     */
    private String bankAccount;

    /**
     * 开户银行账号持有人
     */
    private String bankAccountHolder;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 纳税人识别号
     */
    private String taxpayerId;

    /**
     * 经营范围
     */
    private String businessScope;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
