package com.zpl.eshop.commodity.dao;


import com.zpl.eshop.commodity.constant.PropertyRequired;
import com.zpl.eshop.commodity.domain.CategoryPropertyRelationshipDO;
import com.zpl.eshop.common.util.DateProvider;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;


/**
 * 类目与属性关系管理DAO组件单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CategoryPropertyRelationshipDAOTest {

    /**
     * 类目与属性关联关系DAO组件
     */
    @Autowired
    private CategoryPropertyRelationshipDAO relationDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增类目与属性的关系
     * @throws Exception
     */
    public void testSave() throws Exception{
        Long categoryId = 1L;
        CategoryPropertyRelationshipDO relation = createRelation(categoryId);

        Assert.assertNotNull(relation.getId());
        Assert.assertThat(relation.getId(), Matchers.greaterThan(0L));
    }

    /**
     *  创建类目属性关联关系
     * @return
     */
    private CategoryPropertyRelationshipDO createRelation(Long categoryId) throws Exception {
        Random random = new Random();


        CategoryPropertyRelationshipDO relation = new CategoryPropertyRelationshipDO();
        relation.setCategoryId(categoryId);
        relation.setGmtCreate(dateProvider.getCurrentTime());
        relation.setGmtModified(dateProvider.getCurrentTime());
        relation.setPropertyId(random.nextLong());
        relation.setPropertyTypes("测试类型");
        relation.setRequired(PropertyRequired.YES);
        relationDAO.save(relation);
        return relation;
    }
}
