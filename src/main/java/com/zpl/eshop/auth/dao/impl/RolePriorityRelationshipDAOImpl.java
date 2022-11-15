package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.zpl.eshop.auth.domain.RolePriorityRelationshipDO;
import com.zpl.eshop.auth.mapper.RolePriorityRelationshipMapper;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色和权限关系管理模块的DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class RolePriorityRelationshipDAOImpl implements RolePriorityRelationshipDAO {

    /**
     * 角色和权限关系管理模块的mapper组件
     */
    @Autowired
    private RolePriorityRelationshipMapper rolePriorityRelationshipMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增账号和权限的关联关系
     *
     * @param rolePriorityRelationship 帐号权限关联关系
     * @throws Exception
     */
    @Override
    public void save(RolePriorityRelationshipDO rolePriorityRelationship) throws Exception {
        rolePriorityRelationship.setGmtCreate(dateProvider.getCurrentTime());
        rolePriorityRelationship.setGmtModified(dateProvider.getCurrentTime());
        rolePriorityRelationshipMapper.save(rolePriorityRelationship);
    }

    /**
     * 根据权限id查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     * @throws Exception
     */
    @Override
    public Long countByPriorityId(Long priorityId) throws Exception {
        return rolePriorityRelationshipMapper.countByPriorityId(priorityId);
    }

    /**
     * 根据角色id查询角色和权限的关系
     *
     * @param roleId 角色id
     * @return 角色权限关系DO对象集合
     * @throws Exception
     */
    @Override
    public List<RolePriorityRelationshipDO> listByRoleId(Long roleId) throws Exception {
        return rolePriorityRelationshipMapper.listByRoleId(roleId);
    }

    /**
     * 根据角色id删除角色权限关联关系
     *
     * @param roleId 角色id
     * @throws Exception
     */
    @Override
    public void removeByRoleId(Long roleId) throws Exception {
        rolePriorityRelationshipMapper.removeByRoleId(roleId);
    }
}
