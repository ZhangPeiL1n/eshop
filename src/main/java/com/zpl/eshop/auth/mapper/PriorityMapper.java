package com.zpl.eshop.auth.mapper;

import com.zpl.eshop.auth.domain.PriorityDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 权限管理模块 mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/1/7 21:50
 **/
@Mapper
public interface PriorityMapper {

    /**
     * 查询根权限
     *
     * @return 根权限集合
     */
    @Select("select" +
            "id," +
            "code," +
            "url," +
            "priority_comment," +
            "priority_type," +
            "parent_id" +
            "gmt_create," +
            "gmt_modified" +
            "from auth_priority" +
            "where parent_id is null")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code"),
            @Result(column = "url", property = "url"),
            @Result(column = "priority_comment", property = "priorityComment"),
            @Result(column = "priority_type", property = "priorityType"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")

    })
    List<PriorityDO> listRootPriorities();

    /**
     * 根据父权限id查询子权限
     *
     * @param parentId 父权限id
     * @return 子权限
     */
    @Select("select" +
            "id," +
            "code," +
            "url," +
            "priority_comment," +
            "priority_type," +
            "parent_id" +
            "gmt_create," +
            "gmt_modified" +
            "from auth_priority" +
            "where parent_id = #{parentId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code"),
            @Result(column = "url", property = "url"),
            @Result(column = "priority_comment", property = "priorityComment"),
            @Result(column = "priority_type", property = "priorityType"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")

    })
    List<PriorityDO> listChildPriorities(@Param("parentId") Long parentId);

    /**
     * 根据id查询权限
     *
     * @param id id
     * @return 权限
     */
    @Select("select" +
            "id," +
            "code," +
            "url," +
            "priority_comment," +
            "priority_type," +
            "parent_id" +
            "gmt_create," +
            "gmt_modified" +
            "from auth_priority" +
            "where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code"),
            @Result(column = "url", property = "url"),
            @Result(column = "priority_comment", property = "priorityComment"),
            @Result(column = "priority_type", property = "priorityType"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")

    })
    PriorityDO getPriorityById(@Param("id") Long id);

    /**
     * 新增权限
     *
     * @param priorityDO 权限DO对象
     */
    @Insert("INSERT INTO auth_priority(code,url,priority_comment,priority_type,parent_id,gmt_create,gmt_modified)" +
            "VALUES(#{code},#{url},#{priorityComment},#{priorityType},#{parentId},#{gmtCreate},#{gmtModified})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void savePriority(PriorityDO priorityDO);

    /**
     * 更新权限
     *
     * @param priorityDO 权限Do对象
     */
    @Update("UPDATE auth_priority SET " +
            "code=#{code},url=#{url}," +
            "priority_comment=#{priorityComment}," +
            "priority_type=#{priorityType}," +
            "parent_id=#{parentId}," +
            "gmt_create=#{gmtCreate}," +
            "gmt_modified=#{gmtModified}" +
            "WHERE id = #{id}")
    void updatePriority(PriorityDO priorityDO);

    /**
     * 删除权限
     *
     * @param priorityId 权限id
     */
    @Delete("DELETE FROM auth_priority where priority_id = #{priorityId}")
    void deletePriority(@Param("priorityId") Long priorityId);
}
