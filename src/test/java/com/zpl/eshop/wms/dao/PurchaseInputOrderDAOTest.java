package com.zpl.eshop.wms.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.constant.PurchaseInputOrderStatus;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderQuery;
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
 * 采购入库单DAO单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/10/9 16:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class PurchaseInputOrderDAOTest {
    /**
     * 日期辅助组件
     */
    @MockBean
    private DateProvider dateProvider;
    /**
     * 采购入库单管理DAO组件
     */
    @Autowired
    private PurchaseInputOrderDAO purchaseInputOrderDAO;

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
     * 测试新建采购入库单
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        Long purchaseOrderId = 1L;
        PurchaseInputOrderDO expectedPurchaseInputOrder = createPurchaseInputOrder(purchaseOrderId);
        Assert.assertNotNull(expectedPurchaseInputOrder.getId());
        Assert.assertThat(expectedPurchaseInputOrder.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试分页查询采购入库单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_input_order.sql"})
    public void testListByPage() throws Exception {
        Integer count = 30;
        Map<Long, PurchaseInputOrderDO> expectedPurchaseInputOrders =
                createPurchaseInputOrderMap(count);

        Integer offset = 10;
        Integer size = 10;
        PurchaseInputOrderQuery query = createPurchaseInputOrderQuery(offset, size);
        List<PurchaseInputOrderDO> actualPurchaseInputOrders = purchaseInputOrderDAO.listByPage(query);

        comparePurchaseInputOrder(size, expectedPurchaseInputOrders, actualPurchaseInputOrders);
    }

    /**
     * 测试根据id查询采购入库单
     *
     * @throws Exception
     */
    @Test
    public void testGetById() throws Exception {
        Long purchaseOrderId = 1L;
        PurchaseInputOrderDO expectedPurchaseInputOrder = createPurchaseInputOrder(purchaseOrderId);

        PurchaseInputOrderDO actualPurchaseInputOrder = purchaseInputOrderDAO.getById(
                expectedPurchaseInputOrder.getId());

        Assert.assertEquals(expectedPurchaseInputOrder, actualPurchaseInputOrder);
    }

    /**
     * 测试更新采购入库单
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        Long purchaseOrderId = 1L;
        PurchaseInputOrderDO expectedPurchaseInputOrder = createPurchaseInputOrder(purchaseOrderId);

        expectedPurchaseInputOrder.setActualArrivalTime(dateProvider.getCurrentTime());
        purchaseInputOrderDAO.update(expectedPurchaseInputOrder);

        PurchaseInputOrderDO actualPurchaseInputOrder = purchaseInputOrderDAO.getById(
                expectedPurchaseInputOrder.getId());

        Assert.assertEquals(expectedPurchaseInputOrder, actualPurchaseInputOrder);
    }

    /**
     * 测试更新采购入库单状态
     *
     * @throws Exception
     */
    @Test
    public void testUpdateStatus() throws Exception {
        Long purchaseOrderId = 1L;
        PurchaseInputOrderDO expectedPurchaseInputOrder = createPurchaseInputOrder(purchaseOrderId);

        expectedPurchaseInputOrder.setStatus(2);
        purchaseInputOrderDAO.updateStatus(expectedPurchaseInputOrder);

        PurchaseInputOrderDO actualPurchaseInputOrder = purchaseInputOrderDAO.getById(
                expectedPurchaseInputOrder.getId());

        Assert.assertEquals(expectedPurchaseInputOrder, actualPurchaseInputOrder);
    }

    /**
     * 测试更新采购入库单状态
     *
     * @throws Exception
     */
    @Test
    public void testUpdateStatus2() throws Exception {
        Long purchaseOrderId = 1L;
        PurchaseInputOrderDO expectedPurchaseInputOrder = createPurchaseInputOrder(purchaseOrderId);
        expectedPurchaseInputOrder.setStatus(2);

        purchaseInputOrderDAO.updateStatus(expectedPurchaseInputOrder.getId(), 2);

        PurchaseInputOrderDO actualPurchaseInputOrder = purchaseInputOrderDAO.getById(
                expectedPurchaseInputOrder.getId());

        Assert.assertEquals(expectedPurchaseInputOrder, actualPurchaseInputOrder);
    }

    /**
     * 比较两个采购入库单集合
     *
     * @param expectedPurchaseInputOrders 期望的采购入库单集合
     * @param actualPurchaseInputOrders   实际的采购入库单集合
     * @throws Exception
     */
    private void comparePurchaseInputOrder(
            Integer expectedSize,
            Map<Long, PurchaseInputOrderDO> expectedPurchaseInputOrders,
            List<PurchaseInputOrderDO> actualPurchaseInputOrders) throws Exception {
        Assert.assertEquals((int) expectedSize, actualPurchaseInputOrders.size());
        for (PurchaseInputOrderDO actualPurchaseInputOrder : actualPurchaseInputOrders) {
            PurchaseInputOrderDO expectedPurchaseInputOrder = expectedPurchaseInputOrders.get(
                    actualPurchaseInputOrder.getId());
            Assert.assertEquals(expectedPurchaseInputOrder, actualPurchaseInputOrder);
        }
    }

    /**
     * 创建采购入库单查询条件
     *
     * @return
     * @throws Exception
     */
    private PurchaseInputOrderQuery createPurchaseInputOrderQuery(
            Integer offset, Integer size) throws Exception {
        PurchaseInputOrderQuery query = new PurchaseInputOrderQuery();
        query.setOffset(offset);
        query.setSize(size);
        return query;
    }

    /**
     * 创建采购入库单集合
     *
     * @return 采购入库单集合
     * @throws Exception
     */
    private Map<Long, PurchaseInputOrderDO> createPurchaseInputOrderMap(Integer count) throws Exception {
        Map<Long, PurchaseInputOrderDO> purchaseInputOrderMap = new HashMap<Long, PurchaseInputOrderDO>();

        List<PurchaseInputOrderDO> purchaseInputOrderList = createPurchaseInputOrders(count);
        for (PurchaseInputOrderDO purchaseInputOrder : purchaseInputOrderList) {
            purchaseInputOrderMap.put(purchaseInputOrder.getId(), purchaseInputOrder);
        }

        return purchaseInputOrderMap;
    }

    /**
     * 创建采购入库单集合
     *
     * @param count 采购入库单数量
     * @return 采购入库单集合
     * @throws Exception
     */
    private List<PurchaseInputOrderDO> createPurchaseInputOrders(Integer count) throws Exception {
        List<PurchaseInputOrderDO> purchaseInputOrderList = new ArrayList<PurchaseInputOrderDO>();
        for (int i = 0; i < count; i++) {
            purchaseInputOrderList.add(createPurchaseInputOrder((long) i));
        }
        return purchaseInputOrderList;
    }

    /**
     * 创建采购入库单
     *
     * @return 采购入库单
     * @throws Exception
     */
    private PurchaseInputOrderDO createPurchaseInputOrder(
            Long purchaseOrderId) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PurchaseInputOrderDO purchaseInputOrder = new PurchaseInputOrderDO();
        purchaseInputOrder.setPurchaseOrderId(purchaseOrderId);
        purchaseInputOrder.setSupplierId(1L);
        purchaseInputOrder.setExpectArrivalTime(formatter.parse("2018-01-10 10:00:00"));
        purchaseInputOrder.setPurchaseContactor("张三");
        purchaseInputOrder.setPurchaseContactorPhoneNumber("测试电话号码");
        purchaseInputOrder.setPurchaseContactorEmail("测试邮箱地址");
        purchaseInputOrder.setPurchaseOrderRemark("测试采购单");
        purchaseInputOrder.setPurchaser("张三");
        purchaseInputOrder.setStatus(PurchaseInputOrderStatus.EDITING);
        purchaseInputOrder.setGmtCreate(dateProvider.getCurrentTime());
        purchaseInputOrder.setGmtModified(dateProvider.getCurrentTime());

        purchaseInputOrderDAO.save(purchaseInputOrder);

        return purchaseInputOrder;
    }
}
