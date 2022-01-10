package com.zpl.eshop.auth.dao;

/**
 * 角色和权限关系管理模块 DAO 组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/10 21:59
 **/
public interface RolePriorityRelationshipDAO {

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    Long getCountByPriorityId(Long priorityId);
}
