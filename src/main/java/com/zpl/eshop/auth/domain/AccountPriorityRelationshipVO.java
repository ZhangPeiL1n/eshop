package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 帐号权限关系DO
 *
 * @author ZhangPeiL1n
 * @date 2022/2/1 23:35
 **/
@Getter
@Setter
@ToString
public class AccountPriorityRelationshipVO extends AbstractObject {

    /**
     * id
     */
    private Long id;
    /**
     * 帐号id
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

    /**
     * 帐号角色关联关系
     */
    private List<AccountRoleRelationshipVO> roleRelations;

    /**
     * 帐号权限关联关系
     */
    private List<AccountPriorityRelationshipVO> priorityRelations;
}
