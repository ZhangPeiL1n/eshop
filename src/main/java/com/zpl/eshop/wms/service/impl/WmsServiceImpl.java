package com.zpl.eshop.wms.service.impl;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;
import com.zpl.eshop.wms.service.WmsService;
import org.springframework.stereotype.Service;

/**
 * @author ZhangPeiL1n
 * @date 2022/5/28 12:39
 **/
@Service
public class WmsServiceImpl implements WmsService {

    /**
     * 创建采购入库单
     *
     * @param purchaseInputOrderDTO 采购入库单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean createPurchaseInputOrder(PurchaseInputOrderDTO purchaseInputOrderDTO) {
        return true;
    }

    /**
     * 创建销售出库单
     *
     * @param saleDeliveryOrderDTO 销售出库单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean createSaleDeliveryOrder(SaleDeliveryOrderDTO saleDeliveryOrderDTO) {
        return true;
    }

    /**
     * 创建退货入库单
     *
     * @param returnGoodsInputOrderDTO 退货入库单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean createReturnGoodsInputOrder(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) {
        return true;
    }

    /**
     * 通知 wms中心，“提交订单“事件发生了
     *
     * @param orderDTO 订单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 通知 wms中心，“支付订单”事件发生了
     *
     * @param orderDTO 订单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPayOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 通知 wms中心，“取消订单”事件发生了
     *
     * @param orderDTO 订单 DTO
     * @return 处理结果
     */
    @Override
    public Boolean informCancelOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }
}
