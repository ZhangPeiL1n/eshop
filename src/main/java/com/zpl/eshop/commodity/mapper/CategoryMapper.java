package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.CategoryDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 类目管理模块 mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/5/17 22:17
 **/
@Mapper
public interface CategoryMapper {

    /**
     * 查询根类目
     *
     * @return 根类目集合
     */
    @Select("select " +
            "id," +
            "name," +
            "description," +
            "is_leaf," +
            "parent_id," +
            "gmt_create," +
            "gmt_modified " +
            "from commodity_category " +
            "where parent_id is null")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "is_leaf", property = "isLeaf"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<CategoryDO> listRoots();

    /**
     * 查询子类目
     *
     * @param id 父类目 id
     * @return 子类目集合
     */
    @Select("select " +
            "id," +
            "name," +
            "description," +
            "is_leaf," +
            "parent_id," +
            "gmt_create," +
            "gmt_modified " +
            "from commodity_category " +
            "where parent_id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "is_leaf", property = "isLeaf"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<CategoryDO> listChildren(@Param("id") Long id);

    /**
     * 根据id查询类目
     *
     * @param id 类目id
     * @return 子类目集合
     */
    @Select("select " +
            "id," +
            "name," +
            "description," +
            "is_leaf," +
            "parent_id," +
            "gmt_create," +
            "gmt_modified " +
            "from commodity_category " +
            "where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "is_leaf", property = "isLeaf"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    CategoryDO getById(@Param("id") Long id);

    /**
     * 新增类目
     *
     * @param categoryDO 类目
     */
    @Insert("insert into commodity_category(" +
            "name," +
            "description," +
            "is_leaf," +
            "parent_id," +
            "gmt_create," +
            "gmt_modified" +
            ") values (" +
            "#{name}," +
            "#{description}," +
            "#{isLeaf}," +
            "#{parentId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(CategoryDO categoryDO);
}
