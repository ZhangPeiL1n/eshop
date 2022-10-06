package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 采购入库单条目DTO类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 23:08
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseInputOrderItemDTO extends AbstractObject {

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
    private Double purchasePrice;

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

    /**
     * 采购入库单商品上架条目集合
     */
    private List<PurchaseInputOrderPutOnItemDTO> putOnItems;

}
