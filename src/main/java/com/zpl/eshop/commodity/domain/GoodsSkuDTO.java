package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 商品 sku dto
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 17:09
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsSkuDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * sku编号
     */
    private String skuCode;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品sku编号
     */
    private String goodsSkuCode;

    /**
     * 销售属性
     */
    private String saleProperties;

    /**
     * 商品毛重
     */
    private Double grossWeight;

    /**
     * 商品长度
     */
    private Double goodsLength;

    /**
     * 商品宽度
     */
    private Double goodsWidth;

    /**
     * 商品高度
     */
    private Double goodsHeight;

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
     * 商品sku销售属性值
     */
    List<GoodsSkuSalePropertyValueDTO> propertyValues;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 商品sku的销售库存
     */
    private Long saleStockQuantity;

}
