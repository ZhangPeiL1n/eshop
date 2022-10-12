package com.zpl.eshop.finance.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 采购结算单
 *
 * @author ZhangPeiL1n
 * @date 2022/10/12 20:58
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseSettlementOrderDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 采购入库单id
     */
    private Long purchaseInputOrderId;

    /**
     * 采购单id
     */
    private Long purchaseOrderId;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 预期到达时间
     */
    private Date expectArrivalTime;

    /**
     * 实际到达时间
     */
    private Date actualArrivalTime;

    /**
     * 采购联系人
     */
    private String purchaseContactor;

    /**
     * 采购联系人电话号码
     */
    private String purchaseContactorPhoneNumber;

    /**
     * 采购联系人邮箱地址
     */
    private String purchaseContactorEmail;

    /**
     * 采购单备注
     */
    private String purchaseOrderRemark;

    /**
     * 采购员
     */
    private String purchaser;

    /**
     * 采购入库单的状态
     */
    private Integer status;

    /**
     * 采购结算单总金额
     */
    private Double totalSettlementAmount;

    /**
     * 采购入库单的创建时间
     */
    private Date gmtCreate;

    /**
     * 采购入库单的修改时间
     */
    private Date gmtModified;

    /**
     * 采购结算单条目集合
     */
    private List<PurchaseSettlementOrderItemDTO> items;
}
