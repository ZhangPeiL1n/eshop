package com.zpl.eshop.order.state;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 完成退货入库状态
 *
 * @author ZhangPeiL1n
 * @date 2022/10/19 21:06
 **/
@Component
public class FinishedInputReturnGoodsOrderState extends AbstractOrderState {

    @Autowired
    public FinishedInputReturnGoodsOrderState(DateProvider dateProvider, OrderInfoDAO orderInfoDAO) {
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
        return OrderStatus.FINISHED_INPUT_RETURN_GOODS;
    }
}
