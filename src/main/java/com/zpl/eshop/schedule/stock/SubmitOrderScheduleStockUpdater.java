package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDetailDAO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsStockDAO;
import com.zpl.eshop.schedule.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 提交订单更新组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 23:03
 **/
@Component
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SubmitOrderScheduleStockUpdater extends AbstractScheduleStockUpdater {

    /**
     * 商品库存管理的DAO组件
     */
    @Autowired
    private ScheduleGoodsStockDAO goodsStockDAO;

    /**
     * 货位库存管理的DAO组件
     */
    @Autowired
    private ScheduleGoodsAllocationStockDAO goodsAllocationStockDAO;

    /**
     * 货位库存明细管理的DAO组件
     */
    @Autowired
    private ScheduleGoodsAllocationStockDetailDAO stockDetailDAO;

    /**
     * 销售出库调度结果
     */
    private SaleDeliveryScheduleResult scheduleResult;

    /**
     * 更新商品库存
     */
    @Override
    protected void updateGoodsStock() throws Exception {
        OrderItemDTO orderItem = scheduleResult.getOrderItem();
        ScheduleGoodsStockDO goodsStock = goodsStockDAO.getBySkuId(orderItem.getGoodsSkuId());
        goodsStock.setAvailableStockQuantity(goodsStock.getAvailableStockQuantity()
                - orderItem.getPurchaseQuantity());
        goodsStock.setLockedStockQuantity(goodsStock.getLockedStockQuantity() +
                orderItem.getPurchaseQuantity());
        goodsStockDAO.update(goodsStock);
    }

    /**
     * 更新货位库存
     */
    @Override
    protected void updateGoodsAllocationStock() throws Exception {
        List<ScheduleOrderPickingItemDTO> pickingItems = scheduleResult.getPickingItems();
        for (ScheduleOrderPickingItemDTO pickingItem : pickingItems) {
            ScheduleGoodsAllocationStockDO goodsAllocationStock = goodsAllocationStockDAO.getBySkuId(
                    pickingItem.getGoodsAllocationId(), pickingItem.getGoodsSkuId());
            goodsAllocationStock.setAvailableStockQuantity(goodsAllocationStock.getAvailableStockQuantity()
                    - pickingItem.getPickingCount());
            goodsAllocationStock.setLockedStockQuantity(goodsAllocationStock.getLockedStockQuantity()
                    + pickingItem.getPickingCount());
            goodsAllocationStockDAO.update(goodsAllocationStock);
        }
    }

    /**
     * 更新货位库存明细
     */
    @Override
    protected void updateGoodsAllocationStockDetail() throws Exception {
        List<ScheduleOrderSendOutDetailDTO> sendOutDetails = scheduleResult.getSendOutDetails();
        for (ScheduleOrderSendOutDetailDTO sendOutDetail : sendOutDetails) {
            ScheduleGoodsAllocationStockDetailDO stockDetail = stockDetailDAO.getById(
                    sendOutDetail.getGoodsAllocationStockDetailId());
            stockDetail.setCurrentStockQuantity(stockDetail.getCurrentStockQuantity()
                    - sendOutDetail.getSendOutCount());
            stockDetail.setLockedStockQuantity(stockDetail.getLockedStockQuantity()
                    + sendOutDetail.getSendOutCount());
            stockDetailDAO.update(stockDetail);
        }
    }

    /**
     * 设置库存更新组件需要的参数
     */
    @Override
    public void setParameter(Object parameter) {
        this.scheduleResult = (SaleDeliveryScheduleResult) parameter;
    }
}