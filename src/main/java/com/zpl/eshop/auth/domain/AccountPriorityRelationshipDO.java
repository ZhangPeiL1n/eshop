package com.zpl.eshop.auth.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 帐号权限关系DO
 *
 * @author ZhangPeiL1n
 * @date 2022/2/1 23:35
 **/
@Getter
@Setter
@ToString
public class AccountPriorityRelationshipDO {

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
}
