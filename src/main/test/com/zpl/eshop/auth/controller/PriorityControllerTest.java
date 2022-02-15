package com.zpl.eshop.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.auth.constant.PriorityType;
import com.zpl.eshop.auth.domain.PriorityDTO;
import com.zpl.eshop.auth.domain.PriorityVO;
import com.zpl.eshop.auth.service.PriorityService;
import com.zpl.eshop.common.util.DateProvider;
import net.minidev.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * 权限管理模块Controller 单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/2 11:38
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(PriorityController.class)
public class PriorityControllerTest {

    /**
     * mvc 测试模拟类
     */
    @Autowired
    private MockMvc mvc;

    /**
     * 日期辅助组件
     */
    @MockBean
    private DateProvider dateProvider;

    /**
     * 权限管理模块Service组件
     */
    @MockBean
    private PriorityService priorityService;


    @Before
    public void setup() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        when(dateProvider.getCurrentTime()).thenReturn(currentTime);
        when(dateProvider.formatDateTime(currentTime)).thenReturn(simpleDateFormat.format(currentTime));
        when(dateProvider.parse2Datetime(simpleDateFormat.format(currentTime))).thenReturn(currentTime);
    }

    /**
     * 测试查询根权限
     */
    @Test
    public void testListRootPriorities() throws Exception {
        Long parentId = 1L;
        List<PriorityDTO> priorityDTOs = createMockPriorityDTOs(parentId);
        when(priorityService.listRootPriorities()).thenReturn(priorityDTOs);
        List<PriorityVO> priorityVOs = convertPriorityDTOs(priorityDTOs);

        mvc.perform(MockMvcRequestBuilders.get("/auth/priority/root")).andExpect(MockMvcResultMatchers.content().json(JSONArray.toJSONString(priorityVOs)));
    }

    /**
     * 测试根据父权限id查询子权限
     *
     * @throws Exception
     */
    @Test
    public void testListChildPriorities() throws Exception {
        Long parentId = 1L;
        List<PriorityDTO> mockPriorityDTOs = createMockPriorityDTOs(parentId);
        when(priorityService.listChildPriorities(parentId)).thenReturn(mockPriorityDTOs);
        List<PriorityVO> priorityVOs = convertPriorityDTOs(mockPriorityDTOs);
        mvc.perform(MockMvcRequestBuilders.get("/auth/priority/child/{parentId}", parentId)).andExpect(MockMvcResultMatchers.content().json(JSONArray.toJSONString(priorityVOs)));
    }

    /**
     * 根据id查询权限
     *
     * @throws Exception
     */
    @Test
    public void testGetPriorityById() throws Exception {
        Long id = 2L;
        Long parentId = 1L;
        PriorityDTO mockPriorityDTO = createMockPriorityDTO(id, parentId);
        when(priorityService.getPriorityById(id)).thenReturn(mockPriorityDTO);
        mvc.perform(MockMvcRequestBuilders.get("/auth/priority/{id}", id)).
                andExpect(MockMvcResultMatchers.content().json(JSONObject.toJSONString(convertPriorityDTO2VO(mockPriorityDTO))));
    }

    /**
     * 测试新增权限
     */
    @Test
    public void testSavePriority() throws Exception {
        Long id = 2L;
        Long parentId = 1L;
        PriorityDTO mockPriorityDTO = createMockPriorityDTO(id, parentId);
        PriorityVO priorityVO = convertPriorityDTO2VO(mockPriorityDTO);
        when(priorityService.savePriority(mockPriorityDTO)).thenReturn(true);
        mvc.perform(MockMvcRequestBuilders.post("/auth/priority/")
                        .contentType("application/json")
                        .content(JSONObject.toJSONString(priorityVO)))
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    /**
     * 测试更新权限
     *
     * @throws Exception
     */
    @Test
    public void testUpdatePriority() throws Exception {
        Long id = 2L;
        Long parentId = 1L;
        PriorityDTO mockPriorityDTO = createMockPriorityDTO(id, parentId);
        PriorityVO priorityVO = convertPriorityDTO2VO(mockPriorityDTO);
        when(priorityService.updatePriority(mockPriorityDTO)).thenReturn(true);
        mvc.perform(put("/auth/priority/{id}", id)
                        .contentType("application/json")
                        .content(JSONObject.toJSONString(priorityVO)))
                .andExpect(content().string("true"));
    }

    /**
     * 测试删除权限
     *
     * @throws Exception
     */
    @Test
    public void testDeletePriority() throws Exception {
        Long id = 2L;
        when(priorityService.deletePriority(id)).thenReturn(true);
        mvc.perform(delete("/auth/priority/{id}", id)).andExpect(content().string("true"));
    }

    /**
     * 构造根权限DTO对象
     *
     * @return 权限DTO对象
     */
    private PriorityDTO createMockPriorityDTO(Long id, Long parentId) throws Exception {
        Random random = new Random();
        int randomInt = random.nextInt() * 100;
        PriorityDTO priorityDTO = new PriorityDTO();
        priorityDTO.setId(id);
        priorityDTO.setCode("TEST_" + randomInt);
        priorityDTO.setParentId(parentId);
        priorityDTO.setPriorityType(PriorityType.MENU);
        priorityDTO.setUrl(null);
        priorityDTO.setPriorityComment("TEST_" + randomInt);
        priorityDTO.setGmtCreate(dateProvider.getCurrentTime());
        priorityDTO.setGmtModified(dateProvider.getCurrentTime());
        return priorityDTO;
    }

    /**
     * 构造模拟的权限集合
     *
     * @param parentId 父权限id
     * @return 模拟的权限集合
     * @throws Exception
     */
    private List<PriorityDTO> createMockPriorityDTOs(Long parentId) throws Exception {
        ArrayList<PriorityDTO> priorityDTOs = new ArrayList<>();
        priorityDTOs.add(createMockPriorityDTO(1L, parentId));
        priorityDTOs.add(createMockPriorityDTO(2L, parentId));
        return priorityDTOs;
    }

    /**
     * 权限DTO对象转换为权限VO对象
     *
     * @param priorityDTO 权限DTO对象
     * @return 权限VO对象
     */
    private PriorityVO convertPriorityDTO2VO(PriorityDTO priorityDTO) throws Exception {
        PriorityVO priorityVO = new PriorityVO();
        priorityVO.setId(priorityDTO.getId());
        priorityVO.setCode(priorityDTO.getCode());
        priorityVO.setParentId(priorityDTO.getParentId());
        priorityVO.setUrl(priorityDTO.getUrl());
        priorityVO.setPriorityType(priorityDTO.getPriorityType());
        priorityVO.setPriorityComment(priorityDTO.getPriorityComment());
        priorityVO.setGmtCreate(dateProvider.formatDateTime(priorityDTO.getGmtCreate()));
        priorityVO.setGmtModified(dateProvider.formatDateTime(priorityDTO.getGmtCreate()));
        return priorityVO;
    }

    /**
     * 将权限 DO集合 转换为 权限DTO map
     *
     * @param priorityDTOs 权限 DTO 集合
     * @return 权限 DTO map
     */
    private Map<Long, PriorityVO> convertPriorityDTOs2Map(List<PriorityDTO> priorityDTOs) throws Exception {
        Map<Long, PriorityVO> priorityDTOMap = new HashMap<>();
        for (PriorityDTO priorityDTO : priorityDTOs) {
            priorityDTOMap.put(priorityDTO.getId(), convertPriorityDTO2VO(priorityDTO));
        }
        return priorityDTOMap;
    }

    /**
     * 将权限 DO集合 转换为 权限DTO map
     *
     * @param priorityDTOs 权限 DTO 集合
     * @return 权限 DTO map
     */
    private List<PriorityVO> convertPriorityDTOs(List<PriorityDTO> priorityDTOs) throws Exception {
        ArrayList<PriorityVO> priorityVOs = new ArrayList<>(priorityDTOs.size());
        for (PriorityDTO priorityDTO : priorityDTOs) {
            priorityVOs.add(convertPriorityDTO2VO(priorityDTO));
        }
        return priorityVOs;
    }
}
