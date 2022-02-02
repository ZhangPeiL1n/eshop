package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.AccountPriorityRelationshipDO;

/**
 * 帐号和权限关系管理模块 DAO 组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/10 22:06
 **/
public interface AccountPriorityRelationshipDAO {

    /**
     * 新增帐号权限关联关系
     *
     * @param accountPriorityRelationshipDO 帐号权限关联关系DO
     * @return 新增成功返回true
     */
    Boolean save(AccountPriorityRelationshipDO accountPriorityRelationshipDO);

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    Long countByPriorityId(Long priorityId);
}
