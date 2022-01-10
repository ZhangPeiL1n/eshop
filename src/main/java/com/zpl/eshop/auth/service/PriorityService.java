package com.zpl.eshop.auth.service;

import com.zpl.eshop.auth.domain.PriorityDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限管理模块的 service 组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/7 22:25
 **/
public interface PriorityService {


    /**
     * 查询根权限
     *
     * @return 根权限集合
     */
    List<PriorityDTO> listRootPriorities();

    /**
     * 根据父权限id查询子权限
     *
     * @param parentId 父权限id
     * @return 子权限
     */
    public List<PriorityDTO> listChildPriorities(Long parentId);

    /**
     * 根据id查询权限
     *
     * @param id id
     * @return 权限
     */
    PriorityDTO getPriorityById(@Param("id") Long id);

    /**
     * 新增权限
     *
     * @param priorityDTO 权限DTO对象
     * @return 新增是否成功
     */
    Boolean savePriority(PriorityDTO priorityDTO);

    /**
     * 更新权限
     *
     * @param priorityDTO 权限DTO对象
     * @return 更新是否成功
     */
    Boolean updatePriority(PriorityDTO priorityDTO);

    /**
     * 删除权限
     *
     * @param id 权限id
     * @return 删除是否成功
     */
    Boolean deletePriority(Long id);
}
