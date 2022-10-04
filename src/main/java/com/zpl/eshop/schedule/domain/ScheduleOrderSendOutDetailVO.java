package com.zpl.eshop.schedule.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 发货明细
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 18:18
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ScheduleOrderSendOutDetailVO extends AbstractObject {
    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderInfoId;

    /**
     * 订单条目id
     */
    private Long orderItemId;

    /**
     * 货位库存明细id
     */
    private Long goodsAllocationStockDetailId;

    /**
     * 发货数量
     */
    private Long sendOutQuantity;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
