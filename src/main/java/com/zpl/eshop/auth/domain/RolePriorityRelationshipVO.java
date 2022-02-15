package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 角色权限关系VO
 *
 * @author ZhangPeiL1n
 * @date 2022/2/1 23:35
 **/
@Getter
@Setter
@ToString
public class RolePriorityRelationshipVO extends AbstractObject {

    /**
     * id
     */
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
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
