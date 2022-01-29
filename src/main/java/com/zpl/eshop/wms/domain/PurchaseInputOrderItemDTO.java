package com.zpl.eshop.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 采购入库单条目DTO类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 23:08
 **/
@Getter
@Setter
@ToString
public class PurchaseInputOrderItemDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 采购入库单id
     */
    private Long purchaseInputOrderId;
    /**
     * 商品skuId
     */
    private Long goodsSkuId;
    /**
     * 采购数量
     */
    private Long purchaseCount;
    /**
     * 采购价格
     */
    private Long purchasePrice;
    /**
     * 合格数量
     */
    private Long qualifiedCount;
    /**
     * 到货数量
     */
    private Long arrivalCount;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

}
