package com.zpl.eshop.schedule.service;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderItemDTO;
import com.zpl.eshop.wms.constant.SaleDeliveryOrderStatus;
import com.zpl.eshop.wms.domain.*;
import com.zpl.eshop.wms.service.WmsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 调度中心对外接口Service组件单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/8/5 21:40
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceTest {

    /**
     * 调度中心service组件
     */
    @Autowired
    private ScheduleService scheduleService;

    /**
     * wms中心service组件
     */
    @MockBean
    private WmsService wmsService;

    /**
     * 日期辅助组件
     */
    @MockBean
    private DateProvider dateProvider;

    /**
     * 初始化
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Mockito.when(dateProvider.getCurrentTime()).thenReturn(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
    }

    /**
     * 测试调度采购入库
     *
     * @throws Exception
     */
    @Test
    public void testSchedulePurchaseInput() throws Exception {
        PurchaseOrderDTO purchaseOrder = createPurchaseOrder();
        PurchaseInputOrderDTO purchaseInputOrder = createPurchaseInputOrder();

        scheduleService.schedulePurchaseInput(purchaseOrder);

        Mockito.verify(wmsService, Mockito.times(1)).createPurchaseInputOrder(purchaseInputOrder);
    }

    /**
     * 测试调度销售出库
     *
     * @throws Exception
     */
    @Test
    public void testScheduleSaleDelivery() throws Exception {
        OrderInfoDTO order = createOrder();

        for (OrderItemDTO orderItem : order.getOrderItems()) {
            mockStockDetails(orderItem.getGoodsSkuId());
        }

        SaleDeliveryOrderDTO expectedSaleDeliveryOrder = createExpectedSaleDeliveryOrder(order);

        scheduleService.scheduleSaleDelivery(order);

        Mockito.verify(wmsService, Mockito.times(1)).createSaleDeliveryOrder(expectedSaleDeliveryOrder);

    }

    /**
     * 自行创建一个期望的销售出库单
     *
     * @param order 订单
     * @return 销售出库单
     * @throws Exception
     */
    private SaleDeliveryOrderDTO createExpectedSaleDeliveryOrder(OrderInfoDTO order) throws Exception {
        SaleDeliveryOrderDTO saleDeliveryOrder = new SaleDeliveryOrderDTO();
        saleDeliveryOrder.setOrderId(order.getId());
        order.clone(saleDeliveryOrder);

        ArrayList<SaleDeliveryOrderItemDTO> saleDeliveryOrderItems = new ArrayList<>();
        saleDeliveryOrder.setSaleDeliveryOrderItems(saleDeliveryOrderItems);
        for (OrderItemDTO orderItem : order.getOrderItems()) {
            SaleDeliveryOrderItemDTO saleDeliveryOrderItem = new SaleDeliveryOrderItemDTO();
            ArrayList<SaleDeliveryOrderPickingItemDTO> pickingItems = new ArrayList<>();

            SaleDeliveryOrderPickingItemDTO pickingItem1 = new SaleDeliveryOrderPickingItemDTO();
            pickingItem1.setGoodsAllocationId(1L);
            pickingItem1.setPickingCount(100L);
            pickingItem1.setGmtCreate(dateProvider.getCurrentTime());
            pickingItem1.setGmtModified(dateProvider.getCurrentTime());
            pickingItems.add(pickingItem1);

            SaleDeliveryOrderPickingItemDTO pickingItem2 = new SaleDeliveryOrderPickingItemDTO();
            pickingItem2.setGoodsAllocationId(2L);
            pickingItem2.setPickingCount(20L);
            pickingItem2.setGmtCreate(dateProvider.getCurrentTime());
            pickingItem2.setGmtModified(dateProvider.getCurrentTime());
            pickingItems.add(pickingItem2);

            saleDeliveryOrderItem.setPickingItems(pickingItems);

            ArrayList<SaleDeliveryOrderSendOutDetailDTO> sendOutDetails = new ArrayList<>();
            SaleDeliveryOrderSendOutDetailDTO sendOutDetail1 = new SaleDeliveryOrderSendOutDetailDTO();
            sendOutDetail1.setGoodsAllocationStockDetailId(1L);
            sendOutDetail1.setSendOutCount(40L);
            sendOutDetail1.setGmtCreate(dateProvider.getCurrentTime());
            sendOutDetail1.setGmtModified(dateProvider.getCurrentTime());
            sendOutDetails.add(sendOutDetail1);

            SaleDeliveryOrderSendOutDetailDTO sendOutDetail2 = new SaleDeliveryOrderSendOutDetailDTO();
            sendOutDetail2.setGoodsAllocationStockDetailId(2L);
            sendOutDetail2.setSendOutCount(60L);
            sendOutDetail2.setGmtCreate(dateProvider.getCurrentTime());
            sendOutDetail2.setGmtModified(dateProvider.getCurrentTime());
            sendOutDetails.add(sendOutDetail2);

            SaleDeliveryOrderSendOutDetailDTO sendOutDetail3 = new SaleDeliveryOrderSendOutDetailDTO();
            sendOutDetail3.setGoodsAllocationStockDetailId(3L);
            sendOutDetail3.setSendOutCount(20L);
            sendOutDetail3.setGmtCreate(dateProvider.getCurrentTime());
            sendOutDetail3.setGmtModified(dateProvider.getCurrentTime());
            sendOutDetails.add(sendOutDetail3);

            saleDeliveryOrderItem.setSendOutItems(sendOutDetails);
            saleDeliveryOrderItem.setGmtCreate(dateProvider.getCurrentTime());
            saleDeliveryOrderItem.setGmtModified(dateProvider.getCurrentTime());
            orderItem.clone(saleDeliveryOrderItem);

            saleDeliveryOrderItems.add(saleDeliveryOrderItem);
        }

        saleDeliveryOrder.setStatus(SaleDeliveryOrderStatus.EDITING);
        saleDeliveryOrder.setGmtCreate(dateProvider.getCurrentTime());
        saleDeliveryOrder.setGmtModified(dateProvider.getCurrentTime());
        return saleDeliveryOrder;
    }

    private void mockStockDetails(Long goodsSkuId) throws Exception {
        List<GoodsAllocationStockDetailDTO> stockDetails = new ArrayList<>();
        GoodsAllocationStockDetailDTO stockDetail1 = new GoodsAllocationStockDetailDTO();
        stockDetail1.setId(1L);
        stockDetail1.setGoodsSkuId(goodsSkuId);
        stockDetail1.setGoodsAllocationId(1L);
        stockDetail1.setPutOnTime(dateProvider.getCurrentTime());
        stockDetail1.setPutOnQuantity(40L);
        stockDetail1.setCurrentStockQuantity(40L);
        stockDetail1.setGmtCreate(dateProvider.getCurrentTime());
        stockDetail1.setGmtModified(dateProvider.getCurrentTime());
        stockDetails.add(stockDetail1);

        GoodsAllocationStockDetailDTO stockDetail2 = new GoodsAllocationStockDetailDTO();
        stockDetail2.setId(2L);
        stockDetail2.setGoodsSkuId(goodsSkuId);
        stockDetail2.setGoodsAllocationId(1L);
        stockDetail2.setPutOnTime(dateProvider.getCurrentTime());
        stockDetail2.setPutOnQuantity(60L);
        stockDetail2.setCurrentStockQuantity(60L);
        stockDetail2.setGmtCreate(dateProvider.getCurrentTime());
        stockDetail2.setGmtModified(dateProvider.getCurrentTime());
        stockDetails.add(stockDetail2);

        GoodsAllocationStockDetailDTO stockDetail3 = new GoodsAllocationStockDetailDTO();
        stockDetail3.setId(3L);
        stockDetail3.setGoodsSkuId(goodsSkuId);
        stockDetail3.setGoodsAllocationId(2L);
        stockDetail3.setPutOnTime(dateProvider.getCurrentTime());
        stockDetail3.setPutOnQuantity(150L);
        stockDetail3.setCurrentStockQuantity(150L);
        stockDetail3.setGmtCreate(dateProvider.getCurrentTime());
        stockDetail3.setGmtModified(dateProvider.getCurrentTime());
        stockDetails.add(stockDetail3);

        Mockito.when(wmsService.listStockDetailsByGoodsSkuId(goodsSkuId)).thenReturn(stockDetails);
    }

    /**
     * 创建订单
     *
     * @return
     * @throws Exception
     */
    private OrderInfoDTO createOrder() throws Exception {
        OrderInfoDTO order = new OrderInfoDTO();
        order.setId(1L);
        order.setUserAccountId(1L);
        order.setUsername("zhangsan");
        order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        order.setOrderStatus(1);
        order.setConsignee("张三");
        order.setDeliveryAddress("运城市");
        order.setConsigneeCellPhoneNumber("18612345678");
        order.setFreight(10.8);
        order.setPayType(1);
        order.setTotalAmount(100.00);
        order.setDiscountAmount(1.8);
        order.setCouponAmount(10.0);
        order.setPayableAmount(99.0);
        order.setInvoiceTitle("xxxx");
        order.setTaxpayerId(UUID.randomUUID().toString().replace("-", ""));
        order.setOrderComment("测试订单");
        order.setPublishComment(0);
        order.setGmtCreate(dateProvider.getCurrentTime());
        order.setGmtModified(dateProvider.getCurrentTime());
        order.setOrderItems(new ArrayList<>());

        for (int i = 0; i < 2; i++) {
            order.getOrderItems().add(createOrderItem(1L, (long) i, (long) i));
        }
        return order;
    }

    /**
     * 创建订单条目
     *
     * @param orderInfoId
     * @param orderItemId
     * @return
     * @throws Exception
     */
    private OrderItemDTO createOrderItem(Long orderInfoId, Long orderItemId, Long goodsSkuId) throws Exception {
        OrderItemDTO item = new OrderItemDTO();
        item.setId(orderItemId);
        item.setOrderInfoId(orderInfoId);
        item.setGoodsId(1L);
        item.setGoodsSkuId(goodsSkuId);
        item.setGoodsSkuCode(UUID.randomUUID().toString().replace("-", ""));
        item.setGoodsName("测试商品");
        item.setSaleProperties("测试销售属性");
        item.setGoodsGrossWeight(56.0);
        item.setPurchaseQuantity(120L);
        item.setPurchasePrice(4.0);
        item.setPromotionActivityId(null);
        item.setGoodsLength(12.3);
        item.setGoodsWidth(45.6);
        item.setGoodsHeight(78.9);
        item.setGmtCreate(dateProvider.getCurrentTime());
        item.setGmtModified(dateProvider.getCurrentTime());
        return item;
    }

    /**
     * 创建采购单
     *
     * @return 采购单
     * @throws Exception
     */
    private PurchaseOrderDTO createPurchaseOrder() throws Exception {
        Long purchaseOrderId = 1L;
        PurchaseOrderDTO order = new PurchaseOrderDTO();
        order.setId(purchaseOrderId);
        order.setSupplierId(1L);
        order.setExpectArrivalTime(dateProvider.parse2Datetime("2022-08-05 10:00:00"));
        order.setContactor("彦祖");
        order.setContactorPhoneNumber("133221333123111");
        order.setContactorEmail("337845818@163.com");
        order.setRemark("测试采购单");
        order.setStatus(3);
        order.setGmtCreate(dateProvider.getCurrentTime());
        order.setGmtModified(dateProvider.getCurrentTime());

        int itemCount = 5;
        List<PurchaseOrderItemDTO> items = new ArrayList<>(itemCount);

        for (int i = 0; i < itemCount; i++) {
            items.add(createPurchaseOrderItem((long) i, purchaseOrderId, (long) i));
        }
        order.setItems(items);
        return order;
    }

    /**
     * 创建采购单条目
     *
     * @param purchaseOrderId
     * @return 采购单条目
     * @throws Exception
     */
    private PurchaseOrderItemDTO createPurchaseOrderItem(Long itemId, Long purchaseOrderId, Long goodsSkuId) throws Exception {
        PurchaseOrderItemDTO item = new PurchaseOrderItemDTO();
        item.setId(itemId);
        item.setPurchaseOrderId(purchaseOrderId);
        item.setGoodsSkuId(goodsSkuId);
        item.setPurchasePrice(999.0);
        item.setPurchaseCount(999L);
        item.setGmtCreate(dateProvider.getCurrentTime());
        item.setGmtModified(dateProvider.getCurrentTime());
        return item;
    }

    /**
     * 创建采购入库单
     */
    private PurchaseInputOrderDTO createPurchaseInputOrder() throws Exception {
        PurchaseInputOrderDTO inputOrder = new PurchaseInputOrderDTO();
        inputOrder.setSupplierId(1L);
        inputOrder.setExpectArrivalTime(dateProvider.parse2Datetime("2022-08-05 10:00:00"));
        inputOrder.setPurchaseContactor("彦祖");
        inputOrder.setPurchaseContactorPhoneNumber("133221333123111");
        inputOrder.setPurchaseContactorEmail("337845818@163.com");
        inputOrder.setPurchaseOrderRemark("测试采购单");
        // inputOrder.setPurchaser("李四");


        int itemCount = 5;
        List<PurchaseInputOrderItemDTO> items = new ArrayList<>(itemCount);

        for (int i = 0; i < itemCount; i++) {
            items.add(createPurchaseInputOrderItem((long) i));
        }
        inputOrder.setItems(items);
        return inputOrder;
    }

    /**
     * 创建采购单入库单条目
     *
     * @return 采购单条目
     * @throws Exception
     */
    private PurchaseInputOrderItemDTO createPurchaseInputOrderItem(Long goodsSkuId) throws Exception {
        PurchaseInputOrderItemDTO item = new PurchaseInputOrderItemDTO();
        item.setGoodsSkuId(goodsSkuId);
        item.setPurchasePrice(999.0);
        item.setPurchaseCount(999L);
        return item;
    }
}
