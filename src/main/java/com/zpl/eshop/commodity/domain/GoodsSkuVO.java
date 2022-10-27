package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/8/25 22:16
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsSkuVO extends AbstractObject {
    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品sku编号
     */
    private String skuCode;

    /**
     * 采购价格
     */
    private Double purchasePrice;

    /**
     * 销售价格
     */
    private Double salePrice;

    /**
     * 折扣价格
     */
    private Double discountPrice;

    /**
     * 销售属性
     */
    private String saleProperties;

    /**
     * 商品sku的销售库存
     */
    private Long saleStockQuantity;

    /**
     * 商品sku销售属性值
     */
    List<GoodsSkuSalePropertyValueVO> propertyValues;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}