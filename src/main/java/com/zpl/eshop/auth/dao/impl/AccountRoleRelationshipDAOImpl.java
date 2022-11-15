package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.zpl.eshop.auth.domain.AccountRoleRelationshipDO;
import com.zpl.eshop.auth.mapper.AccountRoleRelationshipMapper;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账号角色关系管理模块DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class AccountRoleRelationshipDAOImpl implements AccountRoleRelationshipDAO {

    /**
     * 账号角色关系管理模块mapper组件
     */
    @Autowired
    private AccountRoleRelationshipMapper accountRoleRelationMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 根据角色id来查询记录数
     *
     * @param roleId 角色id
     * @return 记录数
     */
    @Override
    public Long countByRoleId(Long roleId) {
        return accountRoleRelationMapper.countByRoleId(roleId);
    }

    /**
     * 根据账号id查询账号和角色关联关系
     *
     * @param accountId 账号id
     * @return 账号和角色关联关系
     */
    @Override
    public List<AccountRoleRelationshipDO> listByAccountId(Long accountId) {
        return accountRoleRelationMapper.listByAccountId(accountId);
    }

    /**
     * 根据角色id查询账号id集合
     *
     * @param roleId 角色id
     * @return 账号id集合
     */
    @Override
    public List<Long> listAccountIdsByRoleId(Long roleId) {
        return accountRoleRelationMapper.listAccountIdsByRoleId(roleId);
    }

    /**
     * 新增账号和角色的关联关系
     *
     * @param relation 账号和角色的关联关系
     * @throws Exception
     */
    @Override
    public void save(AccountRoleRelationshipDO relation) throws Exception {
        relation.setGmtCreate(dateProvider.getCurrentTime());
        relation.setGmtModified(dateProvider.getCurrentTime());
        accountRoleRelationMapper.save(relation);
    }

    /**
     * 根据账号id删除账号和角色的关联关系
     *
     * @param accountId 账号id
     * @throws Exception
     */
    @Override
    public void removeByAccountId(Long accountId) throws Exception {
        accountRoleRelationMapper.removeByAccountId(accountId);
    }
}
