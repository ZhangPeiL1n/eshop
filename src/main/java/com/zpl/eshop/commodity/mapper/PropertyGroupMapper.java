package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.PropertyGroupDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 属性分组Mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/5/18 0:22
 **/
@Mapper
public interface PropertyGroupMapper {

    /**
     * 新增属性分组
     *
     * @param propertyGroupDO 属性分组
     */
    @Insert("insert into commodity_property_group(" +
            "name," +
            "category_id," +
            "gmt_create," +
            "gmt_modified" +
            ") values (" +
            "#{name}," +
            "#{categoryId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(PropertyGroupDO propertyGroupDO);

    /**
     * 根据类目id查找属性分组
     *
     * @param categoryId 类目id
     * @return 属性分组
     */
    @Select("select " +
            "id," +
            "name," +
            "category_id," +
            "gmt_create," +
            "gmt_modified " +
            "from commodity_property_group " +
            "where category_id = #{categoryId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")

    })
    List<PropertyGroupDO> listByCategoryId(@Param("categoryId") Long categoryId);
}
