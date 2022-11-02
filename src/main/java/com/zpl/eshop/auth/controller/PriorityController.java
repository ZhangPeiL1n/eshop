package com.zpl.eshop.auth.controller;

import com.zpl.eshop.auth.domain.PriorityDTO;
import com.zpl.eshop.auth.domain.PriorityVO;
import com.zpl.eshop.auth.service.PriorityService;
import com.zpl.eshop.auth.service.impl.Priority;
import com.zpl.eshop.common.util.DateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理模块的controller组件
 *
 * @author ZhangPeiL1n
 */
@RestController
@RequestMapping("/auth/priority")
public class PriorityController {

    private static final Logger logger = LoggerFactory.getLogger(PriorityController.class);

    /**
     * 权限管理模块的service组件
     */
    @Autowired
    private PriorityService priorityService;

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
    @GetMapping("/root")
    public List<PriorityVO> listRootPriorities() {
        try {
            List<PriorityDTO> priorityDTOList = priorityService.listRootPriorities();
            if (priorityDTOList == null) {
                priorityDTOList = new ArrayList<>();
            }

            List<PriorityVO> priorityVOList = new ArrayList<>(priorityDTOList.size());
            for (PriorityDTO priorityDTO : priorityDTOList) {
                priorityVOList.add(convertPriorityDto2vo(priorityDTO));
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
    public List<PriorityVO> listChildPriorities(
            @PathVariable("parentId") Long parentId) {
        try {
            List<PriorityDTO> priorityDTOList = priorityService.listChildPriorities(parentId);
            if (priorityDTOList == null) {
                priorityDTOList = new ArrayList<>();
            }

            List<PriorityVO> priorityVOList = new ArrayList<>(priorityDTOList.size());
            for (PriorityDTO priorityDTO : priorityDTOList) {
                priorityVOList.add(convertPriorityDto2vo(priorityDTO));
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
     * @param id 权限id
     * @return 权限
     */
    @GetMapping("/{id}")
    public PriorityVO getPriorityById(@PathVariable("id") Long id) {
        try {
            PriorityDTO priorityDTO = priorityService.getPriorityById(id);
            if (priorityDTO == null) {
                priorityDTO = new PriorityDTO();
            }

            return convertPriorityDto2vo(priorityDTO);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new PriorityVO();
    }

    /**
     * 查询账号被授权的权限树
     *
     * @param accountId 账号id
     * @return 权限树
     */
    @GetMapping("/authorized/tree/{accountId}")
    public List<Priority> listAuthorizedTree(
            @PathVariable("accountId") Long accountId) {
        try {
            return priorityService.listAuthorizedByAccountId(accountId);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 判断账号对指定编号的权限是否有授权记录
     *
     * @param accountId 账号id
     */
    @GetMapping("/authorized/code/{accountId}")
    public Boolean existAuthorizedByCode(@PathVariable("accountId") Long accountId, String code) {
        try {
            return priorityService.existAuthorizedByCode(accountId, code);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 判断账号对指定url的权限是否有授权记录
     *
     * @param accountId 账号id
     */
    @GetMapping("/authorized/url/{accountId}")
    public Boolean existAuthorizedByUrl(@PathVariable("accountId") Long accountId, String url) {
        try {
            return priorityService.existAuthorizedByUrl(accountId, url);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 新增权限
     *
     * @param priorityVO 权限VO对象
     */
    @PostMapping("/")
    public Boolean savePriority(@RequestBody PriorityVO priorityVO) {
        try {
            priorityService.savePriority(convertPriorityVo2Dto(priorityVO));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 更新权限
     *
     * @param priorityVO 权限VO对象
     */
    @PutMapping("/{id}")
    public Boolean updatePriority(@RequestBody PriorityVO priorityVO) {
        try {
            priorityService.updatePriority(convertPriorityVo2Dto(priorityVO));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 删除权限
     */
    @DeleteMapping("/{id}")
    public Boolean removePriority(@PathVariable("id") Long id) {
        try {
            return priorityService.removePriority(id);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * 将权限DTO对象转换为VO对象
     *
     * @param priorityDTO
     * @return
     * @throws Exception
     */
    private PriorityVO convertPriorityDto2vo(PriorityDTO priorityDTO) throws Exception {
        PriorityVO priorityVO = priorityDTO.clone(PriorityVO.class);
        priorityVO.setGmtCreate(dateProvider.formatDateTime(priorityDTO.getGmtCreate()));
        priorityVO.setGmtModified(dateProvider.formatDateTime(priorityDTO.getGmtModified()));
        return priorityVO;
    }

    /**
     * 将权限VO对象转换为DTO对象
     *
     * @param priorityVO
     * @return
     * @throws Exception
     */
    private PriorityDTO convertPriorityVo2Dto(PriorityVO priorityVO) throws Exception {
        PriorityDTO priorityDTO = priorityVO.clone(PriorityDTO.class);
        if (priorityVO.getGmtCreate() != null) {
            priorityDTO.setGmtCreate(dateProvider.parse2Datetime(priorityVO.getGmtCreate()));
        }
        if (priorityVO.getGmtModified() != null) {
            priorityDTO.setGmtModified(dateProvider.parse2Datetime(priorityVO.getGmtModified()));
        }
        return priorityDTO;
    }

}
