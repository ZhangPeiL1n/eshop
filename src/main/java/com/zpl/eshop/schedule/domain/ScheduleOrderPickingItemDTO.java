package com.zpl.eshop.schedule.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 调度中心订单拣货条目DO
 *
 * @author ZhangPeiL1n
 * @date 2022/10/3 18:13
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ScheduleOrderPickingItemDTO extends AbstractObject {

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
     * 货位id
     */
    private Long goodsAllocationId;

    /**
     * 商品sku id
     */
    private Long goodsSkuId;

    /**
     * 拣货数量
     */
    private Long pickingCount;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
