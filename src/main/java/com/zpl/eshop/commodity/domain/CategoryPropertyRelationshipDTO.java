package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;

import java.util.Date;

/**
 * 类目属性关联关系DO
 *
 * @author ZhangPeiL1n
 * @date 2022/5/17 23:03
 **/
@Data
public class CategoryPropertyRelationshipDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;
    /**
     * 类目id
     */
    private Long categoryId;
    /**
     * 属性id
     */
    private Long propertyId;
    /**
     * 是否必填
     */
    private Integer isRequired;
    /**
     * 属性类型：1.关键属性，2.销售属性，3.非关键属性，4.导购属性
     * 可多个值拼接
     */
    private String propertyTypes;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
}
