package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 属性分组与属性关联关系DO
 *
 * @author ZhangPeiL1n
 * @date 2022/5/24 21:51
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PropertyGroupRelationshipDTO extends AbstractObject {
    /**
     * id
     */
    private Long id;
    /**
     * 属性分组id
     */
    private Long propertyGroupId;
    /**
     * 属性id
     */
    private Long propertyId;
    /**
     * 属性是否必填
     */
    private Integer required;
    /**
     * 属性类型
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
