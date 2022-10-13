package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.AbstractObject;

/**
 * 权限VO类
 *
 * @author ZhangPeiL1n
 */
public class PriorityVO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 权限编号
     */
    private String code;

    /**
     * 权限URL
     */
    private String url;

    /**
     * 权限备注
     */
    private String priorityComment;

    /**
     * 权限类型
     */
    private Integer priorityType;

    /**
     * 父权限id
     */
    private Long parentId;

    /**
     * 权限的创建时间
     */
    private String gmtCreate;

    /**
     * 权限的修改时间
     */
    private String gmtModified;
}
