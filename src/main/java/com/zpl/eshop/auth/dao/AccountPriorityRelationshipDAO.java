package com.zpl.eshop.auth.dao;

/**
 * 帐号和权限关系管理模块 DAO 组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/10 22:06
 **/
public interface AccountPriorityRelationshipDAO {

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    Long getCountByPriorityId(Long priorityId);
}
