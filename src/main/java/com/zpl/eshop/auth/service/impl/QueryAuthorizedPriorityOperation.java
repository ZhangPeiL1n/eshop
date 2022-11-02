package com.zpl.eshop.auth.service.impl;

import com.zpl.eshop.auth.dao.PriorityDAO;
import com.zpl.eshop.auth.domain.PriorityDO;
import com.zpl.eshop.common.constant.CollectionSize;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询授权权限的操作
 *
 * @author ZhangPeiL1n
 */
@Component
@Scope("prototype")
@Data
public class QueryAuthorizedPriorityOperation implements PriorityOperation<Boolean> {

    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 权限管理DAO组件
     */
    @Autowired
    private PriorityDAO priorityDAO;

    /**
     * 执行这个操作
     */
    @Override
    public Boolean doExecute(Priority priority) throws Exception {
        List<Priority> targetChildren = new ArrayList<>();

        Map<String, Object> parameters = new HashMap<>(CollectionSize.DEFAULT);
        parameters.put("accountId", accountId);
        parameters.put("parentId", priority.getId());

        List<PriorityDO> children = priorityDAO.listAuthorizedByAccountId(parameters);
        for (PriorityDO child : children) {
            Priority targetChild = child.clone(Priority.class);
            targetChild.execute(this);
            targetChildren.add(targetChild);
        }

        priority.setChildren(targetChildren);

        return true;
    }
}
