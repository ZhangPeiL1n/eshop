package com.zpl.eshop.auth.service.impl;

import com.zpl.eshop.auth.composite.PriorityNode;
import com.zpl.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.zpl.eshop.auth.dao.PriorityDAO;
import com.zpl.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.zpl.eshop.auth.domain.PriorityDO;
import com.zpl.eshop.auth.domain.PriorityDTO;
import com.zpl.eshop.auth.service.PriorityService;
import com.zpl.eshop.auth.visitor.PriorityNodeDeleteVisitor;
import com.zpl.eshop.auth.visitor.PriorityNodeRelateCheckVisitor;
import com.zpl.eshop.common.util.DateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理模块的service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/7 22:26
 **/
@Service
public class PriorityServiceImpl implements PriorityService {

    private static final Logger logger = LoggerFactory.getLogger(PriorityServiceImpl.class);

    /**
     * 权限管理模块的DAO组件
     */
    @Autowired
    private PriorityDAO priorityDAO;

    /**
     * 角色和权限管理模块的DAO组件
     */
    @Autowired
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;

    /**
     * 帐号和权限管理的DAO组件
     */
    @Autowired
    private AccountPriorityRelationshipDAO accountPriorityRelationshipDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 查询根权限
     *
     * @return 根权限集合
     */
    @Override
    public List<PriorityDTO> listRootPriorities() {
        try {
            List<PriorityDO> priorityDOList = priorityDAO.listRootPriorities();
            if (priorityDOList == null) {
                return null;
            }
            ArrayList<PriorityDTO> priorityDTOList = new ArrayList<>();
            for (PriorityDO priorityDO : priorityDOList) {
                priorityDTOList.add(priorityDO.clone(PriorityDTO.class));
            }
            return priorityDTOList;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 根据父权限id查询子权限
     *
     * @param parentId 父权限id
     * @return 子权限
     */
    @Override
    public List<PriorityDTO> listChildPriorities(Long parentId) {
        try {
            List<PriorityDO> priorityDOList = priorityDAO.listChildPriorities(parentId);
            if (priorityDOList == null) {
                return null;
            }
            ArrayList<PriorityDTO> priorityDTOList = new ArrayList<>();
            for (PriorityDO priorityDO : priorityDOList) {
                priorityDTOList.add(priorityDO.clone(PriorityDTO.class));
            }
            return priorityDTOList;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 根据id查询权限
     *
     * @param id id
     * @return 权限
     */
    @Override
    public PriorityDTO getPriorityById(Long id) {
        try {
            PriorityDO priorityDO = priorityDAO.getPriorityById(id);
            if (priorityDO == null) {
                return null;
            }
            return priorityDO.clone(PriorityDTO.class);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 新增权限
     *
     * @param priorityDTO 权限DTO对象
     */
    @Override
    public Boolean savePriority(PriorityDTO priorityDTO) {
        try {
            priorityDTO.setGmtCreate(dateProvider.getCurrentTime());
            priorityDTO.setGmtModified(dateProvider.getCurrentTime());
            priorityDAO.savePriority(priorityDTO.clone(PriorityDO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * 更新权限
     *
     * @param priorityDTO 权限DTO对象
     * @return 更新是否成功
     */
    @Override
    public Boolean updatePriority(PriorityDTO priorityDTO) {
        try {
            priorityDTO.setGmtModified(dateProvider.getCurrentTime());
            priorityDAO.updatePriority(priorityDTO.clone(PriorityDO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * 删除权限
     *
     * @param id 权限id
     * @return 删除是否成功
     */
    @Override
    public Boolean deletePriority(Long id) {
        try {
            // 根据 id 查询权限
            // zpl：这个 id 不是已经是权限的 id 了，直接 new 一个 DO 或者 Node 应该就行了
            PriorityDO priorityDO = priorityDAO.getPriorityById(id);
            PriorityNode node = priorityDO.clone(PriorityNode.class);

            // 检查这个权限及其子权限是否被角色或者帐号关联
            PriorityNodeRelateCheckVisitor priorityNodeRelateCheckVisitor = new PriorityNodeRelateCheckVisitor(priorityDAO, rolePriorityRelationshipDAO, accountPriorityRelationshipDAO);
            priorityNodeRelateCheckVisitor.visit(node);
            Boolean relateCheckResult = priorityNodeRelateCheckVisitor.getRelateCheckResult();
            // zpl：这是一个卫语句
            if (relateCheckResult) {
                return false;
            }
            // 递归删除当前权限及其下子权限
            PriorityNodeDeleteVisitor priorityNodeDeleteVisitor = new PriorityNodeDeleteVisitor(priorityDAO);
            priorityNodeDeleteVisitor.visit(node);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }
}
