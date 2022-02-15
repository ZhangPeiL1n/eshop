package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.constant.PriorityType;
import com.zpl.eshop.auth.domain.PriorityDO;
import com.zpl.eshop.common.util.DateProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * 权限管理模块的DAO组件的单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/1 12:36
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class PriorityDAOTest {

    /**
     * 权限管理模块的DAO组件
     */
    @Autowired
    private PriorityDAO priorityDAO;


    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增权限
     */
    @Test
    public void testSavePriority() throws Exception {
        Long parentId = 1L;
        PriorityDO priorityDO = createPriorityDO(parentId);
        assertNotNull(priorityDO.getId());
        assertThat(parentId, greaterThan(0L));
    }

    /**
     * 测试查询根权限
     */
    @Test
    public void testListRootPriorities() throws Exception {
        // 构造两个根权限
        Map<Long, PriorityDO> priorityDOMap = createPriorityDOMap(null);
        // 查询根权限集合
        List<PriorityDO> priorityDOs = priorityDAO.listRootPriorities();
        // 执行断言
        comparePriorityDOs(priorityDOs, priorityDOMap);
    }

    /**
     * 测试根据父权限id查询子权限
     */
    @Test
    public void testListChildPriorities() throws Exception {
        Long parentId = 1L;
        Map<Long, PriorityDO> childrenPriorityDOMap = createPriorityDOMap(parentId);
        List<PriorityDO> priorityDOs = priorityDAO.listChildPriorities(parentId);
        assertNotNull(priorityDOs);
        comparePriorityDOs(priorityDOs, childrenPriorityDOMap);
    }

    /**
     * 测试根据id查询权限
     *
     * @throws Exception
     */
    @Test
    public void testGetPriorityById() throws Exception {
        PriorityDO priorityDO = createPriorityDO(null);
        priorityDAO.savePriority(priorityDO);
        PriorityDO targetPriorityDO = priorityDAO.getPriorityById(priorityDO.getId());
        assertNotNull(targetPriorityDO);
        assertEquals(priorityDO, targetPriorityDO);
    }

    /**
     * 测试更新权限
     *
     * @throws Exception
     */
    @Test
    public void testUpdatePriority() throws Exception {
        PriorityDO priorityDO = createPriorityDO(null);
        priorityDO.setCode(priorityDO.getCode() + "_modified");
        priorityDO.setGmtModified(dateProvider.getCurrentTime());
        priorityDO.setPriorityComment(priorityDO.getPriorityComment() + "_modified");
        priorityDO.setUrl(priorityDO.getUrl() + "_modified");
        priorityDAO.updatePriority(priorityDO);
        PriorityDO targetPriority = priorityDAO.getPriorityById(priorityDO.getId());
        assertNotNull(targetPriority);
        assertEquals(priorityDO, targetPriority);
    }

    /**
     * 测试删除权限
     */
    @Test
    public void testDeletePriority() throws Exception {
        PriorityDO priorityDO = createPriorityDO(null);
        priorityDAO.deletePriority(priorityDO.getId());
        PriorityDO targetPriority = priorityDAO.getPriorityById(priorityDO.getId());
        assertNull(targetPriority);
    }

    /**
     * 创建权限map
     *
     * @param parentId 父权限id
     * @return 权限map
     * @throws Exception
     */
    private Map<Long, PriorityDO> createPriorityDOMap(Long parentId) throws Exception {
        PriorityDO childPriorityDO1 = createPriorityDO(parentId);
        PriorityDO childPriorityDO2 = createPriorityDO(parentId);
        Map<Long, PriorityDO> priorityDOMap = new HashMap<>();
        priorityDOMap.put(childPriorityDO1.getId(), childPriorityDO1);
        priorityDOMap.put(childPriorityDO2.getId(), childPriorityDO2);
        return priorityDOMap;
    }

    /**
     * 构造根权限DO对象
     *
     * @return 权限DO对象
     */
    private PriorityDO createPriorityDO(Long parentId) throws Exception {
        Random random = new Random();
        int randomInt = random.nextInt() * 100;
        PriorityDO priorityDO = new PriorityDO();
        priorityDO.setCode("TEST_" + randomInt);
        priorityDO.setParentId(parentId);
        priorityDO.setPriorityType(PriorityType.MENU);
        priorityDO.setUrl(null);
        priorityDO.setPriorityComment("TEST_" + randomInt);
        priorityDO.setGmtCreate(dateProvider.getCurrentTime());
        priorityDO.setGmtModified(dateProvider.getCurrentTime());
        priorityDAO.savePriority(priorityDO);
        return priorityDO;
    }

    /**
     * 比较权限集合
     *
     * @param priorityDOs   查询出来的权限集合
     * @param priorityDOMap 构造出来的权限
     */
    private void comparePriorityDOs(List<PriorityDO> priorityDOs, Map<Long, PriorityDO> priorityDOMap) {
        assertEquals(priorityDOMap.size(), priorityDOs.size());
        for (PriorityDO priorityDO : priorityDOs) {
            assertEquals(priorityDOMap.get(priorityDO.getId()), priorityDO);
        }
    }

}
