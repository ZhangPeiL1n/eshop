package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品sku的销售属性值
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 20:54
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsSkuSalePropertyValueVO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 商品SkuId
     */
    private Long goodsSkuId;

    /**
     * 关联关系id
     */
    private Long relationId;

    /**
     * 属性值
     */
    private String propertyValue;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
