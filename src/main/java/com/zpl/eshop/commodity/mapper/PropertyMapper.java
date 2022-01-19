package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.PropertyDO;
import com.zpl.eshop.commodity.domain.PropertyQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品属性管理模块的 mapper 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/19 21:13
 **/
@Mapper
public interface PropertyMapper {

    /**
     * 分页查询商品属性
     *
     * @param propertyQuery 查询条件
     * @return 商品属性
     */
    @Select("<script>" +
            "SELECT " +
            "id," +
            "property_name," +
            "property_desc," +
            "input_type," +
            "input_value," +
            "gmt_create," +
            "gmt_modified" +
            "FROM commodity_property a," +
            "(" +
            "SELECT id FROM commodity_property " +
            "<if test='propertyName != null'>" +
            "WHERE property_name like '#{propertyName}%'" +
            "</if>" +
            "LIMIT #{offset},#{size}" +
            ") b" +
            "WHERE a.id = b.id" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "property_name", property = "propertyName"),
            @Result(column = "property_desc", property = "propertyDesc"),
            @Result(column = "input_type", property = "inputType"),
            @Result(column = "input_value", property = "inputValue"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<PropertyDO> listPropertiesByPage(PropertyQuery propertyQuery);

    /**
     * 新增商品属性
     *
     * @param propertyDO 商品属性DO
     */
    @Insert("INSERT INTO commodity_property(" +
            "property_name," +
            "property_desc," +
            "input_type," +
            "gmt_create," +
            "gmt_modified" +
            ")VALUES(" +
            "#{propertyName}," +
            "#{propertyDesc}," +
            "#{inputType}," +
            "#{inputValue}," +
            "#{gmtCreate}" +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void saveProperty(PropertyDO propertyDO);

    /**
     * 根据id查询商品属性
     *
     * @param propertyId 属性id
     * @return 商品属性DO
     */
    @Select("SELECT " +
            "id," +
            "property_name," +
            "property_desc," +
            "input_type," +
            "input_value," +
            "gmt_create," +
            "gmt_modified" +
            "FROM commodity_property " +
            "WHERE id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "property_name", property = "propertyName"),
            @Result(column = "property_desc", property = "propertyDesc"),
            @Result(column = "input_type", property = "inputType"),
            @Result(column = "input_value", property = "inputValue"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    PropertyDO getPropertyById(@Param("id") Long propertyId);

    /**
     * 更新商品属性
     *
     * @param propertyDO 商品属性DO
     */
    @Update("UPDATE commodity_property SET " +
            "property_desc = #{propertyDesc}," +
            "gmt_creat = #{gmtCreate}" +
            "WHERE id = #{id}")
    void updateProperty(PropertyDO propertyDO);
}
