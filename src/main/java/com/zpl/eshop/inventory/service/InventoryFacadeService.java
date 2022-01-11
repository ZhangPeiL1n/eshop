package com.zpl.eshop.inventory.service;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;

/**
 * 库存中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 21:48
 **/
public interface InventoryFacadeService {
    /**
     * 通知库存中心，“采购入库完成”事件发生了
     *
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrderDTO);

    /**
     * 通知库存中心，“提交订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informSubmitOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知库存中心，“支付订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informPayOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知库存中心，“取消订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean cancelOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知库存中心，“退货入库”事件发生了
     * @param returnGoodsInputOrderDTO 退货入库DTO
     * @return 处理结果
     */
    Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO);

    /**
     * 查询商品 sku 的库存
     * @param goodsSkuId 商品 sku id
     * @return 库存
     */
    Long getSaleStockQuantity(Long goodsSkuId);

}
