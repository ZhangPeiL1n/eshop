package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 账号DTO类
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;
	
    /**
     * 用户名
     */
    private String username;
	
    /**
     * 密码
     */
    private String password;
	
    /**
     * 员工姓名
     */
    private String name;
	
    /**
     * 账号备注
     */
    private String remark;
	
    /**
     * 创建时间
     */
    private Date gmtCreate;
	
    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 账号和角色的关联关系
     */
    private List<AccountRoleRelationshipDTO> roleRelations;

    /**
     * 账号和权限的关联关系
     */
    private List<AccountPriorityRelationshipDTO> priorityRelations;
}
