package com.zpl.eshop.auth.dao;

/**
 * 帐号角色关联关系DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 18:37
 **/
public interface AccountRoleRelationshipDAO {

    /**
     * 查询角色关联的帐号记录数
     *
     * @param roleId 角色id
     * @return 记录数
     */
    Long countByRoleId(Long roleId);
}
