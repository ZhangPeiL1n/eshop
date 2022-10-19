package com.zpl.eshop.order.state;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 待收货状态
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 19:27
 **/
@Component
public class WaitForReceiveState extends AbstractOrderState {

    @Autowired
    public WaitForReceiveState(DateProvider dateProvider, OrderInfoDAO orderInfoDAO) {
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
        return OrderStatus.WAIT_FOR_RECEIVE;
    }

    /**
     * 判断当前订单能否执行确认收货操作
     *
     * @param order 订单
     * @return 能否确认收货
     */
    @Override
    public Boolean canConfirmReceipt(OrderInfoDTO order) {
        return true;
    }

}
