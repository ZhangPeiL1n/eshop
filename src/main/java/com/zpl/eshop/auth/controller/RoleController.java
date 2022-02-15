package com.zpl.eshop.auth.controller;

import com.zpl.eshop.auth.domain.*;
import com.zpl.eshop.auth.service.RoleService;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理模块Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 18:45
 **/
@RestController
@RequestMapping("/auth/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * 角色管理模块Service组件
     */
    @Autowired
    private RoleService roleService;


    /**
     * 分页查询角色
     *
     * @param query 查询条件
     * @return 角色VO集合
     */
    @GetMapping("/")
    public List<RoleVO> listByPage(RoleQuery query) {
        try {
            List<RoleDTO> roleDTOList = roleService.listByPage(query);

            return ObjectUtils.convertList(roleDTOList, RoleVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RoleVO getById(@PathVariable("id") Long id) {
        try {
            RoleDTO roleDTO = roleService.getById(id);
            RoleVO roleVO = roleDTO.clone(RoleVO.class);
            roleVO.setRolePriorityRelations(ObjectUtils.convertList(roleDTO.getRolePriorityRelations(), RolePriorityRelationshipVO.class));
            return roleVO;
        } catch (Exception e) {
            logger.error("error", e);
            return new RoleVO();
        }
    }

    /**
     * 新增角色
     *
     * @param roleVO 新增角色
     * @return true 新增成功
     */
    @PostMapping("/")
    public Boolean save(@RequestBody RoleVO roleVO) {
        try {
            RoleDTO roleDTO = roleVO.clone(RoleDTO.class);
            roleDTO.setRolePriorityRelations(ObjectUtils.convertList(roleVO.getRolePriorityRelations(), RolePriorityRelationshipDTO.class));
            roleService.save(roleDTO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新角色
     *
     * @param roleVO 角色VO对象
     * @return 新增成功返回true
     */
    @PutMapping("/")
    public Boolean update(@RequestBody RoleVO roleVO) {
        try {
            RoleDTO roleDTO = roleVO.clone(RoleDTO.class);
            roleDTO.setRolePriorityRelations(ObjectUtils.convertList(roleVO.getRolePriorityRelations(), RolePriorityRelationshipDTO.class));
            roleService.update(roleDTO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 根据角色id删除
     *
     * @param id 角色id
     * @return 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            roleService.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
