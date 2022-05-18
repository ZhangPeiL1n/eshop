package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.CategoryPropertyRelationshipDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 类目属性关系管理mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/5/18 0:09
 **/
@Mapper
public interface CategoryPropertyRelationshipMapper {

    /**
     * 新增类目属性关系
     *
     * @param relationshipDO 类目属性关系
     */
    @Insert("insert into commodity_category_property_relationship(" +
            "category_id," +
            "property_id," +
            "is_required," +
            "property_types," +
            "gmt_create," +
            "gmt_modified" +
            ") values (" +
            "#{categoryId}," +
            "#{propertyId}," +
            "#{isRequired}," +
            "#{propertyTypes}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(CategoryPropertyRelationshipDO relationshipDO);
}
