package com.zpl.eshop.auth.service;

import com.zpl.eshop.auth.domain.AccountDTO;
import com.zpl.eshop.auth.domain.AccountQuery;

import java.util.List;

/**
 * 账号管理service组件接口
 *
 * @author ZhangPeiL1n
 */
public interface AccountService {

    /**
     * 分页查询账号
     *
     * @param query 查询条件
     * @return 账号
     */
    List<AccountDTO> listByPage(AccountQuery query) throws Exception;

    /**
     * 根据id查询账号
     *
     * @param id 账号id
     * @return 账号
     */
    AccountDTO getById(Long id) throws Exception;

    /**
     * 新增账号
     *
     * @param account 账号
     * @return 处理结果
     */
    void save(AccountDTO account) throws Exception;

    /**
     * 更新账号
     *
     * @param account 账号
     * @return 处理结果
     */
    void update(AccountDTO account) throws Exception;

    /**
     * 更新密码
     *
     * @param account 账号
     */
    void updatePassword(AccountDTO account) throws Exception;

    /**
     * 删除账号
     *
     * @param id 账号id
     * @return 处理结果
     */
    void remove(Long id) throws Exception;

}
