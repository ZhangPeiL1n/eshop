package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.AccountRoleRelationshipDO;

import java.util.List;

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

    /**
     * 新增帐号和角色关联关系
     *
     * @param relation 关系
     */
    void save(AccountRoleRelationshipDO relation);

    /**
     * 根据帐号id查询帐号和角色关联关系
     *
     * @param accountId 帐号id
     * @return 帐号和角色关联关系
     */
    List<AccountRoleRelationshipDO> listByAccountId(Long accountId);


    /**
     * 根据帐号id删除帐号角色关联关系
     *
     * @param accountId 帐号id
     */
    void removeByAccountId(Long accountId);
}
