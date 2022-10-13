package com.zpl.eshop.membership.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 会员积分变更明细
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberPointDetailDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 用户账号id
     */
    private Long userAccountId;

    /**
     * 本次变更之前的积分
     */
    private Long oldMemberPoint;

    /**
     * 本次变更的积分
     */
    private Long updatedMemberPoint;

    /**
     * 本次变更之后的积分
     */
    private Long newMemberPoint;

    /**
     * 本次变更原因
     */
    private String updateReason;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
