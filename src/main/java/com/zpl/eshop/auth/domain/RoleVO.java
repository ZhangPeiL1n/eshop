package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 角色VO类
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 角色编号
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色备注
     */
    private String remark;

    /**
     * 角色的创建时间
     */
    private Date gmtCreate;

    /**
     * 角色的修改时间
     */
    private Date gmtModified;

    /**
     * 角色权限关系集合
     */
    private List<RolePriorityRelationshipVO> rolePriorityRelations;
}
