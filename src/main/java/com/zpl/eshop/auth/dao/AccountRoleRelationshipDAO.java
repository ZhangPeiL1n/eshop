package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.AccountRoleRelationshipDO;

import java.util.List;

/**
 * 账号角色关系管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 */
public interface AccountRoleRelationshipDAO {

    /**
     * 根据角色id来查询记录数
     *
     * @param roleId 角色id
     * @return 记录数
     * @throws Exception
     */
    Long countByRoleId(Long roleId) throws Exception;

    /**
     * 根据账号id查询账号和角色关联关系
     *
     * @param accountId 账号id
     * @return 账号和角色关联关系
     * @throws Exception
     */
    List<AccountRoleRelationshipDO> listByAccountId(Long accountId) throws Exception;

    /**
     * 根据角色id查询账号id集合
     *
     * @param roleId 角色id
     * @return 账号id集合
     * @throws Exception
     */
    List<Long> listAccountIdsByRoleId(Long roleId) throws Exception;

    /**
     * 新增账号和角色的关联关系
     *
     * @param relation 账号和角色的关联关系
     * @throws Exception
     */
    void save(AccountRoleRelationshipDO relation) throws Exception;

    /**
     * 根据账号id删除账号和角色的关联关系
     *
     * @param accountId 账号id
     * @throws Exception
     */
    void removeByAccountId(Long accountId) throws Exception;
}
