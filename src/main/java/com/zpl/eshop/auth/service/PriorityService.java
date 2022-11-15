package com.zpl.eshop.auth.service;

import com.zpl.eshop.auth.domain.PriorityDTO;

import java.util.List;

/**
 * 权限管理模块的service组件接口
 *
 * @author ZhangPeiL1n
 */
public interface PriorityService {

	/**
     * 查询根权限
     *
     * @return 根权限集合
     * @throws Exception
     */
    List<PriorityDTO> listRootPriorities() throws Exception;

}
