package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品属性值
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 20:49
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsPropertyValueDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * type=1 品类与属性关联关系的ID
     * type=2 商品关联属性分组id
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
