package com.zpl.eshop.auth.service.impl;

import com.zpl.eshop.auth.dao.PriorityDAO;
import com.zpl.eshop.auth.domain.PriorityDTO;
import com.zpl.eshop.auth.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限管理模块的service组件
 *
 * @author ZhangPeiL1n
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PriorityServiceImpl implements PriorityService {

    /**
     * 权限管理模块的DAO组件
     */
    @Autowired
    private PriorityDAO priorityDAO;

    /**
     * 查询根权限
     *
     * @return 根权限集合
     */
    @Override
    public List<PriorityDTO> listRootPriorities() {
        return null;
    }

}
