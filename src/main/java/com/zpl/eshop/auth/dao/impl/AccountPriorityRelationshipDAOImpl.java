package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.zpl.eshop.auth.domain.AccountPriorityRelationshipDO;
import com.zpl.eshop.auth.mapper.AccountPriorityRelationshipMapper;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账号和权限关系管理模块的DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class AccountPriorityRelationshipDAOImpl implements AccountPriorityRelationshipDAO {

    /**
     * 账号和权限关系管理模块的mapper组件
     */
    @Autowired
    private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     * @throws Exception
     */
    @Override
    public Long countByPriorityId(Long priorityId) throws Exception {
        return accountPriorityRelationshipMapper.countByPriorityId(priorityId);
    }

    /**
     * 根据账号id查询账号和权限的关联关系
     *
     * @param accountId 账号id
     * @return 账号和权限的关联关系
     * @throws Exception
     */
    @Override
    public List<AccountPriorityRelationshipDO> listByAccountId(Long accountId) throws Exception {
        return accountPriorityRelationshipMapper.listByAccountId(accountId);
    }

    /**
     * 新增账号和权限的关联关系
     *
     * @param accountPriorityRelationship 账号和权限的关联关系
     * @throws Exception
     */
    @Override
    public void save(AccountPriorityRelationshipDO accountPriorityRelationship) throws Exception {
        accountPriorityRelationship.setGmtCreate(dateProvider.getCurrentTime());
        accountPriorityRelationship.setGmtModified(dateProvider.getCurrentTime());
        accountPriorityRelationshipMapper.save(accountPriorityRelationship);
    }

    /**
     * 根据账号id删除账号和权限的关联关系
     *
     * @param accountId 账号id
     * @throws Exception
     */
    @Override
    public void removeByAccountId(Long accountId) throws Exception {
        accountPriorityRelationshipMapper.removeByAccountId(accountId);
    }

}
