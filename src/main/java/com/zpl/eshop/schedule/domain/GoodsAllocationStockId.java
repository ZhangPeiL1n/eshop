package com.zpl.eshop.schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ZhangPeiL1n
 * @date 2022/9/17 20:48
 **/
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GoodsAllocationStockId {

    /**
     * 商品货位id
     */
    private Long goodsAllocationId;

    /**
     * 商品skuId
     */
    private Long goodsSkuId;
}
