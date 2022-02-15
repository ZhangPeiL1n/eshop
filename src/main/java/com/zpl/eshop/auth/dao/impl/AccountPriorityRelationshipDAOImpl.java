package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.zpl.eshop.auth.domain.AccountPriorityRelationshipDO;
import com.zpl.eshop.auth.mapper.AccountPriorityRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 帐号和权限管理模块 DAO 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/10 22:07
 **/
@Repository
public class AccountPriorityRelationshipDAOImpl implements AccountPriorityRelationshipDAO {

    private final Logger logger = LoggerFactory.getLogger(AccountPriorityRelationshipDAOImpl.class);
    /**
     * 账户和权限关系管理模块 mapper
     */
    @Autowired
    private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;

    /**
     * 新增帐号权限关联关系
     *
     * @param accountPriorityRelationshipDO 帐号权限关联关系DO
     * @return 新增成功返回true
     */
    @Override
    public Boolean save(AccountPriorityRelationshipDO accountPriorityRelationshipDO) {
        try {
            accountPriorityRelationshipMapper.save(accountPriorityRelationshipDO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 根据权限 id 查询记录数
     *
     * @param priorityId 权限id
     * @return 记录数
     */
    @Override
    public Long countByPriorityId(Long priorityId) {
        try {
            return accountPriorityRelationshipMapper.countByPriorityId(priorityId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
