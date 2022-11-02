package com.zpl.eshop.auth.service.impl;

import com.zpl.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.zpl.eshop.auth.dao.PriorityDAO;
import com.zpl.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.zpl.eshop.auth.domain.PriorityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检查权限是否被关联的操作
 *
 * @author ZhangPeiL1n
 */
@Component
@Scope("prototype")
public class RelatedCheckPriorityOperation implements PriorityOperation<Boolean> {

    /**
     * 关联检查结果
     */
    private Boolean relateCheckResult = false;

    /**
     * 权限管理模块的DAO组件
     */
    @Autowired
    private PriorityDAO priorityDAO;

    /**
     * 角色和权限关系管理模块的DAO组件
     */
    @Autowired
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;

    /**
     * 账号和权限关系管理模块的DAO组件
     */
    @Autowired
    private AccountPriorityRelationshipDAO accountPriorityRelationshipDAO;

    /**
     * 访问权限树节点
     */
    @Override
    public Boolean doExecute(Priority node) throws Exception {
        List<PriorityDO> priorities = priorityDAO
                .listChildPriorities(node.getId());

        if (priorities != null && priorities.size() > 0) {
            for (PriorityDO priority : priorities) {
                Priority priorityNode = priority.clone(Priority.class);
                priorityNode.execute(this);
            }
        }

        if (relateCheck(node)) {
            this.relateCheckResult = true;
        }

        return this.relateCheckResult;
    }

    /**
     * 检查权限是否被任何一个角色或者是账号关联了
     *
     * @param node 权限树节点
     * @return 是否被任何一个角色或者是账号关联了，如果有关联则为true；如果没有关联则为false
     */
    private Boolean relateCheck(Priority node) {
        Long roleRelatedCount = rolePriorityRelationshipDAO
                .countByPriorityId(node.getId());
        if (roleRelatedCount != null && roleRelatedCount > 0) {
            return true;
        }

        Long accountRelatedCount = accountPriorityRelationshipDAO
                .countByPriorityId(node.getId());
        return accountRelatedCount != null && accountRelatedCount > 0;
    }

    public Boolean getRelateCheckResult() {
        return relateCheckResult;
    }

}
