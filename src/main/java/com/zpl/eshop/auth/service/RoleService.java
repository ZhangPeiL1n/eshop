package com.zpl.eshop.auth.service;

import com.zpl.eshop.auth.domain.RoleDTO;
import com.zpl.eshop.auth.domain.RoleQuery;

import java.util.List;

/**
 * 角色管理模块service组件接口
 *
 * @author ZhangPeiL1n
 */
public interface RoleService {

    /**
     * 分页查询角色
     *
     * @param query 查询条件
     * @return 角色DO对象集合
     * @throws Exception
     */
    List<RoleDTO> listByPage(RoleQuery query) throws Exception;

    /**
     * 根据id查询角色DO对象
     *
     * @param id 角色 id
     * @return 角色DO对象
     * @throws Exception
     */
    RoleDTO getById(Long id) throws Exception;

    /**
     * 新增角色
     *
     * @param role 角色DO对象
     * @return 新增结果
     * @throws Exception
     */
    Boolean save(RoleDTO role) throws Exception;

    /**
     * 更新角色
     *
     * @param role 角色DO对象
     * @return 更新结果
     * @throws Exception
     */
    Boolean update(RoleDTO role) throws Exception;

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 删除结果
     * @throws Exception
     */
    Boolean remove(Long id) throws Exception;

}
