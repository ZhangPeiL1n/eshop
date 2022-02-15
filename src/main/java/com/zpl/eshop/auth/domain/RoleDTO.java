package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * 角色DO对象
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 15:41
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class RoleDTO extends AbstractObject {

    private static final Logger logger = LoggerFactory.getLogger(RoleDTO.class);

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
     * 角色说明备注
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
     * 角色权限关系集合
     */
    private List<RolePriorityRelationshipDTO> rolePriorityRelations;

}
