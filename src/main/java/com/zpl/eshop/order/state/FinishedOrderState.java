package com.zpl.eshop.order.state;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 已完成状态
 *
 * @author ZhangPeiL1n
 * @date 2022/10/11 20:52
 **/
@Component
public class FinishedOrderState extends AbstractOrderState {

    @Autowired
    public FinishedOrderState(DateProvider dateProvider, OrderInfoDAO orderInfoDAO) {
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
        return OrderStatus.FINISHED;
    }

    /**
     * 能否申请退货
     *
     * @param order 订单
     * @return 能否退货
     */
    @Override
    public Boolean canApplyReturnGoods(OrderInfoDTO order) {
        return true;
    }
}
