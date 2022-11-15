package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.AccountPriorityRelationshipDO;

import java.util.List;

/**
 * 账号和权限关系管理模块的DAO组件接口
 *
 * @author ZhangPeiL1n
 */
public interface AccountPriorityRelationshipDAO {

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     * @throws Exception
     */
    Long countByPriorityId(Long priorityId) throws Exception;

    /**
     * 根据账号id查询账号和权限的关联关系
     *
     * @param accountId 账号id
     * @return 账号和权限的关联关系
     * @throws Exception
     */
    List<AccountPriorityRelationshipDO> listByAccountId(Long accountId) throws Exception;

    /**
     * 新增账号和权限的关联关系
     *
     * @param accountPriorityRelationship 账号和权限的关联关系
     * @throws Exception
     */
    void save(AccountPriorityRelationshipDO accountPriorityRelationship) throws Exception;

    /**
     * 根据账号id删除账号和权限的关联关系
     *
     * @param accountId 账号id
     * @throws Exception
     */
    void removeByAccountId(Long accountId) throws Exception;

}
