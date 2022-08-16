package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 货位库存明细 DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 17:49
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsAllocationStockDetailDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 货位 id
     */
    private Long goodsAllocationId;

    /**
     * 商品 skuId
     */
    private Long goodsSkuId;

    /**
     * 上架时间
     */
    private Date putOnTime;

    /**
     * 上架数量
     */
    private Long putOnQuantity;

    /**
     * 当前库存数量
     */
    private Long currentStockQuantity;

    /**
     * 锁定库存数量
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
