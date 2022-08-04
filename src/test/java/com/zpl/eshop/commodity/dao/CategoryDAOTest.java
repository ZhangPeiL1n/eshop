package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.constant.CategoryLeaf;
import com.zpl.eshop.commodity.domain.CategoryDO;
import com.zpl.eshop.common.util.DateProvider;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.soap.Addressing;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangPeiL1n
 * @date 2022/7/7 22:40
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CategoryDAOTest {

    /**
     * 类目管理DAO组件
     */
    @Autowired
    private CategoryDAO categoryDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增类目
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        Long parentId = null;
        CategoryDO category = createCategory(parentId);

        Assert.assertNotNull(category);
        Assert.assertThat(category.getId(), Matchers.greaterThan(0L));

    }

    /**
     * 测试查询根节点
     *
     * @throws Exception
     */
    @Test
    public void testListRoots() throws Exception {
        //构造5个根类目
        int categorySize = 5;
        Long parentId = null;

        Map<Long, CategoryDO> categories = createCategories(categorySize);
        List<CategoryDO> resultCategories = categoryDAO.listRoots();
        compareCategories(categories, resultCategories);
    }

    /**
     * 测试查询子类目
     */
    @Test
    public void testListChildren() throws Exception {
        //构造5个子类目
        int categorySize = 5;
        Long parentId = 1L;
        Map<Long, CategoryDO> categories = createCategories(categorySize, parentId);

        List<CategoryDO> resultCategories = categoryDAO.listChildren(parentId);
        compareCategories(categories, resultCategories);
    }

    /**
     * 测试根据id查询类目
     *
     * @throws Exception
     */
    public void testGetById() throws Exception {
        CategoryDO category = createCategory();

        CategoryDO resultCategory = categoryDAO.getById(category.getId());

        Assert.assertNotNull(resultCategory);
        Assert.assertEquals(category, resultCategory);
    }

    /**
     * 测试更新类目
     *
     * @throws Exception
     */
    public void testUpdate() throws Exception {
        CategoryDO category = createCategory();
        category.setDescription("修改描述");
        categoryDAO.update(category);
        CategoryDO resultCategory = categoryDAO.getById(category.getId());

        Assert.assertEquals(category.getDescription(), resultCategory.getDescription());
    }

    /**
     * 测试删除类目
     *
     * @throws Exception
     */
    public void testRemove() throws Exception {
        CategoryDO category = createCategory();
        categoryDAO.remove(category.getId());
        CategoryDO resultCategory = categoryDAO.getById(category.getId());
        Assert.assertNull(resultCategory);
    }

    /**
     * 创建类目
     *
     * @return
     */
    private CategoryDO createCategory() throws Exception {
        Long parentId = null;
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setParentId(parentId);
        return categoryDO;
    }

    /**
     * 创建类目
     *
     * @return
     */
    private CategoryDO createCategory(Long parentId) throws Exception {
        CategoryDO category = new CategoryDO();
        category.setIsLeaf(CategoryLeaf.NO);
        category.setDescription("测试类目");
        category.setGmtCreate(dateProvider.getCurrentTime());
        category.setGmtModified(dateProvider.getCurrentTime());
        category.setName("测试类目");
        category.setParentId(parentId);
        categoryDAO.save(category);
        return category;
    }

    /**
     * 创建类目集合
     *
     * @param categoryCount 集合长度
     * @return 类目集合
     * @throws Exception
     */
    private Map<Long, CategoryDO> createCategories(int categoryCount) throws Exception {
        Long parentId = null;
        return createCategories(categoryCount, parentId);
    }

    /**
     * 创建类目集合
     *
     * @param categoryCount 集合长度
     * @param parentId      父类目id
     * @return 类目集合
     * @throws Exception
     */
    private Map<Long, CategoryDO> createCategories(int categoryCount, Long parentId) throws Exception {
        Map<Long, CategoryDO> categoryMap = new HashMap<>();
        for (int i = 0; i < categoryCount; i++) {
            CategoryDO category = createCategory(parentId);
            categoryMap.put(category.getId(), category);
        }
        return categoryMap;
    }

    /**
     * 比较两个类目集合
     *
     * @param targetCategoryMap 目标类目结合
     * @param resultCategories  结果类目集合
     * @throws Exception
     */
    private void compareCategories(Map<Long, CategoryDO> targetCategoryMap, List<CategoryDO> resultCategories) throws Exception {
        Assert.assertThat(resultCategories.size(), Matchers.greaterThanOrEqualTo(targetCategoryMap.size()));
        for (CategoryDO categoryDO : resultCategories) {
            CategoryDO targetCategory = targetCategoryMap.get(categoryDO.getId());
            if (targetCategory == null) {
                continue;
            }
            Assert.assertEquals(categoryDO, targetCategory);
        }
    }
}
