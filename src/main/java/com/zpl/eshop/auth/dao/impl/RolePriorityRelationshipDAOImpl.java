package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.zpl.eshop.auth.domain.RolePriorityRelationshipDO;
import com.zpl.eshop.auth.mapper.RolePriorityRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(RolePriorityRelationshipDAOImpl.class);

    /**
     * 新增角色权限关联关系
     *
     * @param rolePriorityRelationshipDO 角色权限关联关系DO
     * @return 新增成功返回true
     */
    @Override
    public Boolean save(RolePriorityRelationshipDO rolePriorityRelationshipDO) {
        try {
            rolePriorityRelationshipMapper.save(rolePriorityRelationshipDO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 根据权限 id 查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    @Override
    public Long countByPriorityId(Long priorityId) {
        try {
            return rolePriorityRelationshipMapper.getCountByPriorityId(priorityId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
