package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.RoleDO;
import com.zpl.eshop.auth.domain.RoleQuery;

import java.util.List;

/**
 * 角色管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 */
public interface RoleDAO {

    /**
     * 分页查询角色
     *
     * @param query 查询条件
     * @return 角色DO对象集合
     * @throws Exception
     */
    List<RoleDO> listByPage(RoleQuery query) throws Exception;

    /**
     * 根据id查询角色DO对象
     *
     * @param id 角色 id
     * @return 角色DO对象
     * @throws Exception
     */
    RoleDO getById(Long id) throws Exception;

    /**
     * 新增角色
     *
     * @param role 角色DO对象
     * @return id
     * @throws Exception
     */
    Long save(RoleDO role) throws Exception;

    /**
     * 更新角色
     *
     * @param role 角色DO对象
     * @throws Exception
     */
    void update(RoleDO role) throws Exception;

    /**
     * 删除角色
     *
     * @param id 角色id
     * @throws Exception
     */
    void remove(Long id) throws Exception;
}
