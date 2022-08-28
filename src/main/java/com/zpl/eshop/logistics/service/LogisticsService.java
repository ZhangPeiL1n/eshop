package com.zpl.eshop.logistics.service;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.membership.domain.DeliveryAddressDTO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.wms.domain.LogisticOrderDTO;

/**
 * 物流中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:20
 **/
public interface LogisticsService {

    /**
     * 计算商品 sku 的运费
     *
     * @param goodsSkuDTO     商品 sku DTO
     * @param deliveryAddress 收货地址
     * @return 商品 sku 的运费
     */
    Double calculateFreight(GoodsSkuDTO goodsSkuDTO, DeliveryAddressDTO deliveryAddress);

    /**
     * 申请物流单
     *
     * @param order 订单
     * @return 物流单
     */
    LogisticOrderDTO applyLogisticOrder(OrderInfoDTO order);
}
