package com.zpl.eshop.purchase.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.purchase.domain.PurchaseOrderDO;
import com.zpl.eshop.purchase.domain.PurchaseOrderQuery;
import org.hamcrest.Matchers;
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
 * 采购单管理DAO单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/10/21 16:54
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class PurchaseOrderDAOTest {

    /**
     * 日期辅助组件
     */
    @MockBean
    private DateProvider dateProvider;
    /**
     * 采购单管理DAO组件
     */
    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;

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
     * 测试新建采购单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_order.sql"})
    public void testSave() throws Exception {
        PurchaseOrderDO expectedPurchaseOrder = createPurchaseOrder();
        Assert.assertNotNull(expectedPurchaseOrder.getId());
        Assert.assertThat(expectedPurchaseOrder.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试分页查询采购单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_order.sql"})
    public void testListByPage() throws Exception {
        Integer count = 30;
        Map<Long, PurchaseOrderDO> expectedPurchaseOrders =
                createPurchaseOrderMap(count);

        Integer offset = 10;
        Integer size = 10;
        PurchaseOrderQuery query = createPurchaseOrderQuery(offset, size);
        List<PurchaseOrderDO> actualPurchaseOrders = purchaseOrderDAO.listByPage(query);

        comparePurchaseOrder(size, expectedPurchaseOrders, actualPurchaseOrders);
    }

    /**
     * 测试根据id查询采购单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_order.sql"})
    public void testGetById() throws Exception {
        PurchaseOrderDO expectedPurchaseOrder = createPurchaseOrder();
        PurchaseOrderDO actualPurchaseOrder = purchaseOrderDAO.getById(expectedPurchaseOrder.getId());
        Assert.assertEquals(expectedPurchaseOrder, actualPurchaseOrder);
    }

    /**
     * 测试更新采购单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_order.sql"})
    public void testUpdate() throws Exception {
        PurchaseOrderDO expectedPurchaseOrder = createPurchaseOrder();

        expectedPurchaseOrder.setRemark("修改后的备注");
        purchaseOrderDAO.update(expectedPurchaseOrder);

        PurchaseOrderDO actualPurchaseOrder = purchaseOrderDAO.getById(expectedPurchaseOrder.getId());

        Assert.assertEquals(expectedPurchaseOrder, actualPurchaseOrder);
    }

    /**
     * 测试更新采购单状态
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_order.sql"})
    public void testUpdateStatus() throws Exception {
        PurchaseOrderDO expectedPurchaseOrder = createPurchaseOrder();
        expectedPurchaseOrder.setStatus(2);

        purchaseOrderDAO.updateStatus(expectedPurchaseOrder.getId(), 2);

        PurchaseOrderDO actualPurchaseOrder = purchaseOrderDAO.getById(
                expectedPurchaseOrder.getId());

        Assert.assertEquals(expectedPurchaseOrder, actualPurchaseOrder);
    }

    /**
     * 比较两个采购单集合
     *
     * @param expectedPurchaseOrders 期望的采购单集合
     * @param actualPurchaseOrders   实际的采购单集合
     * @throws Exception
     */
    private void comparePurchaseOrder(
            Integer expectedSize,
            Map<Long, PurchaseOrderDO> expectedPurchaseOrders,
            List<PurchaseOrderDO> actualPurchaseOrders) throws Exception {
        Assert.assertEquals((int) expectedSize, actualPurchaseOrders.size());
        for (PurchaseOrderDO actualPurchaseOrder : actualPurchaseOrders) {
            PurchaseOrderDO expectedPurchaseOrder = expectedPurchaseOrders.get(
                    actualPurchaseOrder.getId());
            Assert.assertEquals(expectedPurchaseOrder, actualPurchaseOrder);
        }
    }

    /**
     * 创建采购单查询条件
     *
     * @return
     * @throws Exception
     */
    private PurchaseOrderQuery createPurchaseOrderQuery(
            Integer offset, Integer size) throws Exception {
        PurchaseOrderQuery query = new PurchaseOrderQuery();
        query.setOffset(offset);
        query.setSize(size);
        return query;
    }

    /**
     * 创建采购单集合
     *
     * @param categoryId 类目id
     * @return 采购单集合
     * @throws Exception
     */
    private Map<Long, PurchaseOrderDO> createPurchaseOrderMap(Integer count) throws Exception {
        Map<Long, PurchaseOrderDO> purchaseOrderMap = new HashMap<>();

        List<PurchaseOrderDO> purchaseOrderList = createPurchaseOrders(count);
        for (PurchaseOrderDO purchaseOrder : purchaseOrderList) {
            purchaseOrderMap.put(purchaseOrder.getId(), purchaseOrder);
        }

        return purchaseOrderMap;
    }

    /**
     * 创建采购单集合
     *
     * @param categoryId 类目id
     * @param count      采购单数量
     * @return 采购单集合
     * @throws Exception
     */
    private List<PurchaseOrderDO> createPurchaseOrders(Integer count) throws Exception {
        List<PurchaseOrderDO> purchaseOrderList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            purchaseOrderList.add(createPurchaseOrder());
        }
        return purchaseOrderList;
    }

    /**
     * 创建采购单
     *
     * @return 采购单
     * @throws Exception
     */
    private PurchaseOrderDO createPurchaseOrder() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PurchaseOrderDO purchaseOrder = new PurchaseOrderDO();
        purchaseOrder.setSupplierId(1L);
        purchaseOrder.setExpectArrivalTime(formatter.parse("2018-01-10 10:00:00"));
        purchaseOrder.setContactor("张三");
        purchaseOrder.setContactorPhoneNumber("测试电话号码");
        purchaseOrder.setContactorEmail("测试邮箱地址");
        purchaseOrder.setRemark("测试采购单");
        purchaseOrder.setPurchaser("张三");
        purchaseOrder.setStatus(1);

        purchaseOrderDAO.save(purchaseOrder);

        return purchaseOrder;
    }
}
