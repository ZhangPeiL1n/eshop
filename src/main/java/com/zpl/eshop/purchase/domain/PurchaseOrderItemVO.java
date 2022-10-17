package com.zpl.eshop.purchase.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 采购条目DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/5/28 12:04
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderItemVO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 采购单id
     */
    private Long purchaseOrderId;

    /**
     * 商品 skuId
     */
    private Long goodsSkuId;

    /**
     * 采购数量
     */
    private Long purchaseCount;

    /**
     * 采购价格
     */
    private Double purchasePrice;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
