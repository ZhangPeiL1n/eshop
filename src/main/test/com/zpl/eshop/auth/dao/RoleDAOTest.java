package com.zpl.eshop.auth.dao;

import com.zpl.eshop.auth.domain.RoleDO;
import com.zpl.eshop.auth.domain.RoleQuery;
import com.zpl.eshop.common.util.DateProvider;
import org.hamcrest.Matchers;
import org.junit.Assert;
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

/**
 * 角色管理模块DAO组件测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/5/23 22:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class RoleDAOTest {

    /**
     * 角色管理模块DAO组件
     */
    @Autowired
    private RoleDAO roleDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试分页查询角色
     *
     * @throws Exception
     */
    @Test
    public void testListByPage() throws Exception {

        String namePrefix = "测试角色";
        String codePrefix = "TEST_ROLE";
        String otherName = "别的角色";
        String otherCode = "OTHER_CODE";
        RoleDO role;
        Map<Long, RoleDO> roleMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            role = createRole(namePrefix + i, codePrefix + i);
            roleMap.put(role.getId(), role);
        }


        Integer offsite = 2;
        Integer size = 2;
        RoleQuery query = new RoleQuery();
        query.setSize(size);
        query.setOffsite(offsite);
        query.setName(namePrefix);
        query.setCode(codePrefix);


        List<RoleDO> resultRoles = roleDAO.listByPage(query);
        Assert.assertEquals((int) size, resultRoles.size());
        // Assert.assertEquals();
        for (RoleDO resultRole : resultRoles) {
            Assert.assertEquals(roleMap.get(resultRole.getId()), resultRole);
        }
    }

    /**
     * 测试新增角色
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        RoleDO role = createRole("测试角色", "TEST_CODE");
        Assert.assertNotNull(role.getId());
        Assert.assertThat(role.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试根据id查找角色
     */
    @Test
    public void testGetById() throws Exception {
        RoleDO role = createRole("测试角色", "TEST_CODE");
        RoleDO resultRole = roleDAO.getById(role.getId());
        Assert.assertEquals(role, resultRole);
    }

    /**
     * 测试更新角色
     */
    @Test
    public void testUpdate() throws Exception {
        RoleDO role = createRole("测试角色", "TEST_CODE");
        role.setName(role.getName() + "_修改");
        role.setCode(role.getCode() + "_modified");
        roleDAO.update(role);
        RoleDO resultRole = roleDAO.getById(role.getId());
        Assert.assertEquals(role, resultRole);
    }

    /**
     * 测试根据id删除
     */
    @Test
    public void testRemove() throws Exception {
        RoleDO role = createRole("测试角色", "TEST_CODE");
        roleDAO.remove(role.getId());
        RoleDO resultRole = roleDAO.getById(role.getId());
        Assert.assertNull(resultRole);
    }

    /**
     * 创建角色
     *
     * @return
     * @throws Exception
     */
    private RoleDO createRole(String name, String code) throws Exception {
        RoleDO role = new RoleDO();
        role.setName(name);
        role.setCode(code);
        role.setRemark(name);
        role.setGmtCreate(dateProvider.getCurrentTime());
        role.setGmtModified(dateProvider.getCurrentTime());

        roleDAO.save(role);
        return role;
    }
}
