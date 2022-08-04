package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.CategoryPropertyRelationshipDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 根据类目id查询类目与属性的关联关系
     *
     * @param categoryId 类目id
     * @return 类目属性关联关系DO
     */
    @Select("SELECT " +
            "id," +
            "category_id," +
            "property_id," +
            "is_required," +
            "property_types," +
            "gmt_create," +
            "gmt_modified " +
            "from commodity_category_property_relationship " +
            "where category_id = #{categoryId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "property_id", property = "propertyId"),
            @Result(column = "is_required", property = "isRequired"),
            @Result(column = "property_types", property = "propertyTypes"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<CategoryPropertyRelationshipDO> listByCategoryId(@Param("categoryId") Long categoryId);


    /**
     * 根据类目id删除
     *
     * @param categoryId 类目id
     */
    @Delete("delete from commodity_category_property_relationship " +
            "where category_id = #{categoryId}")
    void removeByCategoryId(@Param("categoryId") Long categoryId);
}
