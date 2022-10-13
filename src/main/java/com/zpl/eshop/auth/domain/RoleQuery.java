package com.zpl.eshop.auth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色查询条件
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 每页记录数
     */
    private Integer size;

    /**
     * 角色编号
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;
}
