package com.zpl.eshop.membership.domain;

import lombok.Data;

/**
 * 会员等级明细查询条件
 *
 * @author ZhangPeiL1n
 */
@Data
public class MemberLevelDetailQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 每页显示的数据条数
     */
    private Integer size;

    /**
     * 用户账号id
     */
    private Long userAccountId;
}
