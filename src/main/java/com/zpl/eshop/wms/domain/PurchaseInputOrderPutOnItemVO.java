package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 采购入库单上架条目
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 23:15
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseInputOrderPutOnItemVO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 采购入库单条目id
     */
    private Long purchaseInputOrderItemId;

    /**
     * 商品skuId
     */
    private Long goodsSkuId;

    /**
     * 货位id
     */
    private Long goodsAllocationId;

    /**
     * 上架数量
     */
    private Long putOnShelvesCount;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
