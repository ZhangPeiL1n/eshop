package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * wms中心商品库存
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 16:59
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class WmsGoodsStockDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

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
