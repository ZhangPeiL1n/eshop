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
public interface InventoryService {
    /**
     * 通知库存中心，“采购入库完成”事件发生了
     *
     * @param purchaseInputOrder 采购入库单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrder) throws Exception;

    /**
     * 通知库存中心，“提交订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean informSubmitOrderEvent(OrderInfoDTO order) throws Exception;

    /**
     * 通知库存中心，“支付订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean informPayOrderEvent(OrderInfoDTO order) throws Exception;

    /**
     * 通知库存中心，“取消订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean cancelOrderEvent(OrderInfoDTO order) throws Exception;

    /**
     * 通知库存中心，“退货入库”事件发生了
     *
     * @param returnGoodsInputOrder 退货入库DTO
     * @return 处理结果
     * @throws Exception
     */
    Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception;

    /**
     * 查询商品 sku 的库存
     *
     * @param goodsSkuId 商品 sku id
     * @return 库存
     * @throws Exception
     */
    Long getSaleStockQuantity(Long goodsSkuId) throws Exception;

    /**
     * 设置销售库存
     *
     * @param goodsSkuId        商品skuId
     * @param saleStockQuantity 销售库存
     * @return 设置结果
     * @throws Exception
     */
    Boolean setSaleStockQuantity(Long goodsSkuId, Long saleStockQuantity) throws Exception;

}
