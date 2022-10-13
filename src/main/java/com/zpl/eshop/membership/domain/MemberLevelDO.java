package com.zpl.eshop.membership.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 会员等级
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberLevelDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 用户账号id
     */
    private Long userAccountId;

    /**
     * 成长值
     */
    private Long growthValue;

    /**
     * 会员等级
     */
    private Integer level;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
