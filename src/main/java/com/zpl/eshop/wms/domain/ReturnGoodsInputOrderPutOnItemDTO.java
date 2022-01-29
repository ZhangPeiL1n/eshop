package com.zpl.eshop.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 退货入库商品上架条目DTO类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 21:32
 **/
@Getter
@Setter
@ToString
public class ReturnGoodsInputOrderPutOnItemDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 退货入库单条目Id
     */
    private Long returnGoodsInputOrderItemId;

    /**
     * 商品skuId
     */
    private Long goodsSkuId;

    /**
     * 商品货位id
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
