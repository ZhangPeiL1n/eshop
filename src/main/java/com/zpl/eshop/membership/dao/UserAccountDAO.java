package com.zpl.eshop.membership.dao;

import com.zpl.eshop.membership.domain.UserAccountDO;

import java.util.List;

/**
 * 用户账号管理DAO接口
 *
 * @author ZhangPeiL1n
 */
public interface UserAccountDAO {

    /**
     * 新增用户账号
     *
     * @param userAccount 用户账号
     * @return 帐号
     * @throws Exception
     */
    UserAccountDO save(UserAccountDO userAccount) throws Exception;

    /**
     * 为登录来统计是否有对应的账号在
     *
     * @param userAccount 用户账号
     * @return 帐号
     * @throws Exception
     */
    UserAccountDO getForLogin(UserAccountDO userAccount) throws Exception;

    /**
     * 根据id查询用户账号
     *
     * @param id 用户账号id
     * @return 用户账号
     * @throws Exception
     */
    UserAccountDO getById(Long id) throws Exception;

    /**
     * 更新密码
     *
     * @param userAccount 用户账号
     * @throws Exception
     */
    void updatePassword(UserAccountDO userAccount) throws Exception;

    /**
     * 查询所有用户账号
     *
     * @return 帐号
     * @throws Exception
     */
    List<UserAccountDO> listAll() throws Exception;

}
