package com.zpl.eshop.order.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 退货申请
 *
 * @author ZhangPeiL1n
 * @date 2022/10/15 19:38
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnGoodsApplyDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderInfoId;

    /**
     * 退货原因
     */
    private Integer returnGoodsReason;

    /**
     * 退货备注
     */
    private String returnGoodsComment;

    /**
     * 退货申请状态
     */
    private Integer returnGoodsApplyStatus;

    /**
     * 退货物流单号
     */
    private String returnGoodsLogisticCode;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
