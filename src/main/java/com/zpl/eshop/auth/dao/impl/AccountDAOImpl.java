package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.AccountDAO;
import com.zpl.eshop.auth.domain.AccountDO;
import com.zpl.eshop.auth.domain.AccountQuery;
import com.zpl.eshop.auth.mapper.AccountMapper;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账号管理DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class AccountDAOImpl implements AccountDAO {

    /**
     * 账号管理mapper组件
     */
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询账号
     *
     * @param query 账号查询条件
     * @return 账号
     * @throws Exception
     */
    @Override
    public List<AccountDO> listByPage(AccountQuery query) throws Exception {
        return accountMapper.listByPage(query);
    }

    /**
     * 根据id查询账号
     *
     * @param id 账号id
     * @return 账号
     * @throws Exception
     */
    @Override
    public AccountDO getById(Long id) throws Exception {
        return accountMapper.getById(id);
    }

    /**
     * 新增账号
     *
     * @param account 账号
     * @return id
     * @throws Exception
     */
    @Override
    public Long save(AccountDO account) throws Exception {
        account.setGmtCreate(dateProvider.getCurrentTime());
        account.setGmtModified(dateProvider.getCurrentTime());
        accountMapper.save(account);
        return account.getId();
    }

    /**
     * 更新账号
     *
     * @param account 账号
     * @throws Exception
     */
    @Override
    public void update(AccountDO account) throws Exception {
        account.setGmtModified(dateProvider.getCurrentTime());
        accountMapper.update(account);
    }

    /**
     * 更新密码
     *
     * @param account 账号
     * @throws Exception
     */
    @Override
    public void updatePassword(AccountDO account) throws Exception {
        account.setGmtModified(dateProvider.getCurrentTime());
        accountMapper.updatePassword(account);
    }

    /**
     * 删除账号
     *
     * @param id 账号id
     * @throws Exception
     */
    @Override
    public void remove(Long id) throws Exception {
        accountMapper.remove(id);
    }

}
