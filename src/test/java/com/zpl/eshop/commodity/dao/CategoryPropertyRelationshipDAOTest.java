package com.zpl.eshop.commodity.dao;


import com.zpl.eshop.commodity.constant.PropertyRequired;
import com.zpl.eshop.commodity.domain.CategoryPropertyRelationshipDO;
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
     *
     * @throws Exception
     */
    public void testSave() throws Exception {
        Long categoryId = 1L;
        CategoryPropertyRelationshipDO relation = createRelation(categoryId);

        Assert.assertNotNull(relation.getId());
        Assert.assertThat(relation.getId(), Matchers.greaterThan(0L));
    }


    /**
     * 测试根据类目Id查询类目属性关联关系
     *
     * @throws Exception
     */
    @Test
    public void testListByCategoryId() throws Exception {
        Long categoryId = 1L;
        int relationSize = 5;
        Map<Long, CategoryPropertyRelationshipDO> relationMap = createRelations(categoryId, relationSize);

        List<CategoryPropertyRelationshipDO> relations = relationDAO.listByCategoryId(categoryId);
        Assert.assertThat(relations.size(), Matchers.greaterThanOrEqualTo(relationSize));
        for (CategoryPropertyRelationshipDO relation : relations) {
            Assert.assertEquals(relationMap.get(relation.getId()), relation);
        }
    }

    /**
     * 根据类目id 删除类目与属性的关联关系
     */
    @Test
    public void testRemoveByCategoryId() throws Exception {
        Long categoryId = 1L;
        int relationSize = 5;
        createRelations(categoryId, relationSize);
        relationDAO.removeByCategoryId(categoryId);
        List<CategoryPropertyRelationshipDO> relations = relationDAO.listByCategoryId(categoryId);
        Assert.assertEquals(0, relations.size());
    }


    /**
     * 创建关联关系集合
     *
     * @param categoryId   类目id
     * @param relationSize 集合大小
     * @return
     * @throws Exception
     */
    private Map<Long, CategoryPropertyRelationshipDO> createRelations(Long categoryId, int relationSize) throws Exception {
        Map<Long, CategoryPropertyRelationshipDO> relationMap = new HashMap<>();
        for (int i = 0; i < relationSize; i++) {
            CategoryPropertyRelationshipDO relation = createRelation(categoryId);
            relationMap.put(relation.getId(), relation);
        }
        return relationMap;
    }

    /**
     * 创建类目属性关联关系
     *
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
        relation.setIsRequired(PropertyRequired.YES);
        relationDAO.save(relation);
        return relation;
    }
}