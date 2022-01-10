package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.zpl.eshop.auth.mapper.AccountPriorityRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 帐号和权限管理模块 DAO 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/10 22:07
 **/
@Repository
public class AccountPriorityRelationshipDAOImpl implements AccountPriorityRelationshipDAO {

    /**
     * 账户和权限关系管理模块 mapper
     */
    @Autowired
    private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;

    /**
     * 根据权限 id 查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    @Override
    public Long getCountByPriorityId(Long priorityId) {
        return accountPriorityRelationshipMapper.getCountByPriorityId(priorityId);
    }
}
