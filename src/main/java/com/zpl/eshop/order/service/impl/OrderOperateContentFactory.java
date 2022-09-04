package com.zpl.eshop.order.service.impl;

import com.zpl.eshop.order.constant.OrderOperateType;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import org.springframework.stereotype.Component;

/**
 * 订单操作内容工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/9/4 15:33
 **/
@Component
public class OrderOperateContentFactory {

    public String getOperateContent(OrderInfoDTO order, Integer operateType) {
        if (OrderOperateType.CREATE_ORDER.equals(operateType)) {
            return "完成订单创建，订单编号为：" + order.getOrderNo();
        }
        return "";
    }
}
