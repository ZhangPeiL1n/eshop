package com.zpl.eshop.finance.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 采购结算单条目
 *
 * @author ZhangPeiL1n
 * @date 2022/10/12 21:00
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseSettlementOrderItemVO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 采购结算单id
     */
    private Long purchaseSettlementOrderId;

    /**
     * 商品sku id
     */
    private Long goodsSkuId;

    /**
     * 商品sku的采购数量
     */
    private Long purchaseCount;

    /**
     * 商品sku的采购价格
     */
    private Double purchasePrice;

    /**
     * 商品sku到货后质检出来的合格商品数量
     */
    private Long qualifiedCount;

    /**
     * 商品sku实际到货的数量
     */
    private Long arrivalCount;

    /**
     * 采购入库单条目的创建时间
     */
    private Date gmtCreate;

    /**
     * 采购入库单条目的修改时间
     */
    private Date gmtModified;
}
