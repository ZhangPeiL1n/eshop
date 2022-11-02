package com.zpl.eshop.membership.dao;

import com.zpl.eshop.membership.domain.MemberLevelDO;

/**
 * 会员等级管理DAO接口
 *
 * @author ZhangPeiL1n
 */
public interface MemberLevelDAO {

    /**
     * 根据用户账号id查询会员等级
     *
     * @param userAccountId 用户账号id
     * @return 会员等级
     */
    MemberLevelDO getByUserAccountId(Long userAccountId) throws Exception;

    /**
     * 新增会员等级
     *
     * @param memberLevel 会员等级
     */
    void save(MemberLevelDO memberLevel) throws Exception;

    /**
     * 更新会员等级
     *
     * @param memberLevel 会员等级
     */
    void update(MemberLevelDO memberLevel) throws Exception;

}