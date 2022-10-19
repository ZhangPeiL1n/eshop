package com.zpl.eshop.order.state;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 完成退货退款状态
 *
 * @author ZhangPeiL1n
 * @date 2022/10/19 21:07
 **/
@Component
public class FinishedRefundOrderState extends AbstractOrderState {

    @Autowired
    public FinishedRefundOrderState(DateProvider dateProvider, OrderInfoDAO orderInfoDAO) {
        super(dateProvider, orderInfoDAO);
    }

    @Override
    protected Integer getOrderStatus() throws Exception {
        return OrderStatus.FINISHED_REFUND;
    }
}
