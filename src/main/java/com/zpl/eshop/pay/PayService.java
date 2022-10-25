package com.zpl.eshop.pay;

import com.zpl.eshop.order.domain.OrderInfoDTO;

/**
 * 支付中心接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/4 14:08
 **/
public interface PayService {

    /**
     * 获取支付二维码
     *
     * @param order 订单
     * @return 二维码
     */
    String getQrCode(OrderInfoDTO order);

}
