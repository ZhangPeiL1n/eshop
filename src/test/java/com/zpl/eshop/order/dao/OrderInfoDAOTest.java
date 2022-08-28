package com.zpl.eshop.order.dao;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.domain.OrderInfoDO;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 订单管理DAO单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/8/28 10:48
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class OrderInfoDAOTest {

    /**
     * 订单管理DAO组件
     */
    @Autowired
    private OrderInfoDAO orderInfoDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试保存订单
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        OrderInfoDO order = createOrder();
        Assert.assertNotNull(order);
        Assert.assertThat(order.getId(), Matchers.greaterThan(0L));
    }

    /**
     * 创建订单
     *
     * @return 订单
     * @throws Exception
     */
    private OrderInfoDO createOrder() throws Exception {
        OrderInfoDO order = new OrderInfoDO();
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
        orderInfoDAO.save(order);
        return order;
    }
}
