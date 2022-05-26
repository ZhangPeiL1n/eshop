package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 类目 DTO 类
 *
 * @author ZhangPeiL1n
 * @date 2022/5/17 22:08
 **/
@Data
public class CategoryVO extends AbstractObject {

    /**
     * 类目id
     */
    private Long id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 类目描述
     */
    private String description;

    /**
     * 是否叶子类目，1：是，0：不是
     */
    private Integer isLeaf;

    /**
     * 父类目id
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 类目与属性的关联关系
     */
    private List<CategoryPropertyRelationshipVO> propertyRelations;

    /**
     * 属性分组与属性的关系
     */
    private List<PropertyGroupVO> propertyGroups;

    /**
     * 类目关联的属性
     */
    private List<PropertyVO> properties;
}
