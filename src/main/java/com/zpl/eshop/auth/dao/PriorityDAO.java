package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.PriorityDO;

import java.util.List;

/**
 * 权限管理模块的DAO组件接口
 *
 * @author ZhangPeiL1n
 */
public interface PriorityDAO {

	/**
     * 查询根权限
     *
     * @return 根权限集合
     * @throws Exception
     */
    List<PriorityDO> listRootPriorities() throws Exception;
}
