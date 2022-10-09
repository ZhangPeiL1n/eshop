package com.zpl.eshop.wms.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 采购入库单条目DAO单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/10/9 17:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class PurchaseInputOrderItemDAOTest {

    /**
     * 日期辅助组件
     */
    @MockBean
    private DateProvider dateProvider;
    /**
     * 采购入库单条目管理DAO组件
     */
    @Autowired
    private PurchaseInputOrderItemDAO purchaseInputOrderItemDAO;

    /**
     * 初始化
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Mockito.when(dateProvider.getCurrentTime()).thenReturn(formatter.parse(formatter.format(new Date())));
    }

    /**
     * 测试新建采购入库单条目
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_input_order_item.sql"})
    public void testBatchSave() throws Exception {
        Long purchaseInputOrderId = 1L;
        Integer count = 10;
        List<PurchaseInputOrderItemDO> expectedPurchaseInputOrderItems =
                createPurchaseInputOrderItems(count, purchaseInputOrderId);

        purchaseInputOrderItemDAO.batchSave(purchaseInputOrderId, expectedPurchaseInputOrderItems);

        List<PurchaseInputOrderItemDO> actualPurchaseInputOrderItems =
                purchaseInputOrderItemDAO.listByPurchaseInputOrderId(purchaseInputOrderId);

        Assert.assertEquals((int) count, actualPurchaseInputOrderItems.size());
    }

    /**
     * 测试根据id查询采购入库单条目
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_input_order_item.sql"})
    public void testListByPurchaseInputOrderId() throws Exception {
        Long purchaseInputOrderId = 1L;
        Integer count = 10;
        Map<Long, PurchaseInputOrderItemDO> expectedPurchaseInputOrderItems =
                createPurchaseInputOrderItemMap(count, purchaseInputOrderId);

        List<PurchaseInputOrderItemDO> actualPurchaseInputOrderItems =
                purchaseInputOrderItemDAO.listByPurchaseInputOrderId(purchaseInputOrderId);

        comparePurchaseInputOrderItem(count, expectedPurchaseInputOrderItems,
                actualPurchaseInputOrderItems);
    }

    /**
     * 测试更新采购入库单条目
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_input_order_item.sql"})
    public void testUpdate() throws Exception {
        Long purchaseInputOrderId = 1L;
        Long goodsSkuId = 1L;
        PurchaseInputOrderItemDO expectedPurchaseInputOrderItem =
                createPurchaseInputOrderItem(purchaseInputOrderId, goodsSkuId);

        List<PurchaseInputOrderItemDO> expectedPurchaseInputOrderItems = new ArrayList<>();
        expectedPurchaseInputOrderItems.add(expectedPurchaseInputOrderItem);
        purchaseInputOrderItemDAO.batchSave(purchaseInputOrderId, expectedPurchaseInputOrderItems);

        expectedPurchaseInputOrderItem.setQualifiedCount(1000L);
        expectedPurchaseInputOrderItem.setArrivalCount(1000L);
        purchaseInputOrderItemDAO.update(expectedPurchaseInputOrderItem);

        List<PurchaseInputOrderItemDO> actualPurchaseInputOrderItems =
                purchaseInputOrderItemDAO.listByPurchaseInputOrderId(purchaseInputOrderId);

        Assert.assertEquals(1, actualPurchaseInputOrderItems.size());
        Assert.assertEquals(expectedPurchaseInputOrderItem, actualPurchaseInputOrderItems.get(0));
    }

    /**
     * 比较两个采购入库单条目集合
     *
     * @param expectedPurchaseInputOrderItems 期望的采购入库单条目集合
     * @param actualPurchaseInputOrderItems   实际的采购入库单条目集合
     * @throws Exception
     */
    private void comparePurchaseInputOrderItem(
            Integer expectedSize,
            Map<Long, PurchaseInputOrderItemDO> expectedPurchaseInputOrderItems,
            List<PurchaseInputOrderItemDO> actualPurchaseInputOrderItems) throws Exception {
        Assert.assertEquals((int) expectedSize, actualPurchaseInputOrderItems.size());
        for (PurchaseInputOrderItemDO actualPurchaseInputOrderItem : actualPurchaseInputOrderItems) {
            PurchaseInputOrderItemDO expectedPurchaseInputOrderItem =
                    expectedPurchaseInputOrderItems.get(actualPurchaseInputOrderItem.getId());
            Assert.assertEquals(expectedPurchaseInputOrderItem, actualPurchaseInputOrderItem);
        }
    }

    /**
     * 创建采购入库单条目集合
     *
     * @param categoryId 类目id
     * @return 采购入库单条目集合
     * @throws Exception
     */
    private Map<Long, PurchaseInputOrderItemDO> createPurchaseInputOrderItemMap(
            Integer count, Long purchaseInputOrderId) throws Exception {
        Map<Long, PurchaseInputOrderItemDO> purchaseInputOrderItemMap = new HashMap<>();

        List<PurchaseInputOrderItemDO> purchaseInputOrderItemList =
                createPurchaseInputOrderItems(count, purchaseInputOrderId);

        purchaseInputOrderItemDAO.batchSave(purchaseInputOrderId, purchaseInputOrderItemList);

        for (PurchaseInputOrderItemDO purchaseInputOrderItem : purchaseInputOrderItemList) {
            purchaseInputOrderItemMap.put(purchaseInputOrderItem.getId(), purchaseInputOrderItem);
        }

        return purchaseInputOrderItemMap;
    }

    /**
     * 创建采购入库单条目集合
     *
     * @param categoryId 类目id
     * @param count      采购入库单条目数量
     * @return 采购入库单条目集合
     * @throws Exception
     */
    private List<PurchaseInputOrderItemDO> createPurchaseInputOrderItems(
            Integer count, Long purchaseInputOrderId) throws Exception {
        List<PurchaseInputOrderItemDO> purchaseInputOrderItemList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            purchaseInputOrderItemList.add(createPurchaseInputOrderItem(purchaseInputOrderId, (long) i));
        }
        return purchaseInputOrderItemList;
    }

    /**
     * 创建采购入库单条目
     *
     * @return 采购入库单条目
     * @throws Exception
     */
    private PurchaseInputOrderItemDO createPurchaseInputOrderItem(
            Long purchaseInputOrderId, Long goodsSkuId) throws Exception {
        PurchaseInputOrderItemDO purchaseInputOrderItem = new PurchaseInputOrderItemDO();
        purchaseInputOrderItem.setPurchaseInputOrderId(purchaseInputOrderId);
        purchaseInputOrderItem.setGoodsSkuId(goodsSkuId);
        purchaseInputOrderItem.setPurchaseCount(1000L);
        purchaseInputOrderItem.setPurchasePrice(5999.0);
        purchaseInputOrderItem.setGmtCreate(dateProvider.getCurrentTime());
        purchaseInputOrderItem.setGmtModified(dateProvider.getCurrentTime());

        return purchaseInputOrderItem;
    }
}
