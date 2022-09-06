package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.domain.CategoryPropertyRelationshipVO;
import com.zpl.eshop.commodity.domain.PropertyGroupVO;
import com.zpl.eshop.commodity.domain.PropertyVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/9/6 20:26
 **/
@Data
public class Properties {

    /**
     * 类目与属性的关联关系
     */
    private List<CategoryPropertyRelationshipVO> propertyRelations = new ArrayList<>();

    /**
     * 类目关联的属性
     */
    private List<PropertyVO> properties = new ArrayList<>();

    /**
     * 属性分组与属性的关系
     */
    private List<PropertyGroupVO> propertyGroups = new ArrayList<>();
}
