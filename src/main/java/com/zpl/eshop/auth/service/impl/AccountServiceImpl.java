package com.zpl.eshop.auth.service.impl;

import com.zpl.eshop.auth.dao.AccountDAO;
import com.zpl.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.zpl.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.zpl.eshop.auth.domain.*;
import com.zpl.eshop.auth.service.AccountService;
import com.zpl.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账号管理service组件
 *
 * @author ZhangPeiL1n
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

    /**
     * 账号管理DAO组件
     */
    @Autowired
    private AccountDAO accountDAO;

    /**
     * 账号和角色关系管理DAO组件
     */
    @Autowired
    private AccountRoleRelationshipDAO roleRelationDAO;

    /**
     * 账号和权限关系管理DAO组件
     */
    @Autowired
    private AccountPriorityRelationshipDAO priorityRelationDAO;

    /**
     * 权限缓存管理组件
     */
    @Autowired
    private PriorityCacheManager priorityCacheManager;

    /**
     * 分页查询账号
     *
     * @param query 查询条件
     * @return 账号
     * @throws Exception
     */
    @Override
    public List<AccountDTO> listByPage(AccountQuery query) throws Exception {
        List<AccountDO> accounts = accountDAO.listByPage(query);
        return ObjectUtils.convertList(
                accounts, AccountDTO.class);
    }

    /**
     * 根据id查询账号
     *
     * @param id 账号id
     * @return 账号
     * @throws Exception
     */
    @Override
    public AccountDTO getById(Long id) throws Exception {
        AccountDO account = accountDAO.getById(id);
        AccountDTO resultAccount = account.clone(AccountDTO.class);

        List<AccountRoleRelationshipDO> roleRelations =
                roleRelationDAO.listByAccountId(id);
        List<AccountRoleRelationshipDTO> resultRoleRelations =
                ObjectUtils.convertList(roleRelations, AccountRoleRelationshipDTO.class);
        resultAccount.setRoleRelations(resultRoleRelations);

        List<AccountPriorityRelationshipDO> priorityRelations =
                priorityRelationDAO.listByAccountId(id);
        List<AccountPriorityRelationshipDTO> resultPriorityRelations =
                ObjectUtils.convertList(priorityRelations, AccountPriorityRelationshipDTO.class);
        resultAccount.setPriorityRelations(resultPriorityRelations);

        return resultAccount;
    }

    /**
     * 新增账号
     *
     * @param account 账号
     * @throws Exception
     */
    @Override
    public void save(AccountDTO account) throws Exception {
        Long accountId = accountDAO.save(account.clone(AccountDO.class));
        account.setId(accountId);
        saveRelations(account);
    }

    /**
     * 更新账号
     *
     * @param account 账号
     *                @throws Exception
     */
    @Override
    public void update(AccountDTO account) throws Exception {
        accountDAO.update(account.clone(AccountDO.class));
        roleRelationDAO.removeByAccountId(account.getId());
        priorityRelationDAO.removeByAccountId(account.getId());

        saveRelations(account);

        priorityCacheManager.remove(account.getId());
    }

    /**
     * 更新密码
     *
     * @param account 账号
     *                @throws Exception
     */
    @Override
    public void updatePassword(AccountDTO account) throws Exception {
        accountDAO.updatePassword(account.clone(AccountDO.class));
    }

    /**
     * 新增关联关系
     *
     * @param account 账号
     * @throws Exception
     */
    private void saveRelations(AccountDTO account) throws Exception {
        for (AccountRoleRelationshipDTO roleRelation : account.getRoleRelations()) {
            roleRelation.setAccountId(account.getId());
            roleRelationDAO.save(roleRelation.clone(AccountRoleRelationshipDO.class));
        }

        for (AccountPriorityRelationshipDTO priorityRelation : account.getPriorityRelations()) {
            priorityRelation.setAccountId(account.getId());
            priorityRelationDAO.save(priorityRelation.clone(AccountPriorityRelationshipDO.class));
        }
    }

    /**
     * 删除账号
     *
     * @param id 账号id
     */
    @Override
    public void remove(Long id) throws Exception {
        roleRelationDAO.removeByAccountId(id);
        priorityRelationDAO.removeByAccountId(id);
        accountDAO.remove(id);
        priorityCacheManager.remove(id);
    }
}
