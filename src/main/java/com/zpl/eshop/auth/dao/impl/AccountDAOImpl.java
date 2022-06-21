package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.AccountDAO;
import com.zpl.eshop.auth.domain.AccountDO;
import com.zpl.eshop.auth.domain.AccountQuery;
import com.zpl.eshop.auth.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/5/28 16:36
 **/
@Repository
public class AccountDAOImpl implements AccountDAO {

    private static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);
    /**
     * 帐号管理模块Mapper组件
     */
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 分页查询帐号
     *
     * @param query 查询条件
     * @return 帐号
     */
    @Override
    public List<AccountDO> listByPage(AccountQuery query) {
        return accountMapper.listByPage(query);
    }


    /**
     * 新增帐号
     *
     * @param account 帐号
     */
    @Override
    public void save(AccountDO account) {
        accountMapper.save(account);

    }

    /**
     * 根据id查询帐号
     *
     * @param id 帐号id
     * @return 帐号
     */
    public AccountDO getById(Long id) {
        return accountMapper.getById(id);
    }


    /**
     * 更新帐号信息
     *
     * @param account 帐号信息
     */
    public void update(AccountDO account) {
        accountMapper.update(account);
    }

    /**
     * 根据id删除帐号
     *
     * @param id 帐号id
     */
    @Override
    public void remove(Long id) {
        accountMapper.remove(id);
    }
}
