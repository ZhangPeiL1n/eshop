package com.zpl.eshop.inventory.service;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.async.*;
import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存中心service组件的单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/5/19 21:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceTest {

    /**
     * 库存中心service组件
     */
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private GoodsStockDAO goodsStockDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 库存更新消息队列
     */
    @Autowired
    private StockUpdateQueue queue;

    /**
     * 库存更新结果管理组件
     */
    @Autowired
    private StockUpdateResultManager resultManager;

    /**
     * 库存更新结果监听组件
     */
    @MockBean
    private StockUpdateObserver observer;

    /**
     * 测试通知库存中心，“采购入库完成”事件发生了
     */
    @Test
    public void testInformPurchaseInputFinished() throws Exception {
        // 准备商品skuId集合
        Long[] goodsSkuIds = new Long[]{1L, 2L};
        // 准备采购数量
        Long purchaseQuantity = 1000L;
        // 准备商品 sku 对应的销售库存 map，将每个商品 sku id 对应的销售库存放在 map 中
        Map<Long, Long> oldSaleStockQuantityMap = new HashMap<>();
        for (Long goodsSkuId : goodsSkuIds) {
            oldSaleStockQuantityMap.put(goodsSkuId, getSaleStockQuantity(goodsSkuId));
        }

        // 构造一个采购入库单，有两个条目，goodsSkuId 为 1 和 2，采购数量为 1000
        PurchaseInputOrderDTO purchaseInputOrder = createPurchaseInputOrder(purchaseQuantity, goodsSkuIds);
        inventoryService.informPurchaseInputFinished(purchaseInputOrder);


        // 断言
        for (Long goodsSkuId : goodsSkuIds) {
            Assert.assertEquals(oldSaleStockQuantityMap.get(goodsSkuId) + purchaseQuantity, (long) getSaleStockQuantity(goodsSkuId));
        }
    }

    /**
     * 测试通知库存中心，“退货入库”事件发生了
     *
     * @throws Exception
     */
    @Test
    public void testInformReturnGoodsInputFinished() throws Exception {
        // 准备两个库存数据，goodsSkuId 分别为 3 和 4
        Long[] goodsSkuIds = new Long[]{3L, 4L};
        // 销售库存
        Long oldSaleStockQuantity = 50L;
        // 已销售库存
        Long oldSaledStockQuantity = 100L;
        // 入库
        Long purchaseQuantity = 3L;

        // 准备两个库存数据
        // 这两个数据的销售库存为50，已销售库存为100
        for (Long goodsSkuId : goodsSkuIds) {
            createGoodsStock(goodsSkuId, oldSaleStockQuantity, 0L, oldSaledStockQuantity);
        }

        // 构造退货入库单，有两个商品条目，goodsSkuId 为 3 和 4
        ReturnGoodsInputOrderDTO returnGoodsInputOrder = createReturnGoodsInputOrder(purchaseQuantity, goodsSkuIds);
        inventoryService.informReturnGoodsInputFinished(returnGoodsInputOrder);

        for (Long goodSkuId : goodsSkuIds) {
            Assert.assertEquals(oldSaleStockQuantity + purchaseQuantity, (long) getSaleStockQuantity(goodSkuId));
            Assert.assertEquals(oldSaledStockQuantity - purchaseQuantity, (long) getSaledStockQuantity(goodSkuId));
        }
    }


    /**
     * 测试通知库存中心，“提交订单”事件发生了
     *
     * @throws Exception
     */
    @Test
    public void testInformSubmitOrderEvent() throws Exception {
        Long[] goodsSkuIds = new Long[]{5L, 6L};
        Long oldSaleStockQuantity = 100L;
        Long oldLockStockQuantity = 50L;
        Long oldSaledStockQuantity = 200L;
        Long purchaseQuantity = 5L;
        for (Long goodsSkuId : goodsSkuIds) {
            createGoodsStock(goodsSkuId, oldSaleStockQuantity, oldLockStockQuantity, oldSaledStockQuantity);
        }
        OrderInfoDTO order = createOrder(goodsSkuIds, purchaseQuantity);
        inventoryService.informSubmitOrderEvent(order);

        // 执行库存变更逻辑的断言
        for (Long goodsSkuId : goodsSkuIds) {
            Assert.assertEquals(oldSaleStockQuantity - purchaseQuantity, (long) getSaleStockQuantity(goodsSkuId));
            Assert.assertEquals(oldLockStockQuantity + purchaseQuantity, (long) getLockedStockQuantity(goodsSkuId));
        }
        assertAsyncOperation();
    }

    /**
     * 测试通知库存中心，“支付订单”事件发生了
     *
     * @throws Exception
     */
    @Test
    public void testInformPayOrderEvent() throws Exception {
        Long[] goodsSkuIds = new Long[]{7L, 8L};
        Long oldSaleStockQuantity = 100L;
        Long oldLockStockQuantity = 50L;
        Long oldSaledStockQuantity = 200L;
        Long purchaseQuantity = 5L;
        for (Long goodsSkuId : goodsSkuIds) {
            createGoodsStock(goodsSkuId, oldSaleStockQuantity, oldLockStockQuantity, oldSaledStockQuantity);
        }
        OrderInfoDTO order = createOrder(goodsSkuIds, purchaseQuantity);
        inventoryService.informPayOrderEvent(order);

        // 执行库存变更逻辑的断言
        for (Long goodsSkuId : goodsSkuIds) {
            Assert.assertEquals(oldLockStockQuantity - purchaseQuantity, (long) getLockedStockQuantity(goodsSkuId));
            Assert.assertEquals(oldSaledStockQuantity + purchaseQuantity, (long) getSaledStockQuantity(goodsSkuId));
        }
        assertAsyncOperation();
    }

    /**
     * 测试通知库存中心，“取消订单”事件发生了
     *
     * @throws Exception
     */
    @Test
    public void testInformCancelOrderEvent() throws Exception {
        Long[] goodsSkuIds = new Long[]{9L, 10L};
        Long oldSaleStockQuantity = 100L;
        Long oldLockStockQuantity = 50L;
        Long oldSaledStockQuantity = 200L;
        Long purchaseQuantity = 5L;
        for (Long goodsSkuId : goodsSkuIds) {
            createGoodsStock(goodsSkuId, oldSaleStockQuantity, oldLockStockQuantity, oldSaledStockQuantity);
        }
        OrderInfoDTO order = createOrder(goodsSkuIds, purchaseQuantity);
        inventoryService.informCancelOrderEvent(order);

        // 执行库存变更逻辑的断言
        for (Long goodsSkuId : goodsSkuIds) {
            Assert.assertEquals(oldSaleStockQuantity + purchaseQuantity, (long) getSaleStockQuantity(goodsSkuId));
            Assert.assertEquals(oldLockStockQuantity - purchaseQuantity, (long) getLockedStockQuantity(goodsSkuId));
        }
        assertAsyncOperation();
    }

    /**
     * 测试查询商品 sku 的库存
     */
    @Test
    public void testGetSaleStockQuantity() throws Exception {
        Long goodSkuId = 1L;
        Long saleStockQuantity = 100L;
        Long lockStockQuantity = 100L;
        Long saledStockQuantity = 100L;
        createGoodsStock(goodSkuId, saleStockQuantity, lockStockQuantity, saledStockQuantity);

        Long resultGoodsStockQuantity = inventoryService.getSaleStockQuantity(1L);
        Assert.assertEquals(saleStockQuantity, resultGoodsStockQuantity);
    }

    /**
     * 对异步操作进行断言
     */
    private void assertAsyncOperation() throws Exception {
        // 执行内存队列的断言
        Assert.assertEquals(1, (int) queue.size());

        // 模拟获取消息
        StockUpdateMessage message = queue.take();

        StockUpdateObservable observable = resultManager.getObservable(message.getId());

        StockUpdateResult result = new StockUpdateResult();
        result.setMessageId(message.getId());
        result.setResult(true);
        resultManager.inform(message.getId(), true);

        Mockito.verify(observer, Mockito.times(1)).update(observable, result);
    }

    /**
     * 构造库存数据
     *
     * @throws Exception
     */
    private void createGoodsStock(Long goodsSkuId, Long saleStockQuantity, Long lockStockQuantity, Long saledStockQuantity) throws Exception {
        GoodsStockDO stock = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);
        if (stock == null) {
            stock = new GoodsStockDO();
            stock.setGoodsSkuId(goodsSkuId);
            stock.setSaleStockQuantity(saleStockQuantity);
            stock.setLockedStockQuantity(lockStockQuantity);
            stock.setSaledStockQuantity(saledStockQuantity);
            stock.setStockStatus(saleStockQuantity > 0L ? 1 : 0);
            stock.setGmtCreate(dateProvider.getCurrentTime());
            stock.setGmtModified(dateProvider.getCurrentTime());
            goodsStockDAO.saveGoodsStock(stock);
        } else {
            stock.setSaleStockQuantity(saleStockQuantity);
            stock.setLockedStockQuantity(lockStockQuantity);
            stock.setSaledStockQuantity(saledStockQuantity);
            stock.setStockStatus(saleStockQuantity > 0L ? 1 : 0);
            goodsStockDAO.updateGoodsStock(stock);
        }
    }

    /**
     * 查询商品sku的销售库存
     *
     * @throws Exception
     */
    private Long getSaleStockQuantity(Long goodsSkuId) throws Exception {
        GoodsStockDO stock = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);
        if (stock == null) {
            return 0L;
        } else {
            return stock.getSaleStockQuantity();
        }
    }

    /**
     * 查询商品sku的锁定库存
     *
     * @throws Exception
     */
    private Long getLockedStockQuantity(Long goodsSkuId) throws Exception {
        GoodsStockDO stock = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);
        if (stock == null) {
            return 0L;
        } else {
            return stock.getLockedStockQuantity();
        }
    }

    /**
     * 查询商品sku的已销售库存
     *
     * @throws Exception
     */
    private Long getSaledStockQuantity(Long goodsSkuId) throws Exception {
        GoodsStockDO stock = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);
        if (stock == null) {
            return 0L;
        } else {
            return stock.getSaledStockQuantity();
        }
    }

    /**
     * 创建采购入库单
     *
     * @return 采购入库单
     * @throws Exception
     */
    private PurchaseInputOrderDTO createPurchaseInputOrder(Long count, Long... goodsSkuIds) throws Exception {
        PurchaseInputOrderDTO order = new PurchaseInputOrderDTO();
        order.setId(1L);
        order.setSupplierId(1L);
        order.setExpectArrivalTime(dateProvider.parse2Datetime("2022-05-19 22:08:00"));
        order.setActualArrivalTime(dateProvider.parse2Datetime("2022-05-19 22:08:00"));
        order.setPurchaseContactor("彦祖");
        order.setPurchaseContactorPhoneNumber("18623568899");
        order.setPurchaseContactorEmail("yanzu@163.com");
        order.setPurchaseOrderRemark("测试采购入库单");
        order.setPurchaser("A");
        order.setStatus(5);
        order.setGmtCreate(dateProvider.parse2Datetime("2022-05-19 00:00:00"));
        order.setGmtModified(dateProvider.parse2Datetime("2022-05-19 00:00:00"));

        List<PurchaseInputOrderItemDTO> items = new ArrayList<>();
        for (int i = 0; i < goodsSkuIds.length; i++) {
            items.add(createPurchaseInputOrderItem((long) i, goodsSkuIds[i], 1L, count));
        }
        order.setItems(items);

        items.forEach(item -> {
            List<PurchaseInputOrderPutOnItemDTO> putOnItems = new ArrayList<>();
            for (int i = 0; i < goodsSkuIds.length; i++) {
                try {
                    putOnItems.add(createPurchaseInputOrderPutOnItemDTO((long) i, (long) i, goodsSkuIds[i]));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            item.setPutOnItems(putOnItems);
        });


        return order;
    }

    /**
     * 创建采购入库单条目
     *
     * @param orderId 采购入库单id
     * @return 采购入库单条目
     * @throws Exception
     */
    private PurchaseInputOrderItemDTO createPurchaseInputOrderItem(Long itemId, Long goodsSkuId, Long orderId, Long count) throws Exception {
        PurchaseInputOrderItemDTO item = new PurchaseInputOrderItemDTO();
        item.setId(itemId);
        item.setPurchaseInputOrderId(orderId);
        item.setGoodsSkuId(goodsSkuId);
        item.setPurchaseCount(count);
        item.setPurchasePrice(88.0);
        item.setQualifiedCount(count);
        item.setArrivalCount(count);
        item.setGmtCreate(dateProvider.parse2Datetime("2022-05-19 00:00:00"));
        item.setGmtModified(dateProvider.parse2Datetime("2022-05-19 00:00:00"));
        return item;
    }

    /**
     * 创建采购入库单上架条目
     *
     * @return 采购入库单上架条目
     * @throws Exception
     */
    private PurchaseInputOrderPutOnItemDTO createPurchaseInputOrderPutOnItemDTO(Long putOnItemId, Long itemId, Long goodsSkuId) throws Exception {
        PurchaseInputOrderPutOnItemDTO item = new PurchaseInputOrderPutOnItemDTO();
        item.setId(putOnItemId);
        item.setPurchaseInputOrderItemId(itemId);
        item.setGoodsSkuId(goodsSkuId);
        item.setGoodsAllocationId(1L);
        item.setPutOnShelvesCount(999L);
        item.setGmtCreate(dateProvider.parse2Datetime("2022-05-19 00:00:00"));
        item.setGmtModified(dateProvider.parse2Datetime("2022-05-19 00:00:00"));
        return item;
    }

    /**
     * 创建退货入库单
     *
     * @return 退货入库单
     * @throws Exception
     */
    private ReturnGoodsInputOrderDTO createReturnGoodsInputOrder(Long purchaseQuantity, Long... goodsSkuIds) throws Exception {
        ReturnGoodsInputOrderDTO returnGoodsInputOrder = new ReturnGoodsInputOrderDTO();
        returnGoodsInputOrder.setId(1L);
        returnGoodsInputOrder.setUserAccountId("测试帐号id");
        returnGoodsInputOrder.setOrderId(1L);
        returnGoodsInputOrder.setOrderNo("test");
        returnGoodsInputOrder.setStatus(3);
        returnGoodsInputOrder.setConsignee("张三");
        returnGoodsInputOrder.setDeliveryAddress("测试地址");
        returnGoodsInputOrder.setConsigneeCellPhoneNumber("18633256966");
        returnGoodsInputOrder.setFreight(45.90);
        returnGoodsInputOrder.setPayType(1);
        returnGoodsInputOrder.setTotalAmount(99.00);
        returnGoodsInputOrder.setDiscountAmount(50.40);
        returnGoodsInputOrder.setCouponAmount(35.00);
        returnGoodsInputOrder.setPayableAmount(899.30);
        returnGoodsInputOrder.setInvoiceTitle("测试发票抬头");
        returnGoodsInputOrder.setTaxpayerId("测试纳税人识别号");
        returnGoodsInputOrder.setOrderComment("测试订单");
        returnGoodsInputOrder.setReturnGoodsReason(1);
        returnGoodsInputOrder.setReturnGoodsRemark("测试退货备注");
        returnGoodsInputOrder.setArrivalTime(dateProvider.parse2Datetime("2022-05-01 14:20:00"));
        returnGoodsInputOrder.setGmtCreate(dateProvider.parse2Datetime("2022-05-01 14:20:00"));
        returnGoodsInputOrder.setGmtModified(dateProvider.parse2Datetime("2022-05-01 14:20:00"));

        List<ReturnGoodsInputOrderItemDTO> items = new ArrayList<>();
        for (int i = 0; i < goodsSkuIds.length; i++) {
            items.add(createReturnGoodsInputOrderItem(1L, goodsSkuIds[i], purchaseQuantity));
        }
        returnGoodsInputOrder.setItems(items);

        items.forEach(item -> {
            List<ReturnGoodsInputOrderPutOnItemDTO> putOnItems = new ArrayList<>();
            for (int i = 0; i < goodsSkuIds.length; i++) {
                try {
                    putOnItems.add(createReturnGoodsInputOrderPutOnItemDTO((long) i, (long) i, goodsSkuIds[i]));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            item.setPutOnItems(putOnItems);
        });


        return returnGoodsInputOrder;
    }

    /**
     * 创建退货入库单条目
     *
     * @param goodsSkuId              商品skuId
     * @param returnGoodsInputOrderId 退货入库单id
     * @param purchaseQuantity        退货数量
     * @return 退货入库单条目
     */
    public ReturnGoodsInputOrderItemDTO createReturnGoodsInputOrderItem(Long returnGoodsInputOrderId, Long goodsSkuId, Long purchaseQuantity) throws Exception {
        ReturnGoodsInputOrderItemDTO item = new ReturnGoodsInputOrderItemDTO();
        item.setReturnGoodsInputOrderId(returnGoodsInputOrderId);
        item.setGoodsSkuId(goodsSkuId);
        item.setGoodsSkuCode("测试商品编号");
        item.setGoodsName("测试商品");
        item.setSaleProperties("测试销售属性");
        item.setGoodsGrossWeight(59.30);
        item.setPurchaseQuantity(purchaseQuantity);
        item.setPurchasePrice(39.30);
        item.setPromotionActivityId(1L);
        item.setGoodsLength(49.00);
        item.setGoodsWidth(29.80);
        item.setGoodsHeight(68.90);
        item.setQualifiedCount(purchaseQuantity);
        item.setArrivalCount(purchaseQuantity);
        item.setGmtCreate(dateProvider.parse2Datetime("2022-05-01 14:20:00"));
        item.setGmtModified(dateProvider.parse2Datetime("2022-05-01 14:20:00"));
        return item;
    }

    /**
     * 创建退货入库单上架条目
     *
     * @return 退货入库单上架条目
     * @throws Exception
     */
    private ReturnGoodsInputOrderPutOnItemDTO createReturnGoodsInputOrderPutOnItemDTO(Long putOnItemId, Long itemId, Long goodsSkuId) throws Exception {
        ReturnGoodsInputOrderPutOnItemDTO item = new ReturnGoodsInputOrderPutOnItemDTO();
        item.setId(putOnItemId);
        item.setReturnGoodsInputOrderItemId(itemId);
        item.setGoodsSkuId(goodsSkuId);
        item.setGoodsAllocationId(1L);
        item.setPutOnShelvesCount(999L);
        item.setGmtCreate(dateProvider.parse2Datetime("2022-05-19 00:00:00"));
        item.setGmtModified(dateProvider.parse2Datetime("2022-05-19 00:00:00"));
        return item;
    }

    /**
     * 构造订单
     *
     * @return
     */
    private OrderInfoDTO createOrder(Long[] goodsSkuIds, Long purchaseQuantity) throws Exception {
        OrderInfoDTO order = new OrderInfoDTO();
        order.setId(1L);
        order.setUserAccountId(133221333123111L);
        order.setUsername("测试用户名");
        order.setOrderNo("test");
        order.setOrderStatus(1);
        order.setConsignee("张三");
        order.setDeliveryAddress("测试地址");
        order.setConsigneeCellPhoneNumber("18633256966");
        order.setFreight(45.90);
        order.setPayType(1);
        order.setTotalAmount(99.00);
        order.setDiscountAmount(50.40);
        order.setCouponAmount(35.00);
        order.setPayableAmount(899.30);
        order.setInvoiceTitle("测试发票抬头");
        order.setTaxpayerId("测试纳税人识别号");
        order.setOrderComment("测试订单");
        order.setPublishComment(1);
        order.setConfirmReceiptTime(dateProvider.parse2Datetime("2022-05-01 14:20:00"));
        order.setGmtCreate(dateProvider.parse2Datetime("2022-05-01 14:20:00"));
        order.setGmtModified(dateProvider.parse2Datetime("2022-05-01 14:20:00"));

        List<OrderItemDTO> items = new ArrayList<>();
        for (int i = 0; i < goodsSkuIds.length; i++) {
            items.add(createOrderItem((long) i, 1L, goodsSkuIds[i], purchaseQuantity));
        }
        order.setOrderItems(items);
        return order;
    }

    /**
     * 创建订单条目
     *
     * @param itemId           条目id
     * @param orderId          订单id
     * @param goodsSkuId       商品skuId
     * @param purchaseQuantity 购买数量
     * @return 订单条目
     */
    public OrderItemDTO createOrderItem(Long itemId, Long orderId, Long goodsSkuId, Long purchaseQuantity) throws Exception {
        OrderItemDTO item = new OrderItemDTO();
        item.setId(itemId);
        item.setOrderInfoId(orderId);
        item.setGoodsId(1L);
        item.setGoodsSkuId(goodsSkuId);
        item.setGoodsSkuCode("测试商品编号");
        item.setGoodsName("测试商品");
        item.setSaleProperties("测试销售属性");
        item.setGoodsGrossWeight(59.30);
        item.setPurchaseQuantity(purchaseQuantity);
        item.setPurchasePrice(39.30);
        item.setPromotionActivityId(1L);
        item.setGoodsLength(49.00);
        item.setGoodsWidth(29.80);
        item.setGoodsHeight(68.90);
        item.setGmtCreate(dateProvider.parse2Datetime("2022-05-01 14:20:00"));
        item.setGmtModified(dateProvider.parse2Datetime("2022-05-01 14:20:00"));
        return item;
    }
}
