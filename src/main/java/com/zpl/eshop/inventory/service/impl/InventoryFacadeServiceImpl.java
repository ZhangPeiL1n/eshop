package com.zpl.eshop.inventory.service.impl;

import com.zpl.eshop.inventory.command.GoodsStockUpdateCommand;
import com.zpl.eshop.inventory.command.PurchaseInputStockUpdateCommandFactory;
import com.zpl.eshop.inventory.command.ReturnGoodsInputStockUpdateCommandFactory;
import com.zpl.eshop.inventory.service.InventoryFacadeService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 库存中心对外提供接口的service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/29 21:55
 **/
@Service
public class InventoryFacadeServiceImpl implements InventoryFacadeService {

    private final Logger logger = LoggerFactory.getLogger(InventoryFacadeServiceImpl.class);

    /**
     * 采购入库库存更新命令工厂
     */
    @Autowired
    private PurchaseInputStockUpdateCommandFactory purchaseInputStockUpdateCommandFactory;

    /**
     * 退货入库库存更新命令工厂
     */
    @Autowired
    private ReturnGoodsInputStockUpdateCommandFactory returnGoodsInputStockUpdateCommandFactory;

    /**
     * 通知库存中心，“采购入库完成”事件发生了
     *
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrderDTO) {
        try {
            GoodsStockUpdateCommand goodsStockUpdateCommand = purchaseInputStockUpdateCommandFactory.create(purchaseInputOrderDTO);
            goodsStockUpdateCommand.updateGoodsStock();
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 通知库存中心，“提交订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 通知库存中心，“支付订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPayOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 通知库存中心，“取消订单”事件发生了
     *
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean cancelOrderEvent(OrderInfoDTO orderDTO) {
        return true;
    }

    /**
     * 通知库存中心，“退货入库”事件发生了
     *
     * @param returnGoodsInputOrderDTO 退货入库DTO
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) {
        try {
            GoodsStockUpdateCommand goodsStockUpdateCommand = returnGoodsInputStockUpdateCommandFactory.create(returnGoodsInputOrderDTO);
            goodsStockUpdateCommand.updateGoodsStock();
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 查询商品 sku 的库存
     *
     * @param goodsSkuId 商品 sku id
     * @return 库存
     */
    @Override
    public Long getSaleStockQuantity(Long goodsSkuId) {
        return 133221333L;
    }
}
