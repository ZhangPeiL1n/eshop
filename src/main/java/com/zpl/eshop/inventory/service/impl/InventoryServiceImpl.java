package com.zpl.eshop.inventory.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.async.StockUpdateMessage;
import com.zpl.eshop.inventory.async.StockUpdateQueue;
import com.zpl.eshop.inventory.async.StockUpdateResultManager;
import com.zpl.eshop.inventory.constant.GoodsStockUpdateOperation;
import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
import com.zpl.eshop.inventory.service.InventoryService;
import com.zpl.eshop.inventory.stock.*;
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
public class InventoryServiceImpl implements InventoryService {

    private final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

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
     * 商品库存管理模块DAO组件
     */
    @Autowired
    private GoodsStockDAO goodsStockDAO;

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
     * @param purchaseInputOrder 采购入库单DTO
     * @return 处理结果
     * @throws Exception
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
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
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
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
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
     * @param order 订单DTO
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean informCancelOrderEvent(OrderInfoDTO orderInfoDTO) {
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
     * @param returnGoodsInputOrder 退货入库DTO
     * @return 处理结果
     * @throws Exception
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
     * @throws Exception
     */
    @Override
    public Long getSaleStockQuantity(Long goodsSkuId) {
        try {
            GoodsStockDO goodsStockDO = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);
            if (goodsStockDO == null) {
                return 0L;
            }
            return goodsStockDO.getSaleStockQuantity();
        } catch (Exception e) {
            logger.error("error", e);
            return 0L;
        }
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
    public Boolean setSaleStockQuantity(Long goodsSkuId, Long saleStockQuantity) {
        try {
            GoodsStockDO goodsStock = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);
            if (goodsStock == null) {
                goodsStock = new GoodsStockDO();
                goodsStock.setGoodsSkuId(goodsSkuId);
                goodsStock.setSaleStockQuantity(saleStockQuantity);
                goodsStock.setLockedStockQuantity(0L);
                goodsStock.setSaledStockQuantity(0L);
                goodsStock.setStockStatus(saleStockQuantity > 0L ? 1 : 0);
                goodsStock.setGmtCreate(dateProvider.getCurrentTime());
                goodsStock.setGmtModified(dateProvider.getCurrentTime());
                goodsStockDAO.saveGoodsStock(goodsStock);
            } else {
                goodsStock.setSaleStockQuantity(goodsStock.getSaleStockQuantity() + saleStockQuantity);
                goodsStockDAO.updateGoodsStock(goodsStock);
            }
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
