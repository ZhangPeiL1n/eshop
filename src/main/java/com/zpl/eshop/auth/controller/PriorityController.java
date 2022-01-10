package com.zpl.eshop.auth.controller;

import com.zpl.eshop.auth.domain.PriorityDTO;
import com.zpl.eshop.auth.domain.PriorityVO;
import com.zpl.eshop.auth.service.PriorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理模块的 controller 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/7 22:33
 **/
@RestController
@RequestMapping("/auth/priority")
public class PriorityController {
    private static final Logger logger = LoggerFactory.getLogger(PriorityController.class);

    /**
     * 权限管理模块的 service 组件
     */
    @Autowired
    private PriorityService priorityService;

    /**
     * 查询根权限
     *
     * @return 根权限集合
     */
    @GetMapping("/root")
    public List<PriorityVO> listRootPriorities() {
        try {
            List<PriorityDTO> priorityDTOList = priorityService.listRootPriorities();
            if (priorityDTOList == null) {
                priorityDTOList = new ArrayList<>();
            }
            List<PriorityVO> priorityVOList = new ArrayList<>(priorityDTOList.size());
            for (PriorityDTO priorityDTO : priorityDTOList) {
                priorityVOList.add(priorityDTO.clone(PriorityVO.class));
            }
            return priorityVOList;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new ArrayList<>();
    }


    /**
     * 根据父权限id查询子权限
     *
     * @param parentId 父权限id
     * @return 子权限
     */
    @GetMapping("/child/{parentId}")
    public List<PriorityVO> listChildPriorities(@PathVariable("parentId") Long parentId) {
        try {
            List<PriorityDTO> priorityDTOList = priorityService.listChildPriorities(parentId);
            List<PriorityVO> priorityVOList = new ArrayList<>(priorityDTOList.size());
            for (PriorityDTO priorityDTO : priorityDTOList) {
                priorityVOList.add(priorityDTO.clone(PriorityVO.class));
            }
            return priorityVOList;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new ArrayList<>();
    }

    /**
     * 根据id查询权限
     *
     * @param id id
     * @return 权限
     */
    @GetMapping("/{id}")
    PriorityVO getPriorityById(@PathVariable("id") Long id) {
        try {
            PriorityDTO priorityDTO = priorityService.getPriorityById(id);
            if (priorityDTO == null) {
                priorityDTO = new PriorityDTO();
            }
            return priorityDTO.clone(PriorityVO.class);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new PriorityVO();
    }

    /**
     * 新增权限
     *
     * @param priorityVO 权限VO对象
     * @return 新增是否成功
     */
    @PostMapping("/")
    public Boolean savePriority(@RequestBody PriorityVO priorityVO) {
        try {
            return priorityService.savePriority(priorityVO.clone(PriorityDTO.class));
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * 更新权限
     *
     * @param priorityVO 权限VO对象
     * @return 更新是否成功
     */
    @PutMapping("/{id}")
    Boolean updatePriority(@RequestBody PriorityVO priorityVO) {
        try {
            return priorityService.updatePriority(priorityVO.clone(PriorityDTO.class));
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * 删除权限
     *
     * @param id 权限 id
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    Boolean deletePriority(@PathVariable("id") Long id) {
        try {
            return priorityService.deletePriority(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
