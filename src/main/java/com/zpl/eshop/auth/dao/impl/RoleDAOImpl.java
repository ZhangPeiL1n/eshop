package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.RoleDAO;
import com.zpl.eshop.auth.domain.RoleDO;
import com.zpl.eshop.auth.domain.RoleQuery;
import com.zpl.eshop.auth.mapper.RoleMapper;
import com.zpl.eshop.common.util.DateProvider;
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
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询角色
     *
     * @param query 查询条件
     * @return 角色DO对象集合
     * @throws Exception
     */
    @Override
    public List<RoleDO> listByPage(RoleQuery query) throws Exception {
        return roleMapper.listByPage(query);
    }

    /**
     * 根据id查询角色DO对象
     *
     * @param id 角色 id
     * @return 角色DO对象
     * @throws Exception
     */
    @Override
    public RoleDO getById(Long id) throws Exception {
        return roleMapper.getById(id);
    }

    /**
     * 新增角色
     *
     * @param role 角色DO对象
     * @throws Exception
     */
    @Override
    public Long save(RoleDO role) throws Exception {
        role.setGmtCreate(dateProvider.getCurrentTime());
        role.setGmtModified(dateProvider.getCurrentTime());
        roleMapper.save(role);
        return role.getId();
    }

    /**
     * 更新角色
     *
     * @param role 角色DO对象
     * @throws Exception
     */
    @Override
    public void update(RoleDO role) throws Exception {
        role.setGmtModified(dateProvider.getCurrentTime());
        roleMapper.update(role);
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @throws Exception
     */
    @Override
    public void remove(Long id) throws Exception {
        roleMapper.remove(id);
    }

}
