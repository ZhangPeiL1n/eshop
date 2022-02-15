package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.RolePriorityRelationshipDO;

import java.util.List;

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

    /**
     * 根据角色id 查找出相关权限
     *
     * @param roleId 角色id
     * @return 角色权限关系集合
     */
    List<RolePriorityRelationshipDO> listByRoleId(Long roleId);

    /**
     * 根据角色删除关联关系
     *
     * @param roleId 角色id
     * @return 删除成功返回true
     */
    Boolean removeByRoleId(Long roleId);
}
