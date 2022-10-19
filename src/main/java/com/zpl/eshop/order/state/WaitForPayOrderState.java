package com.zpl.eshop.order.state;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 待付款状态
 *
 * @author ZhangPeiL1n
 * @date 2022/9/12 14:43
 **/
@Component
public class WaitForPayOrderState extends AbstractOrderState {

    @Autowired
    public WaitForPayOrderState(DateProvider dateProvider, OrderInfoDAO orderInfoDAO) {
        super(dateProvider, orderInfoDAO);
    }

    /**
     * 获取待设置的订单状态
     *
     * @return 订单状态
     * @throws Exception
     */
    @Override
    protected Integer getOrderStatus() throws Exception {
        return OrderStatus.WAIT_FOR_PAY;
    }

    /**
     * 判断当前状态能否执行取消订单操作
     *
     * @param order 订单
     * @return 能否取消
     */
    @Override
    public Boolean canCancel(OrderInfoDTO order) {
        return true;
    }

    /**
     * 判断当前状态能否执行支付操作
     *
     * @param order 订单
     * @return 能否支付
     */
    @Override
    public Boolean canPay(OrderInfoDTO order) {
        return true;
    }
}
