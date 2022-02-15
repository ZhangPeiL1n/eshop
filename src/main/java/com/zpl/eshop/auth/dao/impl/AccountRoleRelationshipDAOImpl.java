package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.zpl.eshop.auth.mapper.AccountRoleRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangPeiL1n
 * @date 2022/2/15 18:38
 **/
@Repository
public class AccountRoleRelationshipDAOImpl implements AccountRoleRelationshipDAO {


    @Autowired
    private AccountRoleRelationshipMapper relationshipMapper;

    private static final Logger logger = LoggerFactory.getLogger(AccountRoleRelationshipDAOImpl.class);

    /**
     * 查询角色关联的帐号记录数
     *
     * @param roleId 角色id
     * @return 记录数
     */
    @Override
    public Long countByRoleId(Long roleId) {
        try {
            return relationshipMapper.countByRoleId(roleId);
        } catch (Exception e) {
            logger.error("error", e);
            return 0L;
        }
    }
}
