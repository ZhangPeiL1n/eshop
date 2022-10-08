package com.zpl.eshop.order.state;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDO;
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
public class WaitForReceiveState implements OrderState {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 订单管理DAO组件
     */
    @Autowired
    private OrderInfoDAO orderInfoDAO;

    /**
     * 订单流转到当前这个状态
     *
     * @param order 订单
     */
    @Override
    public void doTransition(OrderInfoDTO order) throws Exception {
        order.setOrderStatus(OrderStatus.WAIT_FOR_RECEIVE);
        order.setGmtModified(dateProvider.getCurrentTime());
        orderInfoDAO.updateStatus(order.clone(OrderInfoDO.class));
    }

    /**
     * 判断当前状态下能否执行取消订单操作
     *
     * @param order 订单
     * @return 能否执行取消订单操作
     */
    @Override
    public Boolean canCancel(OrderInfoDTO order) throws Exception {
        return false;
    }

    /**
     * 判断订单能否执行支付操作
     *
     * @param order 订单
     * @return 能否执行支付操作
     * @throws Exception
     */
    @Override
    public Boolean canPay(OrderInfoDTO order) throws Exception {
        return false;
    }


}
