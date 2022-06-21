package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.AccountDO;
import com.zpl.eshop.auth.domain.AccountQuery;

import java.util.List;

/**
 * 帐号管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/5/28 16:35
 **/
public interface AccountDAO {


    /**
     * 分页查询帐号
     *
     * @param query 查询条件
     * @return 帐号
     */
    List<AccountDO> listByPage(AccountQuery query);


    /**
     * 新增帐号
     *
     * @param account 帐号
     */
    void save(AccountDO account);

    /**
     * 根据id查询帐号
     *
     * @param id 帐号id
     * @return 帐号
     */
    AccountDO getById(Long id);


    /**
     * 更新帐号信息
     *
     * @param account 帐号信息
     */
    void update(AccountDO account);

    /**
     * 根据id删除帐号
     *
     * @param id 帐号id
     */
    void remove(Long id);
}
