package com.zpl.eshop.auth.dao.impl;

import com.zpl.eshop.auth.dao.RoleDAO;
import com.zpl.eshop.auth.domain.RoleDO;
import com.zpl.eshop.auth.domain.RoleQuery;
import com.zpl.eshop.auth.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/2/15 16:41
 **/
@Repository
public class RoleDAOImpl implements RoleDAO {

    /**
     * 角色管理模块Mapper组件
     */
    @Autowired
    private RoleMapper roleMapper;

    private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

    /**
     * 分页查询角色
     *
     * @param query 查询条件
     * @return 角色DO集合
     */
    @Override
    public List<RoleDO> listByPage(RoleQuery query) {
        try {
            return roleMapper.listByPage(query);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 根据id查找角色
     *
     * @param id 角色id
     * @return 角色DO对象
     */
    @Override
    public RoleDO getById(Long id) {
        try {
            return roleMapper.getById(id);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 新增角色
     *
     * @param roleDO 角色DO
     */
    @Override
    public Long save(RoleDO roleDO) {
        try {
            roleMapper.save(roleDO);
            return roleDO.getId();
        } catch (Exception e) {
            logger.error("error", e);
            return 0L;
        }
    }

    /**
     * 更新角色
     *
     * @param roleDO 角色DO
     */
    @Override
    public Boolean update(RoleDO roleDO) {
        try {
            roleMapper.update(roleDO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 根据id删除
     *
     * @param id id
     */
    @Override
    public Boolean remove(Long id) {
        try {
            roleMapper.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
