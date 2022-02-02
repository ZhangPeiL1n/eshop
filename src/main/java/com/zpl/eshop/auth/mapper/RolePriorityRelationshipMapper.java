package com.zpl.eshop.auth.mapper;

import com.zpl.eshop.auth.domain.RolePriorityRelationshipDO;
import org.apache.ibatis.annotations.*;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/10 22:00
 **/
@Mapper
public interface RolePriorityRelationshipMapper {


    /**
     * 新增角色权限关联关系
     *
     * @param rolePriorityRelationshipDO 角色权限关联关系DO
     */
    @Insert("INSERT INTO auth_role_priority_relationship(" +
            "role_id," +
            "priority_id," +
            "gmt_create," +
            "gmt_modified" +
            ")VALUES(" +
            "#{roleId}," +
            "#{priorityId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(RolePriorityRelationshipDO rolePriorityRelationshipDO);

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    @Select("SELECT count(*) " +
            "FROM auth_role_priority_relationship " +
            "WHERE priority_id = #{priorityId}")
    Long getCountByPriorityId(@Param("priorityId") Long priorityId);
}
