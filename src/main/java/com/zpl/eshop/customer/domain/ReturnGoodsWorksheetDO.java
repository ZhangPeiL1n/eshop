package com.zpl.eshop.customer.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 退货工单DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 22:10
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnGoodsWorksheetDO extends AbstractObject {

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
     * 退货工单状态
     */
    private Integer status;

    /**
     * 退货原因
     */
    private Integer returnGoodsReason;

    /**
     * 退货备注
     */
    private String returnGoodsRemark;

    /**
     * 退货物流单号
     */
    private String returnGoodsLogisticsCode;

    /**
     * 退货工单创建时间
     */
    private Date gmtCreate;

    /**
     * 退货工单修改时间
     */
    private Date gmtModified;
}
