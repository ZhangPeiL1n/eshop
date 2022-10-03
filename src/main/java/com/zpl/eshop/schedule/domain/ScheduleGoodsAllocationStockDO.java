package com.zpl.eshop.schedule.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品货位库存
 *
 * @author ZhangPeiL1n
 * @date 2022/9/15 22:24
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ScheduleGoodsAllocationStockDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 货位id
     */
    private Long goodsAllocationId;

    /**
     * 商品sku id
     */
    private Long goodsSkuId;

    /**
     * 可用库存
     */
    private Long availableStockQuantity;

    /**
     * 锁定库存
     */
    private Long lockedStockQuantity;

    /**
     * 已出库库存
     */
    private Long outputStockQuantity;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
