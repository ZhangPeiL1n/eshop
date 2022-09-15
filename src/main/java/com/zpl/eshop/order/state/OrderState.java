package com.zpl.eshop.order.state;

import com.zpl.eshop.order.domain.OrderInfoDTO;

/**
 * 订单状态
 *
 * @author ZhangPeiL1n
 * @date 2022/9/12 14:39
 **/
public interface OrderState {

    /**
     * 状态流转
     *
     * @param order 订单
     */
    void doTransition(OrderInfoDTO order) throws Exception;

    /**
     * 判断当前状态能否执行取消订单操作
     *
     * @param order 订单
     * @return 能否取消
     */
    Boolean canCancel(OrderInfoDTO order);
}
