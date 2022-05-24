package com.zpl.eshop.auth.service.impl;

import com.zpl.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.zpl.eshop.auth.dao.RoleDAO;
import com.zpl.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.zpl.eshop.auth.domain.*;
import com.zpl.eshop.auth.service.RoleService;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/2/15 17:01
 **/
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    /**
     * 角色管理模块DAO组件
     */
    @Autowired
    private RoleDAO roleDAO;
    /**
     * 角色权限关联关系管理模块DAO组件
     */
    @Autowired
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;
    /**
     * 帐号角色关联关系管理模块DAO组件
     */
    @Autowired
    private AccountRoleRelationshipDAO accountRoleRelationshipDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询角色
     *
     * @param query 查询条件
     * @return 角色DO集合
     */
    @Override
    public List<RoleDTO> listByPage(RoleQuery query) {
        try {
            return ObjectUtils.convertList(roleDAO.listByPage(query), RoleDTO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据id查找角色
     *
     * @param id 角色id
     * @return 角色DO对象
     */
    @Override
    public RoleDTO getById(Long id) {
        try {
            RoleDO roleDO = roleDAO.getById(id);
            if (roleDO == null) {
                return null;
            }
            List<RolePriorityRelationshipDO> relations = rolePriorityRelationshipDAO.listByRoleId(roleDO.getId());
            RoleDTO roleDTO = roleDO.clone(RoleDTO.class);
            roleDTO.setRolePriorityRelations(ObjectUtils.convertList(relations, RolePriorityRelationshipDTO.class));
            return roleDTO;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 新增角色
     *
     * @param roleDTO 角色DO
     */
    @Override
    public Boolean save(RoleDTO roleDTO) {
        try {
            // 存储角色
            roleDTO.setGmtCreate(dateProvider.getCurrentTime());
            roleDTO.setGmtModified(dateProvider.getCurrentTime());
            Long roleId = roleDAO.save(roleDTO.clone(RoleDO.class));

            // 存储角色权限关联关系
            List<RolePriorityRelationshipDTO> rolePriorityRelations = roleDTO.getRolePriorityRelations();
            for (RolePriorityRelationshipDTO relationshipDTO : rolePriorityRelations) {
                relationshipDTO.setRoleId(roleId);
                relationshipDTO.setGmtCreate(dateProvider.getCurrentTime());
                relationshipDTO.setGmtModified(dateProvider.getCurrentTime());
                rolePriorityRelationshipDAO.save(relationshipDTO.clone(RolePriorityRelationshipDO.class));
            }
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新角色
     *
     * @param roleDTO 角色DO
     */
    @Override
    public Boolean update(RoleDTO roleDTO) {
        try {
            roleDTO.setGmtModified(dateProvider.getCurrentTime());
            roleDAO.update(roleDTO.clone(RoleDO.class));
            rolePriorityRelationshipDAO.removeByRoleId(roleDTO.getId());
            for (RolePriorityRelationshipDTO rolePriorityRelationshipDTO : roleDTO.getRolePriorityRelations()) {
                rolePriorityRelationshipDTO.setRoleId(roleDTO.getId());
                rolePriorityRelationshipDTO.setGmtModified(dateProvider.getCurrentTime());
                rolePriorityRelationshipDAO.save(rolePriorityRelationshipDTO.clone(RolePriorityRelationshipDO.class));
            }
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 根据id删除
     *
     * @param id id
     */
    @Override
    public Boolean remove(Long id) {
        try {
            Long count = accountRoleRelationshipDAO.countByRoleId(id);
            if (count > 0) {
                return false;
            }
            roleDAO.remove(id);
            rolePriorityRelationshipDAO.removeByRoleId(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
