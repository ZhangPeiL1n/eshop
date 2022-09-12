package com.zpl.eshop.commodity.dao;


import com.zpl.eshop.commodity.domain.GoodsPropertyValueDO;
import com.zpl.eshop.common.util.DateProvider;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商品属性值管理DAO组件的单元测试类
 *
 * @author ZhangPeiL1n
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class GoodsPropertyValueDAOTest {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;
    /**
     * 商品属性值管理DAO组件
     */
    @Autowired
    private GoodsPropertyValueDAO goodsPropertyValueDAO;

    /**
     * 测试根据商品id查询商品属性值
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_goods_property_value.sql"})
    public void testListByGoodsId() throws Exception {
        Long goodsId = 1L;
        Integer count = 10;
        Map<Long, GoodsPropertyValueDO> expectedGoodsPropertyValueMap = createGoodsPropertyValueMap(goodsId, count);

        List<GoodsPropertyValueDO> actualGoodsPropertyValues = goodsPropertyValueDAO.listByGoodsId(goodsId);

        compareGoodsPropertyValues(count, expectedGoodsPropertyValueMap, actualGoodsPropertyValues);
    }

    /**
     * 测试新建商品属性值
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        Long goodsId = 1L;
        GoodsPropertyValueDO goodsPropertyValue = createGoodsPropertyValue(goodsId);
        Assert.assertNotNull(goodsPropertyValue.getId());
        Assert.assertThat(goodsPropertyValue.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试删除商品属性值
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_goods_property_value.sql"})
    public void testRemove() throws Exception {
        Long goodsId = 1L;
        GoodsPropertyValueDO expectedGoodsPropertyValue = createGoodsPropertyValue(goodsId);

        goodsPropertyValueDAO.removeByGoodsId(expectedGoodsPropertyValue.getGoodsId());

        List<GoodsPropertyValueDO> actualGoodsPropertyValues =
                goodsPropertyValueDAO.listByGoodsId(goodsId);

        Assert.assertEquals(0, actualGoodsPropertyValues.size());
    }

    /**
     * 比较两个商品属性值集合
     *
     * @param expectedGoodsPropertyValueMap 期望的商品属性值集合
     * @param actualGoodsPropertyValues     实际的商品属性值集合
     */
    private void compareGoodsPropertyValues(Integer expectedSize, Map<Long, GoodsPropertyValueDO> expectedGoodsPropertyValueMap, List<GoodsPropertyValueDO> actualGoodsPropertyValues) {
        Assert.assertEquals((int) expectedSize, actualGoodsPropertyValues.size());

        for (GoodsPropertyValueDO actualGoodsPropertyValue : actualGoodsPropertyValues) {
            GoodsPropertyValueDO expectedGoodsPropertyValue = expectedGoodsPropertyValueMap.get(
                    actualGoodsPropertyValue.getId());
            Assert.assertEquals(expectedGoodsPropertyValue, actualGoodsPropertyValue);
        }
    }

    /**
     * 创建商品属性值集合
     *
     * @param goodsId 商品id
     * @return 商品属性值集合
     * @throws Exception
     */
    private Map<Long, GoodsPropertyValueDO> createGoodsPropertyValueMap(Long goodsId, Integer count) throws Exception {
        Map<Long, GoodsPropertyValueDO> goodsPropertyValueMap = new HashMap<>();

        List<GoodsPropertyValueDO> goodsPropertyValues = createGoodsPropertyValues(goodsId, count);
        for (GoodsPropertyValueDO goodsPropertyValue : goodsPropertyValues) {
            goodsPropertyValueMap.put(goodsPropertyValue.getId(), goodsPropertyValue);
        }

        return goodsPropertyValueMap;
    }

    /**
     * 创建商品属性值集合
     *
     * @param goodsId 商品id
     * @param count   商品数量
     * @return 商品属性值集合
     * @throws Exception
     */
    private List<GoodsPropertyValueDO> createGoodsPropertyValues(Long goodsId, Integer count) throws Exception {
        List<GoodsPropertyValueDO> goodsPropertyValues = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            goodsPropertyValues.add(createGoodsPropertyValue(goodsId));
        }
        return goodsPropertyValues;
    }

    /**
     * 创建商品属性值
     *
     * @return 商品
     * @throws Exception
     */
    private GoodsPropertyValueDO createGoodsPropertyValue(Long goodsId) throws Exception {
        GoodsPropertyValueDO goodsPropertyValue = new GoodsPropertyValueDO();

        goodsPropertyValue.setGoodsId(goodsId);
        goodsPropertyValue.setRelationId(1L);
        goodsPropertyValue.setPropertyValue("测试值");
        goodsPropertyValue.setType(1);
        goodsPropertyValue.setGmtCreate(dateProvider.getCurrentTime());
        goodsPropertyValue.setGmtModified(dateProvider.getCurrentTime());

        goodsPropertyValueDAO.save(goodsPropertyValue);

        return goodsPropertyValue;
    }

}
