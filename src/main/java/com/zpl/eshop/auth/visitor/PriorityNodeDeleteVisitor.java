package com.zpl.eshop.auth.visitor;

import com.zpl.eshop.auth.composite.PriorityNode;
import com.zpl.eshop.auth.dao.PriorityDAO;
import com.zpl.eshop.auth.domain.PriorityDO;

import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/10 22:28
 **/
public class PriorityNodeDeleteVisitor implements PriorityNodeVisitor {

    /**
     * 权限管理模块DAO组件
     */
    private final PriorityDAO priorityDAO;

    public PriorityNodeDeleteVisitor(PriorityDAO priorityDAO) {
        this.priorityDAO = priorityDAO;
    }

    /**
     * 访问权限树
     *
     * @param priorityNode 树节点
     */
    @Override
    public void visit(PriorityNode priorityNode) {
        List<PriorityDO> priorityDOList = priorityDAO.listChildPriorities(priorityNode.getId());
        if (priorityDOList != null && priorityDOList.size() > 0) {
            for (PriorityDO priorityDO : priorityDOList) {
                PriorityNode node = priorityDO.clone(PriorityNode.class);
                node.accept(this);
            }
        }
        priorityDAO.deletePriority(priorityNode.getId());
    }
}
