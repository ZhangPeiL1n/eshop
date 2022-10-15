package com.zpl.eshop.order.state;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 已取消状态
 *
 * @author ZhangPeiL1n
 * @date 2022/9/12 14:55
 **/
@Component
public class CanceledOrderState implements OrderState {

    /**
     * 商品管理DAO组件
     */
    @Autowired
    private OrderInfoDAO orderInfoDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 状态流转
     *
     * @param order 订单
     */
    @Override
    public void doTransition(OrderInfoDTO order) throws Exception {
        order.setOrderStatus(OrderStatus.CANCELED);
        order.setGmtModified(dateProvider.getCurrentTime());
        orderInfoDAO.updateStatus(order.clone(OrderInfoDO.class));
    }

    /**
     * 判断当前状态能否执行取消订单操作
     *
     * @param order 订单
     * @return 能否取消
     */
    @Override
    public Boolean canCancel(OrderInfoDTO order) {
        return false;
    }

    /**
     * 判断当前状态能否执行支付操作
     *
     * @param order 订单
     * @return 能否支付
     */
    @Override
    public Boolean canPay(OrderInfoDTO order) {
        return false;
    }

    /**
     * 判断当前订单能否执行确认收货操作
     *
     * @param order 订单
     * @return 能否确认收货
     */
    @Override
    public Boolean canConfirmReceipt(OrderInfoDTO order) {
        return false;
    }

    /**
     * 能否申请退货
     *
     * @param order 订单
     * @return 能否退货
     */
    @Override
    public Boolean canApplyReturnGoods(OrderInfoDTO order) {
        return false;
    }
}
