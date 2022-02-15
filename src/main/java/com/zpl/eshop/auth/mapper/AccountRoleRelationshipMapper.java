package com.zpl.eshop.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 帐号角色关系Mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 18:31
 **/
@Mapper
public interface AccountRoleRelationshipMapper {

    /**
     * 查询角色关联的帐号记录数
     *
     * @param roleId 角色id
     * @return 记录数
     */
    @Select("SELECT count(*) " +
            "FROM auth_account_role_relationship " +
            "WHERE role_id = #{roleId}")
    Long countByRoleId(@Param("roleId") Long roleId);
}
