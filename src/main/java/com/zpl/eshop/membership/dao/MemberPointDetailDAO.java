package com.zpl.eshop.membership.dao;

import com.zpl.eshop.membership.domain.MemberPointDetailDO;
import com.zpl.eshop.membership.domain.MemberPointDetailQuery;

import java.util.List;

/**
 * 会员积分变更明细管理DAO接口
 *
 * @author ZhangPeiL1n
 */
public interface MemberPointDetailDAO {

    /**
     * 分页查询会员积分变更明细
     *
     * @param query 查询调价你
     * @return 会员积分变更明细
     */
    List<MemberPointDetailDO> listByPage(MemberPointDetailQuery query) throws Exception;

    /**
     * 新增会员积分明细
     *
     * @param memberPointDetail 会员积分明细
     */
    void save(MemberPointDetailDO memberPointDetail) throws Exception;

}
