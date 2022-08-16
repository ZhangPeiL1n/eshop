package com.zpl.eshop.wms.service;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.wms.domain.GoodsAllocationStockDetailDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderDTO;

import java.util.List;

/**
 * wms中心对外提供的接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 17:41
 **/
public interface WmsService {


    /**
     * 创建采购入库单
     *
     * @param purchaseInputOrderDTO 采购入库单 DTO
     * @return 处理结果
     */
    Boolean createPurchaseInputOrder(PurchaseInputOrderDTO purchaseInputOrderDTO);

    /**
     * 创建销售出库单
     *
     * @param saleDeliveryOrderDTO 销售出库单 DTO
     * @return 处理结果
     */
    Boolean createSaleDeliveryOrder(SaleDeliveryOrderDTO saleDeliveryOrderDTO);

    /**
     * 创建退货入库单
     *
     * @param returnGoodsInputOrderDTO 退货入库单 DTO
     * @return 处理结果
     */
    Boolean createReturnGoodsInputOrder(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO);

    /**
     * 通知 wms中心，“提交订单“事件发生了
     *
     * @param orderDTO 订单 DTO
     * @return 处理结果
     */
    Boolean informSubmitOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知 wms中心，“支付订单”事件发生了
     *
     * @param orderDTO 订单 DTO
     * @return 处理结果
     */
    Boolean informPayOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知 wms中心，“取消订单”事件发生了
     *
     * @param orderDTO 订单 DTO
     * @return 处理结果
     */
    Boolean informCancelOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 根据商品 skuId 查询货位库存明细
     *
     * @param goodsSkuId 商品sku
     * @return 货位库存明细
     */
    List<GoodsAllocationStockDetailDTO> listStockDetailsByGoodsSkuId(Long goodsSkuId);
}
