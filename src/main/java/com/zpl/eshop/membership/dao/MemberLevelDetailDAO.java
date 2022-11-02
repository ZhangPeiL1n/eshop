package com.zpl.eshop.membership.dao;

import com.zpl.eshop.membership.domain.MemberLevelDetailDO;
import com.zpl.eshop.membership.domain.MemberLevelDetailQuery;

import java.util.List;

/**
 * 会员等级变更明细管理DAO接口
 *
 * @author ZhangPeiL1n
 */
public interface MemberLevelDetailDAO {

    /**
     * 分页查询会员等级变更明细
     *
     * @param query 查询调价你
     * @return 会员等级变更明细
     * @throws Exception
     */
    List<MemberLevelDetailDO> listByPage(MemberLevelDetailQuery query) throws Exception;

    /**
     * 新增会员等级明细
     *
     * @param memberLevelDetail 会员等级明细
     * @throws Exception
     */
    void save(MemberLevelDetailDO memberLevelDetail) throws Exception;

}
