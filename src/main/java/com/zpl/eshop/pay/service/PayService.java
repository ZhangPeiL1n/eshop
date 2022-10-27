package com.zpl.eshop.pay.service;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;

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

    /**
     * 进行退款
     *
     * @param returnGoodsInputOrder 退货入库单
     * @return 退款结果
     */
    Boolean refund(ReturnGoodsInputOrderDTO returnGoodsInputOrder);

}
