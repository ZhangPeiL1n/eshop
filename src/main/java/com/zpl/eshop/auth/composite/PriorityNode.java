package com.zpl.eshop.auth.composite;

import com.zpl.eshop.auth.visitor.PriorityNodeVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/10 21:39
 **/
@Getter
@Setter
public class PriorityNode {

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
     * 权限创建时间
     */
    private Date gmtCreate;
    /**
     * 权限修改时间
     */
    private Date gmtModified;

    /**
     * 子节点
     */
    private List<PriorityNode> childPriorityNode;

    public void accept(PriorityNodeVisitor priorityNodeVisitor) {
        priorityNodeVisitor.visit(this);
    }
}
