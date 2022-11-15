package com.zpl.eshop.membership.dao;

import com.zpl.eshop.membership.domain.UserDetailDO;

/**
 * 用户详细信息管理DAO接口
 *
 * @author ZhangPeiL1n
 */
public interface UserDetailDAO {

    /**
     * 新增用户详细信息
     *
     * @param userDetail 用户详细信息
     * @throws Exception
     */
    void save(UserDetailDO userDetail) throws Exception;

    /**
     * 根据用户账号id查询用户详细信息
     *
     * @param userAccountId 用户账号id
     * @return 用户详细信息
     * @throws Exception
     */
    UserDetailDO getByUserAccountId(Long userAccountId) throws Exception;

    /**
     * 更新用户详细信息
     *
     * @param userDetail 用户详细信息
     *                   @throws Exception
     */
    void updateByUserAccountId(UserDetailDO userDetail) throws Exception;

}
