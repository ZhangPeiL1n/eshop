package com.zpl.eshop.membership.service.impl;

import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.membership.dao.MemberPointDetailDAO;
import com.zpl.eshop.membership.domain.MemberPointDetailDTO;
import com.zpl.eshop.membership.domain.MemberPointDetailQuery;
import com.zpl.eshop.membership.service.MemberPointDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员积分明细管理service组件
 *
 * @author ZhangPeiL1n
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberPointDetailServiceImpl implements MemberPointDetailService {

    /**
     * 会员积分明细管理DAO组件
     */
    @Autowired
    private MemberPointDetailDAO memberPointDetailDAO;

    /**
     * 分页查询会员积分变更明细
     *
     * @param query 查询调价你
     * @return 会员积分变更明细
     */
    @Override
    public List<MemberPointDetailDTO> listByPage(
            MemberPointDetailQuery query) throws Exception {
        return ObjectUtils.convertList(memberPointDetailDAO.listByPage(query),
                MemberPointDetailDTO.class);
    }

}
