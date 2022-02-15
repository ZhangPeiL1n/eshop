package com.zpl.eshop.auth.service;

import com.zpl.eshop.auth.domain.RoleDTO;
import com.zpl.eshop.auth.domain.RoleQuery;

import java.util.List;

/**
 * 角色管理模块Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 16:57
 **/
public interface RoleService {

    /**
     * 分页查询角色
     *
     * @param query 查询条件
     * @return 角色DTO集合
     */
    List<RoleDTO> listByPage(RoleQuery query);

    /**
     * 根据id查找角色
     *
     * @param id 角色id
     * @return 角色DTO对象
     */
    RoleDTO getById(Long id);

    /**
     * 新增角色
     *
     * @param roleDTO 角色DTO
     */
    Boolean save(RoleDTO roleDTO);

    /**
     * 更新角色
     *
     * @param roleDTO 角色DTO
     */
    Boolean update(RoleDTO roleDTO);

    /**
     * 根据id删除
     *
     * @param id id
     */
    Boolean remove(Long id);
}
