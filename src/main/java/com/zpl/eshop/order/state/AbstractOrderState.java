package com.zpl.eshop.order.state;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.domain.OrderInfoDTO;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/19 21:19
 **/
public abstract class AbstractOrderState implements OrderState {

    /**
     * 日期辅助组件
     */
    protected DateProvider dateProvider;

    /**
     * 订单管理DAO组件
     */
    protected OrderInfoDAO orderInfoDAO;

    public AbstractOrderState(DateProvider dateProvider, OrderInfoDAO orderInfoDAO) {
        this.dateProvider = dateProvider;
        this.orderInfoDAO = orderInfoDAO;
    }

    /**
     * 订单流转到当前这个状态
     *
     * @param order 订单
     */
    @Override
    public void doTransition(OrderInfoDTO order) throws Exception {
        order.setOrderStatus(getOrderStatus());
        order.setGmtModified(dateProvider.getCurrentTime());
        orderInfoDAO.updateStatus(order.clone(OrderInfoDO.class));
    }

    /**
     * 获取待设置的订单状态
     *
     * @return 订单状态
     * @throws Exception
     */
    protected abstract Integer getOrderStatus() throws Exception;

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
