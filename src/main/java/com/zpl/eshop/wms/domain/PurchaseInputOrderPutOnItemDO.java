package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 采购入库单上架条目DO
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 17:26
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseInputOrderPutOnItemDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 采购入库单条目id
     */
    private Long purchaseInputOrderItemId;

    /**
     * 货位id
     */
    private Long goodsAllocationId;

    /**
     * 商品sku id
     */
    private Long goodsSkuId;

    /**
     * 商品上架数量
     */
    private Long putOnShelvesCount;

    /**
     * 商品上架条目的创建时间
     */
    private Date gmtCreate;

    /**
     * 商品上架条目的修改时间
     */
    private Date gmtModified;
}
