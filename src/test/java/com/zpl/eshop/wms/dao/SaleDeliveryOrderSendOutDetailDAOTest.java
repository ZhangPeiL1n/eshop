package com.zpl.eshop.wms.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderSendOutDetailDO;
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
 * 销售出库单发货明细管理DAO单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/10/21 10:53
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class SaleDeliveryOrderSendOutDetailDAOTest {

    /**
     * 销售出库单管理DAO组件
     */
    @Autowired
    private SaleDeliveryOrderSendOutDetailDAO sendOutDetailDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增商品发货明细
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_sale_delivery_order_send_out_detail.sql"})
    public void testSave() throws Exception {
        Long saleDeliveryOrderItemId = 1L;
        Long goodsAllocationStockDetailId = 1L;
        SaleDeliveryOrderSendOutDetailDO sendOutDetail = createSaleDeliveryOrderSendOutDetail(
                saleDeliveryOrderItemId, goodsAllocationStockDetailId);
        Assert.assertNotNull(sendOutDetail.getId());
        Assert.assertThat(sendOutDetail.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试查询销售出库单发货明细
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_sale_delivery_order_send_out_detail.sql"})
    public void testListBySaleDeliveryOrderId() throws Exception {
        Integer count = 10;
        Long saleDeliveryOrderItemId = 1L;
        Map<Long, SaleDeliveryOrderSendOutDetailDO> expectedSaleDeliveryOrderSendOutDetailMap =
                createSaleDeliveryOrderSendOutDetailMap(count, saleDeliveryOrderItemId);

        List<SaleDeliveryOrderSendOutDetailDO> actualSaleDeliveryOrderSendOutDetails = sendOutDetailDAO
                .listBySaleDeliveryOrderItemId(saleDeliveryOrderItemId);

        compareSaleDeliveryOrderSendOutDetails(count, expectedSaleDeliveryOrderSendOutDetailMap,
                actualSaleDeliveryOrderSendOutDetails);
    }

    /**
     * 比较销售出库单集合
     *
     * @param expectedSaleDeliveryOrderSendOutDetailMap 期望的销售出库单发货条目集合
     * @param actualSaleDeliveryOrderSendOutDetails     实际的销售出库单发货条目集合
     * @throws Exception
     */
    private void compareSaleDeliveryOrderSendOutDetails(Integer expectedSize,
                                                        Map<Long, SaleDeliveryOrderSendOutDetailDO> expectedSaleDeliveryOrderSendOutDetailMap,
                                                        List<SaleDeliveryOrderSendOutDetailDO> actualSaleDeliveryOrderSendOutDetails) throws Exception {
        Assert.assertEquals((int) expectedSize, actualSaleDeliveryOrderSendOutDetails.size());

        for (SaleDeliveryOrderSendOutDetailDO actualSaleDeliveryOrderSendOutDetail : actualSaleDeliveryOrderSendOutDetails) {
            SaleDeliveryOrderSendOutDetailDO expectedSaleDeliveryOrderSendOutDetail = expectedSaleDeliveryOrderSendOutDetailMap.get(actualSaleDeliveryOrderSendOutDetail.getId());
            Assert.assertEquals(expectedSaleDeliveryOrderSendOutDetail, actualSaleDeliveryOrderSendOutDetail);
        }
    }

    /**
     * 创建销售出库单map
     *
     * @param count 销售出库单数量
     * @return 销售出库单map
     * @throws Exception
     */
    private Map<Long, SaleDeliveryOrderSendOutDetailDO> createSaleDeliveryOrderSendOutDetailMap(Integer count,
                                                                                                Long saleDeliveryOrderItemId) throws Exception {
        Map<Long, SaleDeliveryOrderSendOutDetailDO> sendOutDetailMap = new HashMap<>();

        List<SaleDeliveryOrderSendOutDetailDO> sendOutDetails = createSaleDeliveryOrderSendOutDetails(count, saleDeliveryOrderItemId);
        for (SaleDeliveryOrderSendOutDetailDO sendOutDetail : sendOutDetails) {
            sendOutDetailMap.put(sendOutDetail.getId(), sendOutDetail);
        }

        return sendOutDetailMap;
    }

    /**
     * 创建销售出库单发货明细集合
     *
     * @param count 销售出库单发货明细数量
     * @return 销售出库单发货明细集合
     * @throws Exception
     */
    private List<SaleDeliveryOrderSendOutDetailDO> createSaleDeliveryOrderSendOutDetails(Integer count, Long saleDeliveryOrderItemId) throws Exception {
        List<SaleDeliveryOrderSendOutDetailDO> sendOutDetails = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            sendOutDetails.add(createSaleDeliveryOrderSendOutDetail(saleDeliveryOrderItemId, (long) i));
        }
        return sendOutDetails;
    }

    /**
     * 创建一个销售出库单发货明细
     *
     * @param saleDeliveryOrderItemId
     * @return
     * @throws Exception
     */
    private SaleDeliveryOrderSendOutDetailDO createSaleDeliveryOrderSendOutDetail(
            Long saleDeliveryOrderItemId, Long goodsAllocationStockDetailId) throws Exception {
        SaleDeliveryOrderSendOutDetailDO sendOutDetail = new SaleDeliveryOrderSendOutDetailDO();
        sendOutDetail.setSaleDeliveryOrderItemId(saleDeliveryOrderItemId);
        sendOutDetail.setGoodsAllocationStockDetailId(goodsAllocationStockDetailId);
        sendOutDetail.setSendOutCount(10L);
        sendOutDetail.setGmtCreate(dateProvider.getCurrentTime());
        sendOutDetail.setGmtModified(dateProvider.getCurrentTime());

        sendOutDetailDAO.save(sendOutDetail);

        return sendOutDetail;
    }
}
