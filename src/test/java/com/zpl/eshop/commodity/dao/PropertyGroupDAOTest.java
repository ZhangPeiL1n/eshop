package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.PropertyGroupDO;
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
 * 属性分组管理DAO组件单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/8/4 21:15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class PropertyGroupDAOTest {


    /**
     * 属性分组DAO组件
     */
    @Autowired
    private PropertyGroupDAO propertyGroupDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增属性分组
     */
    @Test
    public void testSave() throws Exception {
        Long categoryId = 1L;
        PropertyGroupDO propertyGroup = createPropertyGroup(categoryId);
        Assert.assertNotNull(propertyGroup.getId());
        Assert.assertThat(propertyGroup.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试根据类目id获取属性分组
     *
     * @throws Exception
     */
    @Test
    public void testListByCategoryId() throws Exception {
        Long categoryId = 1L;
        int propertyGroupSize = 5;
        Map<Long, PropertyGroupDO> targetPropertyGroups = createPropertyGroups(categoryId, propertyGroupSize);

        List<PropertyGroupDO> resultPropertyGroups = propertyGroupDAO.listByCategoryId(categoryId);

        Assert.assertThat(resultPropertyGroups.size(), Matchers.greaterThanOrEqualTo(propertyGroupSize));
        resultPropertyGroups.forEach(propertyGroup -> {
            Assert.assertEquals(targetPropertyGroups.get(propertyGroup.getId()), propertyGroup);
        });
    }


    /**
     * 测试根据类目id删除属性分组
     *
     * @throws Exception
     */
    @Test
    public void testRemoveByCategoryId() throws Exception {
        Long categoryId = 1L;
        int propertyGroupSize = 5;
        createPropertyGroups(categoryId, propertyGroupSize);

        propertyGroupDAO.removeByCategoryId(categoryId);
        List<PropertyGroupDO> propertyGroups = propertyGroupDAO.listByCategoryId(categoryId);
        Assert.assertEquals(0, propertyGroups.size());
    }

    /**
     * 创建属性分组
     *
     * @param categoryId 类目id
     * @return
     * @throws Exception
     */
    private PropertyGroupDO createPropertyGroup(Long categoryId) throws Exception {
        PropertyGroupDO propertyGroup = new PropertyGroupDO();
        propertyGroup.setCategoryId(categoryId);
        propertyGroup.setName("测试属性分组");
        propertyGroup.setGmtCreate(dateProvider.getCurrentTime());
        propertyGroup.setGmtModified(dateProvider.getCurrentTime());
        propertyGroupDAO.save(propertyGroup);
        return propertyGroup;
    }

    /**
     * 创建属性分组集合
     *
     * @param categoryId        类目id
     * @param propertyGroupSize 属性分组数量
     * @return
     * @throws Exception
     */
    private Map<Long, PropertyGroupDO> createPropertyGroups(Long categoryId, int propertyGroupSize) throws Exception {
        Map<Long, PropertyGroupDO> propertyGroupMap = new HashMap<>();
        for (int i = 0; i < propertyGroupSize; i++) {
            PropertyGroupDO propertyGroup = createPropertyGroup(categoryId);
            propertyGroupMap.put(propertyGroup.getId(), propertyGroup);
        }
        return propertyGroupMap;
    }
}
