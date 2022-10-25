package com.zpl.eshop.order.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.domain.OrderItemDO;
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

import java.util.*;

/**
 * 订单条目管理DAO单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/8/28 16:26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class OrderItemDAOTest {

    /**
     * 订单条目DAO
     */
    @Autowired
    private OrderItemDAO orderItemDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试保存订单条目
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_order_item.sql"})
    public void testSave() throws Exception {
        Long orderInfoId = 1L;
        OrderItemDO orderItem = createOrderItem(orderInfoId);
        Assert.assertNotNull(orderItem);
        Assert.assertThat(orderItem.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试查询订单条目
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_order_item.sql"})
    public void testListByOrderInfoId() throws Exception {
        Integer count = 10;
        Long orderInfoId = 1L;
        Map<Long, OrderItemDO> expectedOrderItemMap = createOrderItemMap(count, orderInfoId);

        List<OrderItemDO> actualOrderItems = orderItemDAO.listByOrderInfoId(orderInfoId);

        compareOrderItems(count, expectedOrderItemMap, actualOrderItems);
    }

    /**
     * 比较订单集合
     *
     * @param expectedOrderItemMap 期望的订单集合
     * @param actualOrderItems     实际的订单集合
     */
    private void compareOrderItems(Integer expectedSize, Map<Long, OrderItemDO> expectedOrderItemMap, List<OrderItemDO> actualOrderItems) {
        Assert.assertEquals((int) expectedSize, actualOrderItems.size());

        for (OrderItemDO actualOrderItem : actualOrderItems) {
            OrderItemDO expectedOrderItem = expectedOrderItemMap.get(actualOrderItem.getId());
            Assert.assertEquals(expectedOrderItem, actualOrderItem);
        }
    }

    /**
     * 创建订单map
     *
     * @param count 订单数量
     * @return 订单map
     * @throws Exception
     */
    private Map<Long, OrderItemDO> createOrderItemMap(Integer count, Long orderInfoId) throws Exception {
        Map<Long, OrderItemDO> orderItemMap = new HashMap<>();

        List<OrderItemDO> orderItems = createOrderItems(count, orderInfoId);
        for (OrderItemDO orderItem : orderItems) {
            orderItemMap.put(orderItem.getId(), orderItem);
        }
        return orderItemMap;
    }

    /**
     * 创建订单条目集合
     *
     * @param count 订单条目数量
     * @return 订单条目集合
     * @throws Exception
     */
    private List<OrderItemDO> createOrderItems(Integer count, Long orderInfoId) throws Exception {
        List<OrderItemDO> orderItems = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            orderItems.add(createOrderItem(orderInfoId));
        }
        return orderItems;
    }

    private OrderItemDO createOrderItem(Long orderInfoId) throws Exception {
        OrderItemDO item = new OrderItemDO();
        item.setOrderInfoId(orderInfoId);
        item.setGoodsId(1L);
        item.setGoodsSkuId(1L);
        item.setGoodsSkuCode(UUID.randomUUID().toString().replace("-", ""));
        item.setGoodsName("测试商品");
        item.setSaleProperties("测试销售属性");
        item.setGoodsGrossWeight(56.0);
        item.setPurchaseQuantity(5L);
        item.setPurchasePrice(4.0);
        item.setPromotionActivityId(null);
        item.setGoodsLength(12.3);
        item.setGoodsWidth(45.6);
        item.setGoodsHeight(78.9);
        item.setGmtCreate(dateProvider.getCurrentTime());
        item.setGmtModified(dateProvider.getCurrentTime());
        orderItemDAO.save(item);
        return item;
    }
}
