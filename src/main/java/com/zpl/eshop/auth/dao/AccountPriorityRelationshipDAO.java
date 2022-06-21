package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.AccountPriorityRelationshipDO;

import java.util.List;

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
    void save(AccountPriorityRelationshipDO accountPriorityRelationshipDO);

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    Long countByPriorityId(Long priorityId);

    /**
     * 根据帐号id删除账号权限关联关系
     *
     * @param accountId 帐号id
     */
    void removeByAccountId(Long accountId);

    /**
     * 根据帐号id查询帐号和权限关联关系
     *
     * @param accountId 帐号id
     * @return 帐号和权限关联关系
     */
    List<AccountPriorityRelationshipDO> listByAccountId(Long accountId);
}
