package com.zpl.eshop.inventory.service.impl;

import com.zpl.eshop.inventory.async.StockUpdateMessage;
import com.zpl.eshop.inventory.async.StockUpdateQueue;
import com.zpl.eshop.inventory.async.StockUpdateResultManager;
import com.zpl.eshop.inventory.constant.GoodsStockUpdateOperation;
import com.zpl.eshop.inventory.service.InventoryFacadeService;
import com.zpl.eshop.inventory.updater.*;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    private PurchaseInputStockUpdaterFactory purchaseInputStockUpdateCommandFactory;

    /**
     * 退货入库库存更新命令工厂
     */
    @Autowired
    private ReturnInputStockUpdaterFactory returnGoodsInputStockUpdateCommandFactory;

    /**
     * 提交订单更新库存命令工厂
     */
    @Autowired
    private SubmitOrderStockUpdaterFactory submitOrderStockUpdaterFactory;

    /**
     * 支付订单更新库存命令工厂
     */
    @Autowired
    private PayOrderStockUpdaterFactory payOrderStockUpdaterFactory;

    /**
     * 取消订单更新库存命令工厂
     */
    @Autowired
    private CancelOrderStockUpdaterFactory cancelOrderStockUpdaterFactory;

    /**
     * 商品库存更新消息队列
     */
    @Autowired
    private StockUpdateQueue stockUpdateQueue;

    /**
     * 商品库存更新管理组件
     */
    @Autowired
    private StockUpdateResultManager stockUpdateResultManager;

    /**
     * 通知库存中心，“采购入库完成”事件发生了
     *
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrderDTO) {
        try {
            StockUpdater stockUpdater = purchaseInputStockUpdateCommandFactory.create(purchaseInputOrderDTO);
            stockUpdater.updateGoodsStock();
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 通知库存中心，“提交订单”事件发生了
     *
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderInfoDTO orderInfoDTO) {
        try {
            // 更新本地库存
            StockUpdater stockUpdater = submitOrderStockUpdaterFactory.create(orderInfoDTO);
            stockUpdater.updateGoodsStock();

            // 发送异步消息到内存队列
            StockUpdateMessage stockUpdateMessage = new StockUpdateMessage();
            stockUpdateMessage.setId(UUID.randomUUID().toString().replace("-", ""));
            stockUpdateMessage.setOperation(GoodsStockUpdateOperation.SUBMIT_ORDER);
            stockUpdateMessage.setParameter(orderInfoDTO);
            stockUpdateQueue.put(stockUpdateMessage);

            // 监听异步处理结果
            stockUpdateResultManager.observe(stockUpdateMessage.getId());
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 通知库存中心，“支付订单”事件发生了
     *
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPayOrderEvent(OrderInfoDTO orderInfoDTO) {
        try {
            // 更新本地库存
            StockUpdater stockUpdater = payOrderStockUpdaterFactory.create(orderInfoDTO);
            stockUpdater.updateGoodsStock();

            // 发送异步消息到内存队列
            StockUpdateMessage stockUpdateMessage = new StockUpdateMessage();
            stockUpdateMessage.setId(UUID.randomUUID().toString().replace("-", ""));
            stockUpdateMessage.setOperation(GoodsStockUpdateOperation.PAY_ORDER);
            stockUpdateMessage.setParameter(orderInfoDTO);
            stockUpdateQueue.put(stockUpdateMessage);

            // 监听异步处理结果
            stockUpdateResultManager.observe(stockUpdateMessage.getId());
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 通知库存中心，“取消订单”事件发生了
     *
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean cancelOrderEvent(OrderInfoDTO orderInfoDTO) {
        try {
            // 更新本地库存
            StockUpdater stockUpdater = cancelOrderStockUpdaterFactory.create(orderInfoDTO);
            stockUpdater.updateGoodsStock();
            // 发送异步消息到内存队列
            StockUpdateMessage stockUpdateMessage = new StockUpdateMessage();
            stockUpdateMessage.setId(UUID.randomUUID().toString().replace("-", ""));
            stockUpdateMessage.setOperation(GoodsStockUpdateOperation.CANCEL_ORDER);
            stockUpdateMessage.setParameter(orderInfoDTO);
            stockUpdateQueue.put(stockUpdateMessage);
            // 监听异步处理结果
            stockUpdateResultManager.observe(stockUpdateMessage.getId());
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
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
            StockUpdater stockUpdater = returnGoodsInputStockUpdateCommandFactory.create(returnGoodsInputOrderDTO);
            stockUpdater.updateGoodsStock();
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
