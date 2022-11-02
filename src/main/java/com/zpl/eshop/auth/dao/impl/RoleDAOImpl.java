package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.RoleDAO;
import com.zpl.eshop.auth.domain.RoleDO;
import com.zpl.eshop.auth.domain.RoleQuery;
import com.zpl.eshop.auth.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色管理模块DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class RoleDAOImpl implements RoleDAO {

    /**
     * 角色管理模块mapper组件
     */
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 分页查询角色
     *
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    @Override
    public List<RoleDO> listByPage(RoleQuery query) {
        return roleMapper.listByPage(query);
    }

    /**
     * 根据id查询角色DO对象
     *
     * @param id 角色 id
     * @return 角色DO对象
     */
    @Override
    public RoleDO getById(Long id) {
        return roleMapper.getById(id);
    }

    /**
     * 新增角色
     *
     * @param role 角色DO对象
     */
    @Override
    public Long save(RoleDO role) {
        roleMapper.save(role);
        return role.getId();
    }

    /**
     * 更新角色
     *
     * @param role 角色DO对象
     */
    @Override
    public Boolean update(RoleDO role) {
        roleMapper.update(role);
        return true;
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    @Override
    public Boolean remove(Long id) {
        roleMapper.remove(id);
        return true;
    }

}