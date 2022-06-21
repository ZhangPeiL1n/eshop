package com.zpl.eshop.auth.service;

import com.zpl.eshop.auth.domain.AccountDTO;
import com.zpl.eshop.auth.domain.AccountQuery;

import java.util.List;

/**
 * 帐号管理模块Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/6/1 22:27
 **/
public interface AccountService {

    /**
     * 分页查询帐号
     *
     * @param query 查询条件
     * @return 帐号
     */
    List<AccountDTO> listByPage(AccountQuery query) throws Exception;

    /**
     * 根据id获取帐号
     *
     * @param id id
     * @return 帐号
     */
    AccountDTO getById(Long id) throws Exception;

    /**
     * 新增帐号
     *
     * @param account 帐号
     * @return 处理结果
     */
    void save(AccountDTO account) throws Exception;

    /**
     * 更新帐号
     *
     * @param account 帐号
     * @return 处理结果
     */
    void update(AccountDTO account) throws Exception;

    /**
     * 删除帐号
     *
     * @param id 帐号id
     * @return 处理结果
     */
    void remove(Long id) throws Exception;

}
