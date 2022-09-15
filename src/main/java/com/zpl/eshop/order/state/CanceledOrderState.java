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
}
