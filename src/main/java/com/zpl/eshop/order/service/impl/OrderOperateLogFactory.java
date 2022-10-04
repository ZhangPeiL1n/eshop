package com.zpl.eshop.order.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.constant.OrderOperateType;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderOperateLogDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单操作内容工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/9/4 15:33
 **/
@Component
public class OrderOperateLogFactory {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    public OrderOperateLogDO get(OrderInfoDTO order, Integer operateType) throws Exception {
        String operateContent = null;
        if (OrderOperateType.CREATE_ORDER.equals(operateType)) {
            operateContent = "完成订单创建，订单编号为：" + order.getOrderNo();
        } else if (OrderOperateType.MANUAL_CANCEL_ORDER.equals((operateType))) {
            operateContent = "手动取消订单，订单编号为：" + order.getOrderNo();
        } else if (OrderOperateType.AUTO_CANCEL_ORDER.equals(operateType)) {
            operateContent = "自动取消订单，订单编号为：" + order.getOrderNo();
        } else if (OrderOperateType.PAY_ORDER.equals(operateType)) {
            operateContent = "支付订单，订单编号为：" + order.getOrderNo() + "，支付金额为：" + order.getPayableAmount();
        }
        return create(order, operateType, operateContent);
    }

    /**
     * 创建订单日志
     *
     * @param operateType    操作类型
     * @param operateContent 操作内容
     * @return 订单操作日志
     */
    private OrderOperateLogDO create(OrderInfoDTO order, Integer operateType, String operateContent) throws Exception {
        OrderOperateLogDO log = new OrderOperateLogDO();
        log.setOrderInfoId(order.getId());
        log.setOperateType(operateType);
        log.setOperateContent(operateContent);
        log.setGmtCreate(dateProvider.getCurrentTime());
        log.setGmtModified(dateProvider.getCurrentTime());
        return log;
    }
}
