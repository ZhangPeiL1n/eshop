package com.zpl.eshop.auth.service.impl;

import com.zpl.eshop.auth.dao.AccountDAO;
import com.zpl.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.zpl.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.zpl.eshop.auth.domain.*;
import com.zpl.eshop.auth.service.AccountService;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 帐号管理 service 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/6/1 22:37
 **/
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    /**
     * 帐号管理DAO组件
     */
    @Autowired
    private AccountDAO accountDAO;

    /**
     * 帐号权限关联关系管理DAO组件
     */
    @Autowired
    private AccountPriorityRelationshipDAO priorityRelationDAO;

    /**
     * 帐号角色关联关系管理DAO组件
     */
    @Autowired
    private AccountRoleRelationshipDAO roleRelationDAO;

    @Autowired
    private DateProvider dateprovider;

    /**
     * 分页查询帐号
     *
     * @param query 查询条件
     * @return 帐号
     */
    @Override
    public List<AccountDTO> listByPage(AccountQuery query) throws Exception {
        List<AccountDO> accounts = accountDAO.listByPage(query);
        List<AccountDTO> resultAccounts = ObjectUtils.convertList(accounts, AccountDTO.class);
        return resultAccounts;
    }

    /**
     * 根据id获取帐号
     *
     * @param id id
     * @return 帐号
     */
    @Override
    public AccountDTO getById(Long id) throws Exception {
        AccountDO account = accountDAO.getById(id);
        AccountDTO resultAccount = account.clone(AccountDTO.class);

        List<AccountPriorityRelationshipDO> priorityRelations = priorityRelationDAO.listByAccountId(id);
        resultAccount.setPriorityRelations(ObjectUtils.convertList(priorityRelations, AccountPriorityRelationshipDTO.class));

        List<AccountRoleRelationshipDO> roleRelations = roleRelationDAO.listByAccountId(id);
        resultAccount.setRoleRelations(ObjectUtils.convertList(roleRelations, AccountRoleRelationshipDTO.class));

        return resultAccount;
    }

    /**
     * 新增帐号
     *
     * @param account 帐号
     * @return 处理结果
     */
    @Override
    public void save(AccountDTO account) throws Exception {
        AccountDO accountDO = account.clone(AccountDO.class);
        accountDO.setGmtCreate(dateprovider.getCurrentTime());
        accountDO.setGmtModified(dateprovider.getCurrentTime());
        accountDAO.save(accountDO);
        account.setId(accountDO.getId());
        saveRelations(account);
    }

    /**
     * 更新帐号
     *
     * @param account 帐号
     * @return 处理结果
     */
    @Override
    public void update(AccountDTO account) throws Exception {
        account.setGmtCreate(dateprovider.getCurrentTime());
        account.setGmtModified(dateprovider.getCurrentTime());
        accountDAO.update(account.clone(AccountDO.class));
        Long accountId = account.getId();

        roleRelationDAO.removeByAccountId(accountId);
        priorityRelationDAO.removeByAccountId(accountId);

        saveRelations(account);
    }

    /**
     * 删除帐号
     *
     * @param id 帐号id
     * @return 处理结果
     */
    @Override
    public void remove(Long id) {
        roleRelationDAO.removeByAccountId(id);
        priorityRelationDAO.removeByAccountId(id);
        accountDAO.remove(id);
    }

    /**
     * 保存关联关系
     *
     * @param account 帐号DTO
     * @throws Exception
     */
    private void saveRelations(AccountDTO account) throws Exception {
        Long accountId = account.getId();

        List<AccountPriorityRelationshipDTO> priorityRelations = account.getPriorityRelations();
        List<AccountRoleRelationshipDTO> roleRelations = account.getRoleRelations();


        for (AccountRoleRelationshipDTO roleRelation : roleRelations) {
            roleRelation.setAccountId(accountId);
            roleRelation.setGmtCreate(dateprovider.getCurrentTime());
            roleRelation.setGmtModified(dateprovider.getCurrentTime());
            roleRelationDAO.save(roleRelation.clone(AccountRoleRelationshipDO.class));
        }

        for (AccountPriorityRelationshipDTO priorityRelation : priorityRelations) {
            priorityRelation.setAccountId(accountId);
            priorityRelation.setGmtCreate(dateprovider.getCurrentTime());
            priorityRelation.setGmtModified(dateprovider.getCurrentTime());
            priorityRelationDAO.save(priorityRelation.clone(AccountPriorityRelationshipDO.class));
        }
    }
}
