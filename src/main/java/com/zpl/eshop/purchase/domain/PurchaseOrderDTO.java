package com.zpl.eshop.purchase.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 采购单DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 22:02
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderDTO extends AbstractObject {
    /**
     * id
     */
    private Long id;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 预计到货时间
     */
    private Date expectArrivalTime;

    /**
     * 采购联系人
     */
    private String contactor;

    /**
     * 采购联系人电话
     */
    private String contactorPhoneNumber;

    /**
     * 采购联系人邮箱
     */
    private String contactorEmail;

    /**
     * 采购单备注
     */
    private String remark;

    /**
     * 采购员
     */
    private String purchaser;

    /**
     * 采购单状态
     */
    private Integer status;

    /**
     * 采购单创建时间
     */
    private Date gmtCreateTime;

    /**
     * 采购单修改时间
     */
    private Date gmtModified;

    /**
     * 采购条目集合
     */
    private List<PurchaseOrderItemDTO> items;
}
