package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.constant.PropertyInputType;
import com.zpl.eshop.commodity.domain.PropertyDO;
import com.zpl.eshop.commodity.domain.PropertyQuery;
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

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * 属性管理模块DAO组件的单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/6 22:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class PropertyDAOTest {

    /**
     * 属性管理模块DAO组件
     */
    @Autowired
    private PropertyDAO propertyDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试分页查询商品属性
     */
    @Test
    public void testListPropertiesByPage() throws Exception {
        PropertyDO propertyDO1 = createPropertyDO("机身颜色1", "手机机身颜色", "红色,白色,蓝色");
        PropertyDO propertyDO2 = createPropertyDO("机身颜色2", "手机机身颜色", "红色,白色,蓝色");
        PropertyDO propertyDO3 = createPropertyDO("机身颜色3", "手机机身颜色", "红色,白色,蓝色");
        PropertyDO propertyDO4 = createPropertyDO("机身颜色4", "手机机身颜色", "红色,白色,蓝色");
        PropertyDO propertyDO5 = createPropertyDO("摄像头像素", "手机前后摄像头的像素", null);
        PropertyDO propertyDO6 = createPropertyDO("机身颜色6", "手机机身颜色", "红色,白色,蓝色");

        Map<Long, PropertyDO> propertyDOMap = new HashMap<>();
        propertyDOMap.put(propertyDO1.getId(), propertyDO1);
        propertyDOMap.put(propertyDO2.getId(), propertyDO2);
        propertyDOMap.put(propertyDO3.getId(), propertyDO3);
        propertyDOMap.put(propertyDO4.getId(), propertyDO4);
        propertyDOMap.put(propertyDO5.getId(), propertyDO5);
        propertyDOMap.put(propertyDO6.getId(), propertyDO6);

        PropertyQuery propertyQuery = new PropertyQuery();
        propertyQuery.setPropertyName("机身颜色");
        propertyQuery.setOffset(2);
        propertyQuery.setSize(2);
        List<PropertyDO> propertyDOs = propertyDAO.listPropertiesByPage(propertyQuery);
        assertEquals(propertyQuery.getSize().intValue(), propertyDOs.size());
        for (PropertyDO propertyDO : propertyDOs) {
            PropertyDO sourcePropertyDO = propertyDOMap.get(propertyDO.getId());
            assertEquals(sourcePropertyDO, propertyDO);
            assertTrue(propertyDO.getId().equals(propertyDO3.getId()) || propertyDO.getId().equals(propertyDO4.getId()));
        }

    }

    /**
     * 测试新增商品属性
     */
    @Test
    public void testSaveProperty() throws Exception {
        PropertyDO propertyDO = createPropertyDO("机身颜色", "手机机身颜色", "红色,白色,蓝色");
        assertNotNull(propertyDO.getId());
        assertThat(propertyDO.getId(), greaterThan(0L));
    }

    /**
     * 测试根据id查询商品属性
     */
    @Test
    public void testGetPropertyById() throws Exception {
        PropertyDO propertyDO = createPropertyDO("机身颜色", "手机机身颜色", "红色,白色,蓝色");
        PropertyDO resultPropertyDO = propertyDAO.getPropertyById(propertyDO.getId());
        assertEquals(propertyDO, resultPropertyDO);
    }

    /**
     * 测试更新商品属性
     */
    @Test
    public void testUpdateProperty() throws Exception {
        PropertyDO propertyDO = createPropertyDO("机身颜色", "手机机身颜色", "红色,白色,蓝色");
        propertyDO.setGmtModified(dateProvider.getCurrentTime());
        propertyDAO.updateProperty(propertyDO);
        PropertyDO resultPropertyDO = propertyDAO.getPropertyById(propertyDO.getId());
        assertEquals(propertyDO, resultPropertyDO);
    }

    /**
     * 创建属性DO对象
     *
     * @return 属性DO对象
     */
    private PropertyDO createPropertyDO(String propertyName, String propertyDesc, String inputValue) throws Exception {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setPropertyName(propertyName);
        propertyDO.setPropertyDesc(propertyDesc);
        propertyDO.setInputType(PropertyInputType.MULTIPLE_CHOICE);
        propertyDO.setInputValues(inputValue);
        propertyDO.setGmtCreate(dateProvider.getCurrentTime());
        propertyDO.setGmtModified(dateProvider.getCurrentTime());
        propertyDAO.saveProperty(propertyDO);
        return propertyDO;
    }
}
