package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsSkuSalePropertyValueDO;
import com.zpl.eshop.common.util.DateProvider;
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

import static org.hamcrest.Matchers.greaterThan;


/**
 * 商品sku销售属性值管理DAO组件的单元测试类
 *
 * @author ZhangPeiL1n
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class GoodsSkuSalePropertyValueDAOTest {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;
    /**
     * 商品sku销售属性值管理DAO组件
     */
    @Autowired
    private GoodsSkuSalePropertyValueDAO goodsSkuSalePropertyValueDAO;

    /**
     * 测试根据商品id查询商品sku销售属性值
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_goods_sku_sale_property_value.sql"})
    public void testListByGoodsSkuId() throws Exception {
        Long goodsSkuId = 1L;
        Integer count = 10;
        Map<Long, GoodsSkuSalePropertyValueDO> expectedGoodsSkuSalePropertyValueMap =
                createGoodsSkuSalePropertyValueMap(goodsSkuId, count);

        List<GoodsSkuSalePropertyValueDO> actualGoodsSkuSalePropertyValues =
                goodsSkuSalePropertyValueDAO.listByGoodsSkuId(goodsSkuId);

        compareGoodsSkuSalePropertyValues(count, expectedGoodsSkuSalePropertyValueMap,
                actualGoodsSkuSalePropertyValues);
    }

    /**
     * 测试新建商品sku销售属性值
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        Long goodsSkuId = 1L;
        GoodsSkuSalePropertyValueDO goodsSkuSalePropertyValue = createGoodsSkuSalePropertyValue(goodsSkuId);
        Assert.assertNotNull(goodsSkuSalePropertyValue.getId());
        Assert.assertThat(goodsSkuSalePropertyValue.getId(), greaterThan(0L));
    }

    /**
     * 测试删除商品sku销售属性值
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_goods_sku_sale_property_value.sql"})
    public void testRemove() throws Exception {
        Long goodsSkuId = 1L;
        GoodsSkuSalePropertyValueDO expectedGoodsSkuSalePropertyValue = createGoodsSkuSalePropertyValue(goodsSkuId);

        goodsSkuSalePropertyValueDAO.removeByGoodsSkuId(expectedGoodsSkuSalePropertyValue.getGoodsSkuId());

        List<GoodsSkuSalePropertyValueDO> actualGoodsSkuSalePropertyValues =
                goodsSkuSalePropertyValueDAO.listByGoodsSkuId(goodsSkuId);

        Assert.assertEquals(0, actualGoodsSkuSalePropertyValues.size());
    }

    /**
     * 比较两个商品sku销售属性值集合
     *
     * @param expectedGoodsSkuSalePropertyValueMap 期望的商品sku销售属性值集合
     * @param actualGoodsSkuSalePropertyValues     实际的商品sku销售属性值集合
     * @throws Exception
     */
    private void compareGoodsSkuSalePropertyValues(Integer expectedSize,
                                                   Map<Long, GoodsSkuSalePropertyValueDO> expectedGoodsSkuSalePropertyValueMap,
                                                   List<GoodsSkuSalePropertyValueDO> actualGoodsSkuSalePropertyValues) throws Exception {
        Assert.assertEquals((int) expectedSize, actualGoodsSkuSalePropertyValues.size());

        for (GoodsSkuSalePropertyValueDO actualGoodsSkuSalePropertyValue : actualGoodsSkuSalePropertyValues) {
            GoodsSkuSalePropertyValueDO expectedGoodsSkuSalePropertyValue = expectedGoodsSkuSalePropertyValueMap.get(
                    actualGoodsSkuSalePropertyValue.getId());
            Assert.assertEquals(expectedGoodsSkuSalePropertyValue, actualGoodsSkuSalePropertyValue);
        }
    }

    /**
     * 创建商品sku销售属性值集合
     *
     * @param goodsSkuId 商品id
     * @return 商品sku销售属性值集合
     * @throws Exception
     */
    private Map<Long, GoodsSkuSalePropertyValueDO> createGoodsSkuSalePropertyValueMap(
            Long goodsSkuId, Integer count) throws Exception {
        Map<Long, GoodsSkuSalePropertyValueDO> goodsSkuSalePropertyValueMap = new HashMap<>();

        List<GoodsSkuSalePropertyValueDO> goodsSkuSalePropertyValues = createGoodsSkuSalePropertyValues(goodsSkuId, count);
        for (GoodsSkuSalePropertyValueDO goodsSkuSalePropertyValue : goodsSkuSalePropertyValues) {
            goodsSkuSalePropertyValueMap.put(goodsSkuSalePropertyValue.getId(), goodsSkuSalePropertyValue);
        }

        return goodsSkuSalePropertyValueMap;
    }

    /**
     * 创建商品sku销售属性值集合
     *
     * @param goodsSkuId 商品id
     * @param count      商品数量
     * @return 商品sku销售属性值集合
     * @throws Exception
     */
    private List<GoodsSkuSalePropertyValueDO> createGoodsSkuSalePropertyValues(Long goodsSkuId, Integer count) throws Exception {
        List<GoodsSkuSalePropertyValueDO> goodsSkuSalePropertyValues = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            goodsSkuSalePropertyValues.add(createGoodsSkuSalePropertyValue(goodsSkuId));
        }
        return goodsSkuSalePropertyValues;
    }

    /**
     * 创建商品sku销售属性值
     *
     * @return 商品
     * @throws Exception
     */
    private GoodsSkuSalePropertyValueDO createGoodsSkuSalePropertyValue(Long goodsSkuId) throws Exception {
        GoodsSkuSalePropertyValueDO goodsSkuSalePropertyValue = new GoodsSkuSalePropertyValueDO();

        goodsSkuSalePropertyValue.setGoodsSkuId(goodsSkuId);
        goodsSkuSalePropertyValue.setRelationId(1L);
        goodsSkuSalePropertyValue.setPropertyValue("测试值");
        goodsSkuSalePropertyValue.setGmtCreate(dateProvider.getCurrentTime());
        goodsSkuSalePropertyValue.setGmtModified(dateProvider.getCurrentTime());

        goodsSkuSalePropertyValueDAO.save(goodsSkuSalePropertyValue);

        return goodsSkuSalePropertyValue;
    }

}
