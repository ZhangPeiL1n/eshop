package com.zpl.eshop.wms.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderPickingItemDO;
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
 * 销售出库单拣货条目DAO单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/10/21 10:50
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class SaleDeliveryOrderPickingItemDAOTest {

    /**
     * 销售出库单管理DAO组件
     */
    @Autowired
    private SaleDeliveryOrderPickingItemDAO pickingItemDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增商品拣货条目
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_sale_delivery_order_picking_item.sql"})
    public void testSave() throws Exception {
        Long saleDeliveryOrderItemId = 1L;
        Long goodsAllocationId = 1L;
        Long goodsSkuId = 1L;
        SaleDeliveryOrderPickingItemDO pickingItem = createSaleDeliveryOrderPickingItem(
                goodsAllocationId, goodsSkuId, saleDeliveryOrderItemId);
        Assert.assertNotNull(pickingItem.getId());
        Assert.assertThat(pickingItem.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试查询销售出库单拣货条目
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_sale_delivery_order_picking_item.sql"})
    public void testListBySaleDeliveryOrderId() throws Exception {
        Integer count = 10;
        Long saleDeliveryOrderItemId = 1L;
        Map<Long, SaleDeliveryOrderPickingItemDO> expectedSaleDeliveryOrderPickingItemMap =
                createSaleDeliveryOrderPickingItemMap(count, saleDeliveryOrderItemId);

        List<SaleDeliveryOrderPickingItemDO> actualSaleDeliveryOrderPickingItems = pickingItemDAO
                .listBySaleDeliveryOrderItemId(saleDeliveryOrderItemId);

        compareSaleDeliveryOrderPickingItems(count, expectedSaleDeliveryOrderPickingItemMap,
                actualSaleDeliveryOrderPickingItems);
    }

    /**
     * 比较销售出库单集合
     *
     * @param expectedSaleDeliveryOrderPickingItemMap 期望的销售出库单拣货条目集合
     * @param actualSaleDeliveryOrderPickingItems     实际的销售出库单拣货条目集合
     * @throws Exception
     */
    private void compareSaleDeliveryOrderPickingItems(Integer expectedSize,
                                                      Map<Long, SaleDeliveryOrderPickingItemDO> expectedSaleDeliveryOrderPickingItemMap,
                                                      List<SaleDeliveryOrderPickingItemDO> actualSaleDeliveryOrderPickingItems) throws Exception {
        Assert.assertEquals((int) expectedSize, actualSaleDeliveryOrderPickingItems.size());

        for (SaleDeliveryOrderPickingItemDO actualSaleDeliveryOrderPickingItem : actualSaleDeliveryOrderPickingItems) {
            SaleDeliveryOrderPickingItemDO expectedSaleDeliveryOrderPickingItem = expectedSaleDeliveryOrderPickingItemMap.get(actualSaleDeliveryOrderPickingItem.getId());
            Assert.assertEquals(expectedSaleDeliveryOrderPickingItem, actualSaleDeliveryOrderPickingItem);
        }
    }

    /**
     * 创建销售出库单map
     *
     * @param count 销售出库单数量
     * @return 销售出库单map
     * @throws Exception
     */
    private Map<Long, SaleDeliveryOrderPickingItemDO> createSaleDeliveryOrderPickingItemMap(Integer count,
                                                                                            Long saleDeliveryOrderItemId) throws Exception {
        Map<Long, SaleDeliveryOrderPickingItemDO> pickingItemMap = new HashMap<Long, SaleDeliveryOrderPickingItemDO>();

        List<SaleDeliveryOrderPickingItemDO> pickingItems = createSaleDeliveryOrderPickingItems(count, saleDeliveryOrderItemId);
        for (SaleDeliveryOrderPickingItemDO pickingItem : pickingItems) {
            pickingItemMap.put(pickingItem.getId(), pickingItem);
        }

        return pickingItemMap;
    }

    /**
     * 创建销售出库单拣货条目集合
     *
     * @param count 销售出库单拣货条目数量
     * @return 销售出库单拣货条目集合
     * @throws Exception
     */
    private List<SaleDeliveryOrderPickingItemDO> createSaleDeliveryOrderPickingItems(Integer count, Long saleDeliveryOrderItemId) throws Exception {
        List<SaleDeliveryOrderPickingItemDO> pickingItems = new ArrayList<SaleDeliveryOrderPickingItemDO>();
        for (int i = 0; i < count; i++) {
            pickingItems.add(createSaleDeliveryOrderPickingItem(saleDeliveryOrderItemId, (long) i, (long) i));
        }
        return pickingItems;
    }

    /**
     * 创建一个销售出库单拣货条目
     *
     * @param saleDeliveryOrderItemId
     * @return
     * @throws Exception
     */
    private SaleDeliveryOrderPickingItemDO createSaleDeliveryOrderPickingItem(
            Long saleDeliveryOrderItemId, Long goodsAllocationId, Long goodsSkuId) throws Exception {
        SaleDeliveryOrderPickingItemDO pickingItem = new SaleDeliveryOrderPickingItemDO();
        pickingItem.setGoodsSkuId(goodsSkuId);
        pickingItem.setGoodsAllocationId(goodsAllocationId);
        pickingItem.setSaleDeliveryOrderItemId(saleDeliveryOrderItemId);
        pickingItem.setPickingCount(3L);
        pickingItem.setGmtCreate(dateProvider.getCurrentTime());
        pickingItem.setGmtModified(dateProvider.getCurrentTime());

        pickingItemDAO.save(pickingItem);

        return pickingItem;
    }
}
