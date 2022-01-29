package com.zpl.eshop.inventory.domain;

import lombok.Data;

import java.util.Date;

/**
 * 商品Sku库存DO类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/23 22:12
 **/
@Data
public class GoodsStockDO {
    /**
     * id
     */
    private Long id;

    /**
     * 商品 SkuId
     */
    private Long goodsSkuId;

    /**
     * 销售库存
     */
    private Long saleStockQuantity;

    /**
     * 锁定库存
     */
    private Long lockedStockQuantity;

    /**
     * 已销售库存
     */
    private Long saledStockQuantity;

    /**
     * 库存状态
     */
    private Integer stockStatus;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
