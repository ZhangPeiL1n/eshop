package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.PriorityDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限管理模块DAO接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/7 22:19
 **/
public interface PriorityDAO {

    /**
     * 查询根权限
     *
     * @return 根权限集合
     */
    List<PriorityDO> listRootPriorities();


    /**
     * 根据父权限id查询子权限
     *
     * @param parentId 父权限id
     * @return 子权限
     */
    List<PriorityDO> listChildPriorities(Long parentId);

    /**
     * 根据id查询权限
     *
     * @param id id
     * @return 权限
     */
    PriorityDO getPriorityById(@Param("id") Long id);

    /**
     * 新增权限
     *
     * @param priorityDO 权限DO对象
     * @return 新增是否成功
     */
    Long savePriority(PriorityDO priorityDO);

    /**
     * 更新权限
     *
     * @param priorityDO 权限Do对象
     * @return 更新是否成功
     */
    Boolean updatePriority(PriorityDO priorityDO);

    /**
     * 删除权限
     *
     * @param priorityId 权限id
     * @return 删除是否成功
     */
    Boolean deletePriority(Long priorityId);
}
