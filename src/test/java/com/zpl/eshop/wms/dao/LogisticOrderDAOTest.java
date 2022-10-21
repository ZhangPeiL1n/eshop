package com.zpl.eshop.wms.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.wms.domain.LogisticOrderDO;
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

import java.util.UUID;

/**
 * 物流单管理DAO单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/10/21 11:11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class LogisticOrderDAOTest {

    /**
     * 物流单管理DAO组件
     */
    @Autowired
    private LogisticOrderDAO logisticOrderDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增物流单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_logistic_order.sql"})
    public void testSave() throws Exception {
        Long saleDeliveryOrderId = 1L;
        LogisticOrderDO logisticOrder = createLogisticOrder(saleDeliveryOrderId);
        Assert.assertNotNull(logisticOrder.getId());
        Assert.assertThat(logisticOrder.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 测试根据id查询物流单
     *
     * @throws Exception
     */
    @Test
    @Sql({"clean_logistic_order.sql"})
    public void testGetById() throws Exception {
        Long saleDeliveryOrderId = 1L;
        LogisticOrderDO expectedOrder = createLogisticOrder(saleDeliveryOrderId);
        LogisticOrderDO actualOrder = logisticOrderDAO.getBySaleDeliveryOrderId(saleDeliveryOrderId);
        Assert.assertEquals(expectedOrder, actualOrder);
    }

    private LogisticOrderDO createLogisticOrder(Long saleDeliveryOrderId) throws Exception {
        LogisticOrderDO logisticOrder = new LogisticOrderDO();

        logisticOrder.setSaleDeliveryOrderId(saleDeliveryOrderId);
        logisticOrder.setLogisticCode(UUID.randomUUID().toString().replace("-", ""));
        logisticOrder.setContent("测试物流单");
        logisticOrder.setGmtCreate(dateProvider.getCurrentTime());
        logisticOrder.setGmtModified(dateProvider.getCurrentTime());

        logisticOrderDAO.save(logisticOrder);

        return logisticOrder;
    }

}
