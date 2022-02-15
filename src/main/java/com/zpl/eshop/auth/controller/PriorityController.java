package com.zpl.eshop.auth.controller;

import com.zpl.eshop.auth.domain.PriorityDTO;
import com.zpl.eshop.auth.domain.PriorityVO;
import com.zpl.eshop.auth.service.PriorityService;
import com.zpl.eshop.common.util.DateProvider;
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

    @Autowired
    private DateProvider dateProvider;

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
                PriorityVO priorityVO = priorityDTO.clone(PriorityVO.class);
                priorityVO.setGmtCreate(dateProvider.formatDateTime(priorityDTO.getGmtCreate()));
                priorityVO.setGmtModified(dateProvider.formatDateTime(priorityDTO.getGmtModified()));
                priorityVOList.add(priorityVO);
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
                PriorityVO priorityVO = convertPriorityDTO2VO(priorityDTO);
                priorityVOList.add(priorityVO);
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
            return convertPriorityDTO2VO(priorityDTO);
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
            return priorityService.savePriority(convertPriorityVO2DTO(priorityVO));
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
            return priorityService.updatePriority(convertPriorityVO2DTO(priorityVO));
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

    /**
     * 将 PriorityDTO 转换为 PriorityVO
     *
     * @param priorityDTO priorityDTO
     * @return priorityVO
     */
    private PriorityVO convertPriorityDTO2VO(PriorityDTO priorityDTO) throws Exception {
        PriorityVO priorityVO = priorityDTO.clone(PriorityVO.class);
        priorityVO.setGmtCreate(dateProvider.formatDateTime(priorityDTO.getGmtCreate()));
        priorityVO.setGmtModified(dateProvider.formatDateTime(priorityDTO.getGmtModified()));
        return priorityVO;
    }

    /**
     * 将 PriorityVO 转换为 PriorityDTO
     *
     * @param priorityVO priorityVO
     * @return priorityDTO
     */
    private PriorityDTO convertPriorityVO2DTO(PriorityVO priorityVO) throws Exception {
        return priorityVO.clone(PriorityDTO.class);
    }
}
