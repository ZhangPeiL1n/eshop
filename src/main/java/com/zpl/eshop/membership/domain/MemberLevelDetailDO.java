package com.zpl.eshop.membership.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 会员等级变更明细
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberLevelDetailDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 用户账号id
     */
    private Long userAccountId;

    /**
     * 本次变更之前的成长值
     */
    private Long oldGrowthValue;

    /**
     * 本次变更的成长值
     */
    private Long updatedGrowthValue;

    /**
     * 本次变更之后的成长值
     */
    private Long newGrowthValue;

    /**
     * 本次变更之前的会员等级
     */
    private Integer oldMemberLevel;

    /**
     * 本次变更之后的会员等级
     */
    private Integer newMemberLevel;

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
