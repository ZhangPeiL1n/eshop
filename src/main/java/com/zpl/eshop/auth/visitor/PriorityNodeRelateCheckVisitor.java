package com.zpl.eshop.auth.visitor;

import com.zpl.eshop.auth.composite.PriorityNode;
import com.zpl.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.zpl.eshop.auth.dao.PriorityDAO;
import com.zpl.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.zpl.eshop.auth.domain.PriorityDO;
import lombok.Getter;

import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/10 21:46
 **/
public class PriorityNodeRelateCheckVisitor implements PriorityNodeVisitor {
    private final PriorityDAO priorityDAO;
    private final RolePriorityRelationshipDAO rolePriorityRelationshipDAO;
    private final AccountPriorityRelationshipDAO accountPriorityRelationshipDAO;
    @Getter
    private Boolean relateCheckResult = false;

    public PriorityNodeRelateCheckVisitor(PriorityDAO priorityDAO, RolePriorityRelationshipDAO rolePriorityRelationshipDAO, AccountPriorityRelationshipDAO accountPriorityRelationshipDAO) {
        this.priorityDAO = priorityDAO;
        this.rolePriorityRelationshipDAO = rolePriorityRelationshipDAO;
        this.accountPriorityRelationshipDAO = accountPriorityRelationshipDAO;
    }

    /**
     * 访问树节点
     *
     * @param priorityNode 树节点
     */
    @Override
    public void visit(PriorityNode priorityNode) {

        List<PriorityDO> priorityDOList = priorityDAO.listChildPriorities(priorityNode.getParentId());
        if (priorityDOList != null && priorityDOList.size() > 0) {
            for (PriorityDO priorityDO : priorityDOList) {
                PriorityNode node = priorityDO.clone(PriorityNode.class);
                node.accept(this);
            }
        }

        if (relateCheck(priorityNode)) {
            this.relateCheckResult = true;
        }
    }

    /**
     * 检查权限是否被任何一个角色或者帐号关联
     *
     * @param priorityNode 权限树节点
     * @return 是否被关联
     */
    public Boolean relateCheck(PriorityNode priorityNode) {
        Long roleRelatedCount = rolePriorityRelationshipDAO.getCountByPriorityId(priorityNode.getId());
        if (roleRelatedCount != null && roleRelatedCount > 0) {
            return true;
        }

        Long accountRelatedCount = accountPriorityRelationshipDAO.getCountByPriorityId(priorityNode.getId());
        if (accountRelatedCount != null && accountRelatedCount > 0) {
            return true;
        }
        return false;
    }
}
