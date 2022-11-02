package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.RolePriorityRelationshipDO;

import java.util.List;

/**
 * 角色和权限关系管理模块的DAO组件接口
 *
 * @author ZhangPeiL1n
 */
public interface RolePriorityRelationshipDAO {

    /**
     * 新增角色和权限的关联关系
     *
     * @param rolePriorityRelationshipDO 关系DO
     */
    void save(RolePriorityRelationshipDO rolePriorityRelationshipDO);

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    Long countByPriorityId(Long priorityId);

    /**
     * 根据角色id查询角色和权限的关系
     *
     * @param roleId 角色id
     * @return 角色权限关系DO对象集合
     */
    List<RolePriorityRelationshipDO> listByRoleId(Long roleId);

    /**
     * 根据角色id删除角色权限关联关系
     *
     * @param roleId 角色id
     * @return 操作结果
     */
    Boolean removeByRoleId(Long roleId);

}
