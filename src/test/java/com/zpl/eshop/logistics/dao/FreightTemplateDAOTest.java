package com.zpl.eshop.logistics.dao;

import com.zpl.eshop.logistics.domain.FreightTemplateDO;
import com.zpl.eshop.logistics.domain.FreightTemplateQuery;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 运费模版管理DAO单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/10/11 22:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(rollbackFor = Exception.class)
@Rollback()
public class FreightTemplateDAOTest {


    /**
     * 运费模板管理DAO组件
     */
    @Autowired
    private FreightTemplateDAO freightTemplateDAO;


    /**
     * 测试新增运费模板
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        FreightTemplateDO freightTemplate = createFreightTemplate();
        Assert.assertNotNull(freightTemplate.getId());
        Assert.assertThat(freightTemplate.getId(), Matchers.greaterThan(0L));
    }


    /**
     * 测试分页查询运费模板
     */
    @Test
    @Sql({"clean_freight_template.sql"})
    public void testListByPage() throws Exception {
        int count = 30;
        Map<Long, FreightTemplateDO> expectedFreightTemplates =
                createFreightTemplates(count);

        for (FreightTemplateDO expectedFreightTemplate : expectedFreightTemplates.values()) {
            expectedFreightTemplate.setRule(null);
        }

        Integer offset = 20;
        Integer size = 10;

        FreightTemplateQuery query = new FreightTemplateQuery();
        query.setOffset(offset);
        query.setSize(size);

        List<FreightTemplateDO> actualFreightTemplates = freightTemplateDAO.listByPage(query);

        compareFreightTemplates(size, expectedFreightTemplates, actualFreightTemplates);
    }


    /**
     * 测试根据id查询运费模板
     *
     * @throws Exception
     */
    @Test
    public void testGetById() throws Exception {
        FreightTemplateDO expectedFreightTemplate = createFreightTemplate();
        FreightTemplateDO actualFreightTemplate = freightTemplateDAO.getById(
                expectedFreightTemplate.getId());
        Assert.assertEquals(expectedFreightTemplate, actualFreightTemplate);
    }


    /**
     * 测试更新运费模板
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        FreightTemplateDO freightTemplate = createFreightTemplate();

        freightTemplate.setRemark("修改后的测试运费模板备注");
        freightTemplateDAO.update(freightTemplate);

        FreightTemplateDO resultFreightTemplate = freightTemplateDAO.getById(
                freightTemplate.getId());

        Assert.assertEquals(freightTemplate, resultFreightTemplate);
    }


    /**
     * 比较两个运费模板集合
     *
     * @param expectedFreightTemplates
     * @param actualFreightTemplates
     * @throws Exception
     */
    private void compareFreightTemplates(
            Integer expectedSize,
            Map<Long, FreightTemplateDO> expectedFreightTemplates,
            List<FreightTemplateDO> actualFreightTemplates) throws Exception {
        Assert.assertEquals((int) expectedSize, actualFreightTemplates.size());
        for (FreightTemplateDO actualFreightTemplate : actualFreightTemplates) {
            FreightTemplateDO expectedFreightTemplate = expectedFreightTemplates.get(
                    actualFreightTemplate.getId());
            Assert.assertEquals(expectedFreightTemplate, actualFreightTemplate);
        }
    }


    /**
     * 创建运费模板集合
     *
     * @param count
     * @return
     * @throws Exception
     */
    private Map<Long, FreightTemplateDO> createFreightTemplates(int count) throws Exception {
        Map<Long, FreightTemplateDO> freightTemplateMap = new HashMap<Long, FreightTemplateDO>();

        FreightTemplateDO freightTemplate = null;
        for (int i = 0; i < count; i++) {
            freightTemplate = createFreightTemplate();
            freightTemplateMap.put(freightTemplate.getId(), freightTemplate);
        }

        return freightTemplateMap;
    }


    /**
     * 创建运费模板
     *
     * @return 运费模板
     * @throws Exception
     */
    private FreightTemplateDO createFreightTemplate() throws Exception {
        FreightTemplateDO freightTemplate = new FreightTemplateDO();
        freightTemplate.setName("测试运费模板");
        freightTemplate.setType(1);
        freightTemplate.setRule("测试运费规则");
        freightTemplate.setRemark("测试运费模板");

        freightTemplateDAO.save(freightTemplate);

        return freightTemplate;
    }
}
