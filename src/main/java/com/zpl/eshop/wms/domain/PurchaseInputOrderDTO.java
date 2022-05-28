package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 采购入库单 dto
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 17:43
 **/
@Getter
@Setter
@ToString
public class PurchaseInputOrderDTO extends AbstractObject {
    /**
     * id
     */
    private Long id;
    /**
     * 供应商 id
     */
    private Long supplierId;

    /**
     * 预计到达时间
     */
    private Date expectArrivalTime;

    /**
     * 实际到达时间
     */
    private Date arrivalTime;

    /**
     * 采购联系人
     */
    private String purchaseContactor;

    /**
     * 采购联系人电话号码
     */
    private String purchaseContactPhoneNumber;

    /**
     * 采购联系人邮箱地址
     */
    private String purchaseContactEmail;

    /**
     * 采购单备注
     */
    private String purchaseOrderComment;

    /**
     * 采购员
     */
    private String purchaser;

    /**
     * 采购入库单的状态
     */
    private Integer purchaseInputOrderStatus;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 采购入库单条目集合
     */
    private List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOs;

    /**
     * 采购入库单商品上架条目集合
     */
    private List<PurchaseInputOrderPutOnItemDTO> purchaseInputOrderPutOnItemDTOs;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
