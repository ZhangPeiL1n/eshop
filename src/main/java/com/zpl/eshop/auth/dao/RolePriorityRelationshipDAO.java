package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.RolePriorityRelationshipDO;

/**
 * 角色和权限关系管理模块 DAO 组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/10 21:59
 **/
public interface RolePriorityRelationshipDAO {

    /**
     * 新增角色权限关联关系
     *
     * @param rolePriorityRelationshipDO 角色权限关联关系DO
     * @return 新增成功返回true
     */
    Boolean save(RolePriorityRelationshipDO rolePriorityRelationshipDO);

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    Long countByPriorityId(Long priorityId);
}
