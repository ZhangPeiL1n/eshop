package com.zpl.eshop.membership.service;

import com.zpl.eshop.membership.domain.MemberPointDetailDTO;
import com.zpl.eshop.membership.domain.MemberPointDetailQuery;

import java.util.List;

/**
 * 会员积分明细管理service接口
 *
 * @author ZhangPeiL1n
 */
public interface MemberPointDetailService {

    /**
     * 分页查询会员积分变更明细
     *
     * @param query 查询调价你
     * @return 会员积分变更明细
     * @throws Exception
     */
    List<MemberPointDetailDTO> listByPage(MemberPointDetailQuery query) throws Exception;

}
