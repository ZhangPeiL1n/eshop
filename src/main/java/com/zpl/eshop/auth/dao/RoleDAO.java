package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.RoleDO;
import com.zpl.eshop.auth.domain.RoleQuery;

import java.util.List;

/**
 * 角色管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 16:39
 **/
public interface RoleDAO {

    /**
     * 分页查询角色
     *
     * @param query 查询条件
     * @return 角色DO集合
     */
    List<RoleDO> listByPage(RoleQuery query);

    /**
     * 根据id查找角色
     *
     * @param id 角色id
     * @return 角色DO对象
     */
    RoleDO getById(Long id);

    /**
     * 新增角色
     *
     * @param roleDO 角色DO
     */
    Boolean save(RoleDO roleDO);

    /**
     * 更新角色
     *
     * @param roleDO 角色DO
     */
    Boolean update(RoleDO roleDO);

    /**
     * 根据id删除
     *
     * @param id id
     */
    Boolean remove(Long id);
}
