package com.zpl.eshop.membership.dao.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.membership.dao.MemberPointDetailDAO;
import com.zpl.eshop.membership.domain.MemberPointDetailDO;
import com.zpl.eshop.membership.domain.MemberPointDetailQuery;
import com.zpl.eshop.membership.mapper.MemberPointDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员积分变更明细管理DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class MemberPointDetailDAOImpl implements MemberPointDetailDAO {

    /**
     * 会员积分明细管理mapper组件
     */
    @Autowired
    private MemberPointDetailMapper memberPointDetailMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询会员积分变更明细
     *
     * @param query 查询调价你
     * @return 会员积分变更明细
     */
    @Override
    public List<MemberPointDetailDO> listByPage(MemberPointDetailQuery query) throws Exception {
        return memberPointDetailMapper.listByPage(query);
    }

    /**
     * 新增会员积分明细
     *
     * @param memberPointDetail 会员积分明细
     */
    @Override
    public void save(MemberPointDetailDO memberPointDetail) throws Exception {
        memberPointDetail.setGmtCreate(dateProvider.getCurrentTime());
        memberPointDetail.setGmtModified(dateProvider.getCurrentTime());
        memberPointDetailMapper.save(memberPointDetail);
    }

}
