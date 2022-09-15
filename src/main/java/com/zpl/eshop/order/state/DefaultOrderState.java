package com.zpl.eshop.order.state;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import org.springframework.stereotype.Component;

/**
 * 默认订单状态
 *
 * @author ZhangPeiL1n
 * @date 2022/9/12 15:00
 **/
@Component
public class DefaultOrderState implements OrderState {

    /**
     * 状态流转
     *
     * @param order 订单
     */
    @Override
    public void doTransition(OrderInfoDTO order) {
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


}
