package com.zpl.eshop.inventory.service.impl;

import com.zpl.eshop.inventory.service.InventoryService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import org.springframework.stereotype.Service;

/**
 * 库存中心对外接口Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/8 22:56
 **/
@Service
public class InventoryServiceImpl implements InventoryService {
    /**
     * 通知库存中心，“采购入库完成”事件发生了
     *
     * @param purchaseInputOrder 采购入库单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrder) throws Exception {
        return true;
    }

    /**
     * 通知库存中心，“提交订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderInfoDTO order) throws Exception {
        return true;
    }

    /**
     * 通知库存中心，“支付订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informPayOrderEvent(OrderInfoDTO order) throws Exception {
        return true;
    }

    /**
     * 通知库存中心，“取消订单”事件发生了
     *
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean cancelOrderEvent(OrderInfoDTO order) throws Exception {
        return true;
    }

    /**
     * 通知库存中心，“退货入库”事件发生了
     *
     * @param returnGoodsInputOrder 退货入库DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrder) throws Exception {
        return true;
    }

    /**
     * 查询商品 sku 的库存
     *
     * @param goodsSkuId 商品 sku id
     * @return 库存
     * @throws Exception
     */
    @Override
    public Long getSaleStockQuantity(Long goodsSkuId) throws Exception {
        return 133221333L;
    }

    /**
     * 设置销售库存
     *
     * @param goodsSkuId        商品skuId
     * @param saleStockQuantity 销售库存
     * @return 设置结果
     * @throws Exception
     */
    @Override
    public Boolean setSaleStockQuantity(Long goodsSkuId, Long saleStockQuantity) throws Exception {
        return true;
    }
}
