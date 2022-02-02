package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.RolePriorityRelationshipDO;
import com.zpl.eshop.common.util.DateProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * 角色和权限关系管理模块 DAO 组件接口测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/1 23:29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class RolePriorityRelationshipDAOTest {

    /**
     * 帐号权限关心管理模块DAO组件
     */
    @Autowired
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增帐号权限关联关系
     */
    @Test
    public void testSave() throws Exception {
        Long roleId = 1L;
        Long priorityId = 1L;
        RolePriorityRelationshipDO rolePriorityRelationshipDO = createRolePriorityRelationshipDO(roleId, priorityId);
        assertNotNull(rolePriorityRelationshipDO.getId());
        assertThat(rolePriorityRelationshipDO.getId(), greaterThan(0L));
    }

    /**
     * 测试根据权限id查询记录数
     */
    @Test
    public void testGetCountByPriorityId() throws Exception {
        Long priorityId = 1L;
        Long roleId1 = 1L;
        createRolePriorityRelationshipDO(roleId1, priorityId);

        Long roleId2 = 2L;
        createRolePriorityRelationshipDO(roleId2, priorityId);
        Long count = rolePriorityRelationshipDAO.countByPriorityId(priorityId);
        assertNotNull(count);
        assertEquals(2L, count.longValue());
    }

    /**
     * 创建角色和权限关系
     *
     * @return 角色权限关系DO
     * @throws Exception
     */
    private RolePriorityRelationshipDO createRolePriorityRelationshipDO(Long roleId, Long priorityId) throws Exception {
        RolePriorityRelationshipDO rolePriorityRelationshipDO = new RolePriorityRelationshipDO();
        rolePriorityRelationshipDO.setRoleId(roleId);
        rolePriorityRelationshipDO.setPriorityId(priorityId);
        rolePriorityRelationshipDO.setGmtCreate(dateProvider.getCurrentTime());
        rolePriorityRelationshipDO.setGmtModified(dateProvider.getCurrentTime());
        rolePriorityRelationshipDAO.save(rolePriorityRelationshipDO);
        return rolePriorityRelationshipDO;
    }
}
