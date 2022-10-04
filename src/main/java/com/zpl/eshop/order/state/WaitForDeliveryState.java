package com.zpl.eshop.order.state;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 待发货状态
 *
 * @author ZhangPeiL1n
 * @date 2022/10/4 14:40
 **/
@Component
public class WaitForDeliveryState implements OrderState {

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

    @Override
    public void doTransition(OrderInfoDTO order) throws Exception {
        order.setOrderStatus(OrderStatus.WAIT_FOR_DELIVERY);
        order.setGmtModified(dateProvider.getCurrentTime());
        orderInfoDAO.updateStatus(order.clone(OrderInfoDO.class));
    }

    @Override
    public Boolean canCancel(OrderInfoDTO order) {
        return false;
    }

    @Override
    public Boolean canPay(OrderInfoDTO order) {
        return false;
    }
}
