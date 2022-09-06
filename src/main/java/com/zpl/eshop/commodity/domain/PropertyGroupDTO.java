package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 属性分组DO
 *
 * @author ZhangPeiL1n
 * @date 2022/5/17 23:08
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PropertyGroupDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;
    /**
     * 属性分组名称
     */
    private String name;
    /**
     * 类目id
     */
    private Long categoryId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 属性分组与属性的关联关系
     */
    private List<PropertyGroupRelationshipDTO> relations;
    /**
     * 属性分组关联的属性
     */
    private List<PropertyDTO> properties;
}
