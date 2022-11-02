package com.zpl.eshop.membership.dao;

import com.zpl.eshop.membership.domain.MemberPointDO;

/**
 * 会员积分管理DAO接口
 *
 * @author ZhangPeiL1n
 */
public interface MemberPointDAO {

    /**
     * 根据用户账号id查询会员积分
     *
     * @param userAccountId 用户账号id
     * @return 会员积分
     */
    MemberPointDO getByUserAccountId(Long userAccountId) throws Exception;

    /**
     * 新增会员积分
     *
     * @param memberPoint 会员积分
     */
    void save(MemberPointDO memberPoint) throws Exception;

    /**
     * 更新会员积分
     *
     * @param memberPoint 会员积分
     */
    void update(MemberPointDO memberPoint) throws Exception;

}