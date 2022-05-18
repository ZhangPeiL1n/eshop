package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.PropertyGroupDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

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
}
