package com.zpl.eshop.auth.service;

import com.zpl.eshop.auth.constant.PriorityType;
import com.zpl.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.zpl.eshop.auth.dao.PriorityDAO;
import com.zpl.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.zpl.eshop.auth.domain.PriorityDO;
import com.zpl.eshop.auth.domain.PriorityDTO;
import com.zpl.eshop.common.util.DateProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * 权限管理模块的service组件单元测测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/2 1:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PriorityServiceTest {

    /**
     * 权限管理模块Service组件
     */
    @Autowired
    private PriorityService priorityService;

    /**
     * 权限管理模块DAO组件
     */
    @MockBean
    private PriorityDAO priorityDAO;

    /**
     * 帐号权限关联关系DAO组件
     */
    @MockBean
    private AccountPriorityRelationshipDAO accountPriorityRelationshipDAO;

    /**
     * 角色权限关联关系DAO组件
     */
    @MockBean
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;

    /**
     * 日期辅助组件
     */
    @MockBean
    private DateProvider dateProvider;

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
        Long parentId = null;
        // mock dao 的行为
        List<PriorityDO> priorityDOs = createMockPriorityDOs(parentId);
        when(priorityDAO.listRootPriorities()).thenReturn(priorityDOs);

        List<PriorityDTO> resultPriorityDTOs = priorityService.listRootPriorities();
        // 比较权限集合
        comparePriorities(resultPriorityDTOs, priorityDOs);
    }

    /**
     * 测试根据父权限id查询子权限
     */
    @Test
    public void testListChildPriorities() throws Exception {
        Long parentId = 1L;
        List<PriorityDO> priorityDOs = createMockPriorityDOs(parentId);
        when(priorityDAO.listChildPriorities(parentId)).thenReturn(priorityDOs);

        List<PriorityDTO> resultPriorityDTOS = priorityService.listChildPriorities(parentId);
        // 比较权限集合
        comparePriorities(resultPriorityDTOS, priorityDOs);
    }

    /**
     * 测试根据id查询权限
     */
    @Test
    public void testGetPriorityById() throws Exception {
        Long id = 2L;
        Long parentId = 1L;
        PriorityDO mockPriorityDO = createMockPriorityDO(id, parentId);
        when(priorityDAO.getPriorityById(id)).thenReturn(mockPriorityDO);
        PriorityDTO priorityDTO = convertPriorityDO2PriorityDTO(mockPriorityDO);
        PriorityDTO resultPriorityDTO = priorityService.getPriorityById(id);
        assertEquals(priorityDTO, resultPriorityDTO);
    }

    /**
     * 测试新增权限
     */
    @Test
    public void testSavePriority() throws Exception {
        Long id = 2L;
        Long parentId = 1L;
        PriorityDO mockPriorityDO = createMockPriorityDO(id, parentId);
        PriorityDTO priorityDTO = convertPriorityDO2PriorityDTO(mockPriorityDO);
        when(priorityDAO.savePriority(mockPriorityDO)).thenReturn(id);
        priorityService.savePriority(priorityDTO);
        verify(priorityDAO, times(1)).savePriority(mockPriorityDO);
    }

    /**
     * 测试更新权限
     */
    @Test
    public void testUpdatePriority() throws Exception {
        Long id = 2L;
        Long parentId = 1L;
        PriorityDO mockPriorityDO = createMockPriorityDO(id, parentId);
        PriorityDTO priorityDTO = convertPriorityDO2PriorityDTO(mockPriorityDO);
        when(priorityDAO.updatePriority(mockPriorityDO)).thenReturn(true);
        priorityService.updatePriority(priorityDTO);
        verify(priorityDAO, times(1)).updatePriority(mockPriorityDO);
    }

    /**
     * 测试删除权限
     *
     * @throws Exception
     */
    @Test
    public void testDeletePriority() throws Exception {
        Long id = 2L;
        Long parentId = 1L;
        // 根DO
        PriorityDO mockPriorityDO = createMockPriorityDO(id, parentId);
        // mock PriorityNodeRelateCheckVisitor 过程
        when(priorityDAO.getPriorityById(id)).thenReturn(mockPriorityDO);

        Long childId = 3L;
        // 子DO
        PriorityDO childPriorityDO = createMockPriorityDO(childId, 2L);
        ArrayList<PriorityDO> childPriorityDOs = new ArrayList<>();
        childPriorityDOs.add(childPriorityDO);
        when(priorityDAO.listChildPriorities(id)).thenReturn(childPriorityDOs);
        when(priorityDAO.listChildPriorities(childId)).thenReturn(new ArrayList<>());

        // 子DO关系检查
        when(rolePriorityRelationshipDAO.countByPriorityId(childId)).thenReturn(0L);
        when(accountPriorityRelationshipDAO.countByPriorityId(childId)).thenReturn(0L);
        // 根DO关系检查
        when(rolePriorityRelationshipDAO.countByPriorityId(id)).thenReturn(0L);
        when(accountPriorityRelationshipDAO.countByPriorityId(id)).thenReturn(0L);

        // mock PriorityNodeDeleteVisitor 过程，zpl：这要不要都行
        when(priorityDAO.deletePriority(childId)).thenReturn(true);
        when(priorityDAO.deletePriority(id)).thenReturn(true);
        // 执行删除
        Boolean result = priorityService.deletePriority(id);
        // 断言所有的方法的调用次数
        // relateCheck 部分
        verify(priorityDAO, times(1)).getPriorityById(id);
        verify(priorityDAO, times(2)).listChildPriorities(id);
        verify(priorityDAO, times(2)).listChildPriorities(childId);
        verify(rolePriorityRelationshipDAO, times(1)).countByPriorityId(childId);
        verify(accountPriorityRelationshipDAO, times(1)).countByPriorityId(childId);
        verify(rolePriorityRelationshipDAO, times(1)).countByPriorityId(id);
        verify(accountPriorityRelationshipDAO, times(1)).countByPriorityId(id);

        // delete 部分
        verify(priorityDAO, times(1)).deletePriority(childId);
        verify(priorityDAO, times(1)).deletePriority(id);
        assertTrue(result);
    }

    /**
     * 构造模拟的权限集合
     *
     * @param parentId 父权限id
     * @return 模拟的权限集合
     * @throws Exception
     */
    private List<PriorityDO> createMockPriorityDOs(Long parentId) throws Exception {
        ArrayList<PriorityDO> priorityDOs = new ArrayList<>();
        priorityDOs.add(createMockPriorityDO(1L, parentId));
        priorityDOs.add(createMockPriorityDO(2L, parentId));
        return priorityDOs;
    }

    /**
     * 比较权限集合
     *
     * @param priorityDTOs 权限集合1
     * @param priorityDOs  权限集合2
     */
    private void comparePriorities(List<PriorityDTO> priorityDTOs, List<PriorityDO> priorityDOs) {
        assertEquals(priorityDOs.size(), priorityDTOs.size());
        Map<Long, PriorityDTO> priorityDTOMap = convertPriorityDOs2Map(priorityDOs);
        for (PriorityDTO priorityDTO : priorityDTOs) {
            assertEquals(priorityDTOMap.get(priorityDTO.getId()), priorityDTO);
        }
    }

    /**
     * 将权限 DO集合 转换为 权限DTO map
     *
     * @param priorityDOs 权限 DO 集合
     * @return 权限 DTO map
     */
    private Map<Long, PriorityDTO> convertPriorityDOs2Map(List<PriorityDO> priorityDOs) {
        Map<Long, PriorityDTO> priorityDTOMap = new HashMap<>();
        for (PriorityDO priorityDO : priorityDOs) {
            priorityDTOMap.put(priorityDO.getId(), convertPriorityDO2PriorityDTO(priorityDO));
        }
        return priorityDTOMap;
    }

    /**
     * 构造根权限DO对象
     *
     * @return 权限DO对象
     */
    private PriorityDO createMockPriorityDO(Long id, Long parentId) throws Exception {
        Random random = new Random();
        int randomInt = random.nextInt() * 100;
        PriorityDO priorityDO = new PriorityDO();
        priorityDO.setId(id);
        priorityDO.setCode("TEST_" + randomInt);
        priorityDO.setParentId(parentId);
        priorityDO.setPriorityType(PriorityType.MENU);
        priorityDO.setUrl(null);
        priorityDO.setPriorityComment("TEST_" + randomInt);
        priorityDO.setGmtCreate(dateProvider.getCurrentTime());
        priorityDO.setGmtModified(dateProvider.getCurrentTime());
        return priorityDO;
    }

    /**
     * 权限DO对象转换为权限DTO对象
     *
     * @param priorityDO 权限DO对象
     * @return 权限DTO对象
     */
    private PriorityDTO convertPriorityDO2PriorityDTO(PriorityDO priorityDO) {
        PriorityDTO priorityDTO = new PriorityDTO();
        priorityDTO.setId(priorityDO.getId());
        priorityDTO.setCode(priorityDO.getCode());
        priorityDTO.setParentId(priorityDO.getParentId());
        priorityDTO.setUrl(priorityDO.getUrl());
        priorityDTO.setPriorityType(priorityDO.getPriorityType());
        priorityDTO.setPriorityComment(priorityDO.getPriorityComment());
        priorityDTO.setGmtCreate(priorityDO.getGmtCreate());
        priorityDTO.setGmtModified(priorityDO.getGmtModified());
        return priorityDTO;
    }
}
