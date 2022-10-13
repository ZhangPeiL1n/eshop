package com.zpl.eshop.auth.service.impl;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 权限
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Priority extends AbstractObject {

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
    private Date gmtCreate;

    /**
     * 权限的修改时间
     */
    private Date gmtModified;

    /**
     * 子权限节点
     */
    private List<Priority> children = new ArrayList<>();

    /**
     * 接收一个权限树访问者
     *
     * @param operation 操作
     */
    public <T> T execute(PriorityOperation<T> operation) throws Exception {
        return operation.doExecute(this);
    }
}
