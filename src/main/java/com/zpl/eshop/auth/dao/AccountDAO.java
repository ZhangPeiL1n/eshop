package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.AccountDO;
import com.zpl.eshop.auth.domain.AccountQuery;

import java.util.List;

/**
 * 账号管理DAO组件接口
 *
 * @author ZhangPeiL1n
 */
public interface AccountDAO {

    /**
     * 分页查询账号
     *
     * @param query 账号查询条件
     * @return 账号
     * @throws Exception
     */
    List<AccountDO> listByPage(AccountQuery query) throws Exception;

    /**
     * 根据id查询账号
     *
     * @param id 账号id
     * @return 账号
     * @throws Exception
     */
    AccountDO getById(Long id) throws Exception;

    /**
     * 新增账号
     *
     * @param account 账号
     * @return id
     * @throws Exception
     */
    Long save(AccountDO account) throws Exception;

    /**
     * 更新账号
     *
     * @param account 账号
     * @throws Exception
     */
    void update(AccountDO account) throws Exception;

    /**
     * 更新密码
     *
     * @param account 账号
     * @throws Exception
     */
    void updatePassword(AccountDO account) throws Exception;

    /**
     * 删除账号
     *
     * @param id 账号id
     * @throws Exception
     */
    void remove(Long id) throws Exception;

}
