package com.zpl.eshop.schedule.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 调度中心货位库存明细DO
 *
 * @author ZhangPeiL1n
 * @date 2022/10/4 17:24
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ScheduleGoodsAllocationStockDetailVO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 商品sku id
     */
    private Long goodsSkuId;

    /**
     * 货位id
     */
    private Long goodsAllocationId;

    /**
     * 上架时间
     */
    private Date putOnTime;

    /**
     * 上架数量
     */
    private Long putOnQuantity;

    /**
     * 当前剩余库存数量
     */
    private Long currentStockQuantity;

    /**
     * 当前锁定的库存数量
     */
    private Long lockedStockQuantity;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
