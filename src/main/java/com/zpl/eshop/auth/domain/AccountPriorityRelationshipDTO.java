package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 账号和权限的关联关系DO类
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountPriorityRelationshipDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 权限id
     */
    private Long priorityId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
