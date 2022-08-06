package com.zpl.eshop.schedule.service;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.purchase.domain.PurchaseOrderDTO;
import com.zpl.eshop.purchase.domain.PurchaseOrderItemDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDTO;
import com.zpl.eshop.wms.service.WmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private DateProvider dateProvider;

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
        order.setGmtCreateTime(dateProvider.getCurrentTime());
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
        inputOrder.setPurchaseContactPhoneNumber("133221333123111");
        inputOrder.setPurchaseContactEmail("337845818@163.com");
        inputOrder.setPurchaseOrderComment("测试采购单");
        // inputOrder.setPurchaser("李四");


        int itemCount = 5;
        List<PurchaseInputOrderItemDTO> items = new ArrayList<>(itemCount);

        for (int i = 0; i < itemCount; i++) {
            items.add(createPurchaseInputOrderItem((long) i));
        }
        inputOrder.setPurchaseInputOrderItemDTOs(items);
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
