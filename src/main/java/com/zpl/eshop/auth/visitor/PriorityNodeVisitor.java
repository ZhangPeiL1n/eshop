package com.zpl.eshop.auth.visitor;

import com.zpl.eshop.auth.composite.PriorityNode;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/10 21:40
 **/
public interface PriorityNodeVisitor {

    /**
     * 访问者模式接口
     *
     * @param priorityNode 树节点
     */
    void visit(PriorityNode priorityNode);
}
