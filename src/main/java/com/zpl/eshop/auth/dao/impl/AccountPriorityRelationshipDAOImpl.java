package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.zpl.eshop.auth.domain.AccountPriorityRelationshipDO;
import com.zpl.eshop.auth.mapper.AccountPriorityRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 帐号和权限管理模块 DAO 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/10 22:07
 **/
@Repository
public class AccountPriorityRelationshipDAOImpl implements AccountPriorityRelationshipDAO {

    private static final Logger logger = LoggerFactory.getLogger(AccountPriorityRelationshipDAOImpl.class);
    /**
     * 账户和权限关系管理模块 mapper
     */
    @Autowired
    private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;

    /**
     * 新增帐号权限关联关系
     *
     * @param accountPriorityRelationshipDO 帐号权限关联关系DO
     * @return 新增成功返回true
     */
    @Override
    public void save(AccountPriorityRelationshipDO accountPriorityRelationshipDO) {
        accountPriorityRelationshipMapper.save(accountPriorityRelationshipDO);
    }

    /**
     * 根据权限 id 查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    @Override
    public Long countByPriorityId(Long priorityId) {
        return accountPriorityRelationshipMapper.countByPriorityId(priorityId);
    }


    /**
     * 根据帐号id删除账号权限关联关系
     *
     * @param accountId 帐号id
     */
    @Override
    public void removeByAccountId(Long accountId) {
        accountPriorityRelationshipMapper.removeByAccountId(accountId);
    }

    /**
     * 根据帐号id查询帐号和权限关联关系
     *
     * @param accountId 帐号id
     * @return 帐号和权限关联关系
     */
    public List<AccountPriorityRelationshipDO> listByAccountId(Long accountId) {
        return accountPriorityRelationshipMapper.listByAccountId(accountId);
    }
}
