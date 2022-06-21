package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 帐号角色关系DO类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 18:28
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class AccountRoleRelationshipDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 帐号id
     */
    private Long accountId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
