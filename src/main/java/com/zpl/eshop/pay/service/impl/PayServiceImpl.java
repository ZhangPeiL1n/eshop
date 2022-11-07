package com.zpl.eshop.pay.service.impl;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.pay.service.PayService;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/4 14:10
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PayServiceImpl implements PayService {

    /**
     * 获取支付二维码
     *
     * @param order 订单
     * @return 二维码
     */
    @Override
    public String getQrCode(OrderInfoDTO order) {
        return null;
    }

    /**
     * 进行退款
     *
     * @param returnGoodsInputOrder 退货入库单
     * @return 退款结果
     */
    @Override
    public Boolean refund(ReturnGoodsInputOrderDTO returnGoodsInputOrder) {
        return null;
    }
}
