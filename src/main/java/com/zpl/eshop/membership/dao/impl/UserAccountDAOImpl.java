package com.zpl.eshop.membership.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.membership.dao.UserAccountDAO;
import com.zpl.eshop.membership.domain.UserAccountDO;
import com.zpl.eshop.membership.mapper.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户账号管理DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class UserAccountDAOImpl implements UserAccountDAO {

    /**
     * 用户账号管理mapper组件
     */
    @Autowired
    private UserAccountMapper userAccountMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增用户账号
     *
     * @param userAccount 用户账号
     * @throws Exception
     */
    @Override
    public UserAccountDO save(UserAccountDO userAccount) throws Exception {
        userAccount.setGmtCreate(dateProvider.getCurrentTime());
        userAccount.setGmtModified(dateProvider.getCurrentTime());
        userAccountMapper.save(userAccount);
        return userAccount;
    }

    /**
     * 为登录来统计是否有对应的账号在
     *
     * @param userAccount 用户账号
     * @return 帐号
     * @throws Exception
     */
    @Override
    public UserAccountDO getForLogin(UserAccountDO userAccount) throws Exception {
        return userAccountMapper.getForLogin(userAccount);
    }

    /**
     * 根据id查询用户账号
     *
     * @param id 用户账号id
     * @return 用户账号
     * @throws Exception
     */
    @Override
    public UserAccountDO getById(Long id) throws Exception {
        return userAccountMapper.getById(id);
    }

    /**
     * 更新密码
     *
     * @param userAccount 用户账号
     * @throws Exception
     */
    @Override
    public void updatePassword(UserAccountDO userAccount) throws Exception {
        userAccount.setGmtModified(dateProvider.getCurrentTime());
        userAccountMapper.updatePassword(userAccount);
    }

    /**
     * 查询所有用户账号
     *
     * @return 帐号
     * @throws Exception
     */
    @Override
    public List<UserAccountDO> listAll() throws Exception {
        return userAccountMapper.listAll();
    }
}
