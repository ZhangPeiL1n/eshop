package com.zpl.eshop.finance.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.finance.domain.PurchaseSettlementOrderDO;
import com.zpl.eshop.finance.domain.PurchaseSettlementOrderQuery;
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
 * 采购结算单管理DAO单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/10/20 23:00
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class PurchaseSettlementOrderDAOTest {

    /**
     * 日期辅助组件
     */
    @MockBean
    private DateProvider dateProvider;

    /**
     * 采购结算单管理DAO组件
     */
    @Autowired
    private PurchaseSettlementOrderDAO purchaseSettlementOrderDAO;

    /**
     * 初始化
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Mockito.when(dateProvider.getCurrentTime()).thenReturn(formatter.parse("2018-01-10 10:00:00"));
    }

    /**
     * 测试新建采购结算单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_settlement_order.sql"})
    public void testSave() throws Exception {
        Long purchaseOrderId = 1L;
        Long purchaseInputOrderId = 1L;
        Long supplierId = 1L;
        PurchaseSettlementOrderDO expectedPurchaseSettlementOrder = createPurchaseSettlementOrder(
                purchaseOrderId, purchaseInputOrderId, supplierId);
        Assert.assertNotNull(expectedPurchaseSettlementOrder.getId());
        Assert.assertThat(expectedPurchaseSettlementOrder.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试分页查询采购结算单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_settlement_order.sql"})
    public void testListByPage() throws Exception {
        Integer count = 30;
        Long supplierId = 1L;
        Map<Long, PurchaseSettlementOrderDO> expectedPurchaseSettlementOrders =
                createPurchaseSettlementOrderMap(count, supplierId);

        Integer offset = 10;
        Integer size = 10;
        PurchaseSettlementOrderQuery query = createPurchaseSettlementOrderQuery(offset, size);
        List<PurchaseSettlementOrderDO> actualPurchaseSettlementOrders = purchaseSettlementOrderDAO.listByPage(query);

        comparePurchaseSettlementOrder(size, expectedPurchaseSettlementOrders, actualPurchaseSettlementOrders);
    }

    /**
     * 测试根据id查询采购结算单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_settlement_order.sql"})
    public void testGetById() throws Exception {
        Long purchaseOrderId = 1L;
        Long purchaseInputOrderId = 1L;
        Long supplierId = 1L;
        PurchaseSettlementOrderDO expectedPurchaseSettlementOrder = createPurchaseSettlementOrder(
                purchaseOrderId, purchaseInputOrderId, supplierId);

        PurchaseSettlementOrderDO actualPurchaseSettlementOrder = purchaseSettlementOrderDAO.getById(
                expectedPurchaseSettlementOrder.getId());

        Assert.assertEquals(expectedPurchaseSettlementOrder, actualPurchaseSettlementOrder);
    }

    /**
     * 测试更新采购结算单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_settlement_order.sql"})
    public void testUpdate() throws Exception {
        Long purchaseOrderId = 1L;
        Long purchaseInputOrderId = 1L;
        Long supplierId = 1L;
        PurchaseSettlementOrderDO expectedPurchaseSettlementOrder = createPurchaseSettlementOrder(
                purchaseOrderId, purchaseInputOrderId, supplierId);

        expectedPurchaseSettlementOrder.setTotalSettlementAmount(1200.0);
        purchaseSettlementOrderDAO.update(expectedPurchaseSettlementOrder);

        PurchaseSettlementOrderDO actualPurchaseSettlementOrder = purchaseSettlementOrderDAO.getById(
                expectedPurchaseSettlementOrder.getId());

        Assert.assertEquals(expectedPurchaseSettlementOrder, actualPurchaseSettlementOrder);
    }

    /**
     * 测试更新采购结算单状态
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_settlement_order.sql"})
    public void testUpdateStatus() throws Exception {
        Long purchaseOrderId = 1L;
        Long purchaseInputOrderId = 1L;
        Long supplierId = 1L;
        PurchaseSettlementOrderDO expectedPurchaseSettlementOrder = createPurchaseSettlementOrder(
                purchaseOrderId, purchaseInputOrderId, supplierId);

        expectedPurchaseSettlementOrder.setStatus(2);
        purchaseSettlementOrderDAO.updateStatus(expectedPurchaseSettlementOrder);

        PurchaseSettlementOrderDO actualPurchaseSettlementOrder = purchaseSettlementOrderDAO.getById(
                expectedPurchaseSettlementOrder.getId());

        Assert.assertEquals(expectedPurchaseSettlementOrder, actualPurchaseSettlementOrder);
    }

    /**
     * 测试更新采购结算单状态
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_settlement_order.sql"})
    public void testUpdateStatus2() throws Exception {
        Long purchaseOrderId = 1L;
        Long purchaseInputOrderId = 1L;
        Long supplierId = 1L;
        PurchaseSettlementOrderDO expectedPurchaseSettlementOrder = createPurchaseSettlementOrder(
                purchaseOrderId, purchaseInputOrderId, supplierId);
        expectedPurchaseSettlementOrder.setStatus(2);

        purchaseSettlementOrderDAO.updateStatus(expectedPurchaseSettlementOrder.getId(), 2);

        PurchaseSettlementOrderDO actualPurchaseSettlementOrder = purchaseSettlementOrderDAO.getById(
                expectedPurchaseSettlementOrder.getId());

        Assert.assertEquals(expectedPurchaseSettlementOrder, actualPurchaseSettlementOrder);
    }

    /**
     * 测试查询指定供应商在指定时间范围内的已完成的采购结算单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_purchase_settlement_order.sql"})
    public void testListFinishedBySettlementPeriod() throws Exception {
        Integer count = 10;
        Long supplierId = 1L;
        Map<Long, PurchaseSettlementOrderDO> expectedPurchaseSettlementOrders =
                createPurchaseSettlementOrderMap(count, supplierId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = formatter.parse("2018-01-01 10:00:00");
        Date endTime = formatter.parse("2018-01-31 10:00:00");

        List<PurchaseSettlementOrderDO> actualPurchaseSettlementOrders = purchaseSettlementOrderDAO
                .listFinishedBySettlementPeriod(supplierId, startTime, endTime);

        comparePurchaseSettlementOrder(count, expectedPurchaseSettlementOrders, actualPurchaseSettlementOrders);
    }

    /**
     * 比较两个采购结算单集合
     *
     * @param expectedPurchaseSettlementOrders 期望的采购结算单集合
     * @param actualPurchaseSettlementOrders   实际的采购结算单集合
     * @throws Exception
     */
    private void comparePurchaseSettlementOrder(
            Integer expectedSize,
            Map<Long, PurchaseSettlementOrderDO> expectedPurchaseSettlementOrders,
            List<PurchaseSettlementOrderDO> actualPurchaseSettlementOrders) throws Exception {
        Assert.assertEquals((int) expectedSize, actualPurchaseSettlementOrders.size());
        for (PurchaseSettlementOrderDO actualPurchaseSettlementOrder : actualPurchaseSettlementOrders) {
            PurchaseSettlementOrderDO expectedPurchaseSettlementOrder = expectedPurchaseSettlementOrders.get(
                    actualPurchaseSettlementOrder.getId());
            Assert.assertEquals(expectedPurchaseSettlementOrder, actualPurchaseSettlementOrder);
        }
    }

    /**
     * 创建采购结算单查询条件
     *
     * @return
     * @throws Exception
     */
    private PurchaseSettlementOrderQuery createPurchaseSettlementOrderQuery(Integer offset, Integer size) throws Exception {
        PurchaseSettlementOrderQuery query = new PurchaseSettlementOrderQuery();
        query.setOffset(offset);
        query.setSize(size);
        return query;
    }

    /**
     * 创建采购结算单集合
     *
     * @param supplierId 供应商id
     * @return 采购结算单集合
     * @throws Exception
     */
    private Map<Long, PurchaseSettlementOrderDO> createPurchaseSettlementOrderMap(Integer count, Long supplierId) throws Exception {
        Map<Long, PurchaseSettlementOrderDO> purchaseSettlementOrderMap = new HashMap<>();

        List<PurchaseSettlementOrderDO> purchaseSettlementOrderList = createPurchaseSettlementOrders(count, supplierId);
        for (PurchaseSettlementOrderDO purchaseSettlementOrder : purchaseSettlementOrderList) {
            purchaseSettlementOrderMap.put(purchaseSettlementOrder.getId(), purchaseSettlementOrder);
        }

        return purchaseSettlementOrderMap;
    }

    /**
     * 创建采购结算单集合
     *
     * @param supplierId 供应商id
     * @param count      采购结算单数量
     * @return 采购结算单集合
     * @throws Exception
     */
    private List<PurchaseSettlementOrderDO> createPurchaseSettlementOrders(Integer count, Long supplierId) throws Exception {
        List<PurchaseSettlementOrderDO> purchaseSettlementOrderList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            purchaseSettlementOrderList.add(createPurchaseSettlementOrder((long) i, (long) i, supplierId));
        }
        return purchaseSettlementOrderList;
    }

    /**
     * 创建采购结算单
     *
     * @return 采购结算单
     * @throws Exception
     */
    private PurchaseSettlementOrderDO createPurchaseSettlementOrder(
            Long purchaseOrderId, Long purchaseInputOrderId, Long supplierId) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PurchaseSettlementOrderDO purchaseSettlementOrder = new PurchaseSettlementOrderDO();
        purchaseSettlementOrder.setPurchaseOrderId(purchaseOrderId);
        purchaseSettlementOrder.setPurchaseInputOrderId(purchaseInputOrderId);
        purchaseSettlementOrder.setSupplierId(supplierId);
        purchaseSettlementOrder.setExpectArrivalTime(formatter.parse("2018-01-10 10:00:00"));
        purchaseSettlementOrder.setActualArrivalTime(formatter.parse("2018-01-10 10:00:00"));
        purchaseSettlementOrder.setPurchaseContactor("张三");
        purchaseSettlementOrder.setPurchaseContactorPhoneNumber("测试电话号码");
        purchaseSettlementOrder.setPurchaseContactorEmail("测试邮箱地址");
        purchaseSettlementOrder.setPurchaseOrderRemark("测试采购单");
        purchaseSettlementOrder.setPurchaser("张三");
        purchaseSettlementOrder.setStatus(3);
        purchaseSettlementOrder.setTotalSettlementAmount(1000.0);

        purchaseSettlementOrderDAO.save(purchaseSettlementOrder);

        return purchaseSettlementOrder;
    }

}