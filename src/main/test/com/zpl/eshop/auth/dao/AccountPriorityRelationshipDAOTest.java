package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.AccountPriorityRelationshipDO;
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
 * 帐号和权限关系管理模块 DAO 组件接口测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/1 23:29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class AccountPriorityRelationshipDAOTest {

    /**
     * 帐号权限关心管理模块DAO组件
     */
    @Autowired
    private AccountPriorityRelationshipDAO accountPriorityRelationshipDAO;

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
        Long accountId = 1L;
        Long priorityId = 1L;
        AccountPriorityRelationshipDO accountPriorityRelationshipDO = createAccountPriorityRelationshipDO(accountId, priorityId);
        assertNotNull(accountPriorityRelationshipDO.getId());
        assertThat(accountPriorityRelationshipDO.getId(), greaterThan(0L));
    }

    /**
     * 测试根据权限id查询记录数
     */
    @Test
    public void testGetCountByPriorityId() throws Exception {
        Long priorityId = 1L;
        Long accountId1 = 1L;
        createAccountPriorityRelationshipDO(accountId1, priorityId);

        Long accountId2 = 2L;
        createAccountPriorityRelationshipDO(accountId2, priorityId);
        Long count = accountPriorityRelationshipDAO.countByPriorityId(priorityId);
        assertNotNull(count);
        assertEquals(2L, count.longValue());
    }

    /**
     * 创建帐号和权限关系
     *
     * @return
     * @throws Exception
     */
    private AccountPriorityRelationshipDO createAccountPriorityRelationshipDO(Long accountId, Long priorityId) throws Exception {
        AccountPriorityRelationshipDO accountPriorityRelationshipDO = new AccountPriorityRelationshipDO();
        accountPriorityRelationshipDO.setAccountId(accountId);
        accountPriorityRelationshipDO.setPriorityId(priorityId);
        accountPriorityRelationshipDO.setGmtCreate(dateProvider.getCurrentTime());
        accountPriorityRelationshipDO.setGmtModified(dateProvider.getCurrentTime());
        accountPriorityRelationshipDAO.save(accountPriorityRelationshipDO);
        return accountPriorityRelationshipDO;
    }
}
