package com.zpl.eshop.order.state;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderStatus;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 退货商品待入库状态
 *
 * @author ZhangPeiL1n
 * @date 2022/10/19 21:02
 **/
@Component
public class WaitForInputReturnGoodsOrderState extends AbstractOrderState {

    @Autowired
    public WaitForInputReturnGoodsOrderState(DateProvider dateProvider, OrderInfoDAO orderInfoDAO) {
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
        return OrderStatus.WAIT_FOR_INPUT_RETURN_GOODS;
    }
}
