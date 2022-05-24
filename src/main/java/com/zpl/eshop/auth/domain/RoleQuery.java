package com.zpl.eshop.auth.domain;

import lombok.Data;

/**
 * 角色查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 15:48
 **/
@Data
public class RoleQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offsite;

    /**
     * 分页查询长度
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
