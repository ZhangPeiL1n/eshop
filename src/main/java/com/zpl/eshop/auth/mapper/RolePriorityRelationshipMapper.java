package com.zpl.eshop.auth.mapper;

import com.zpl.eshop.auth.domain.RolePriorityRelationshipDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 角色和权限关系管理模块的mapper组件
 *
 * @author ZhangPeiL1n
 */
@Mapper
public interface RolePriorityRelationshipMapper {

    /**
     * 新增角色和权限的关联关系
     *
     * @param rolePriorityRelationshipDO 角色权限do
     */
    @Insert("INSERT INTO auth_role_priority_relationship("
            + "role_id,"
            + "priority_id,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{roleId},"
            + "#{priorityId},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(RolePriorityRelationshipDO rolePriorityRelationshipDO);

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    @Select("SELECT count(*) "
            + "FROM auth_role_priority_relationship "
            + "WHERE priority_id=#{priorityId}")
    Long countByPriorityId(@Param("priorityId") Long priorityId);

    /**
     * 根据角色id查询角色和权限的关系
     *
     * @param roleId 角色id
     * @return 角色权限关系DO对象集合
     */
    @Select("SELECT "
            + "id,"
            + "priority_id,"
            + "role_id,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM auth_role_priority_relationship "
            + "WHERE role_id=#{roleId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "priority_id", property = "priorityId"),
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<RolePriorityRelationshipDO> listByRoleId(
            @Param("roleId") Long roleId);

    /**
     * 根据角色id删除角色权限关联关系
     *
     * @param roleId 角色id
     */
    @Delete("DELETE FROM auth_role_priority_relationship WHERE role_id=#{roleId}")
    void removeByRoleId(@Param("roleId") Long roleId);

}
