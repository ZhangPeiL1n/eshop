package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.zpl.eshop.auth.mapper.RolePriorityRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 角色和权限关系管理模块 DAO 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/10 22:04
 **/
@Repository
public class RolePriorityRelationshipDAOImpl implements RolePriorityRelationshipDAO {

    /**
     * 角色和权限管理关系模块 mapper
     */
    @Autowired
    RolePriorityRelationshipMapper rolePriorityRelationshipMapper;

    /**
     * 根据权限 id 查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    @Override
    public Long getCountByPriorityId(Long priorityId) {
        return rolePriorityRelationshipMapper.getCountByPriorityId(priorityId);
    }
}
