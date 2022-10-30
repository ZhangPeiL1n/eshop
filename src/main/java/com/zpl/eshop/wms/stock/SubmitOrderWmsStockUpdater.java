package com.zpl.eshop.wms.stock;

import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.schedule.domain.SaleDeliveryScheduleResult;
import com.zpl.eshop.schedule.domain.ScheduleOrderPickingItemDTO;
import com.zpl.eshop.schedule.domain.ScheduleOrderSendOutDetailDTO;
import com.zpl.eshop.wms.dao.GoodsAllocationStockDetailDAO;
import com.zpl.eshop.wms.dao.WmsGoodsAllocationStockDAO;
import com.zpl.eshop.wms.dao.WmsGoodsStockDAO;
import com.zpl.eshop.wms.domain.GoodsAllocationStockDetailDO;
import com.zpl.eshop.wms.domain.WmsGoodsAllocationStockDO;
import com.zpl.eshop.wms.domain.WmsGoodsStockDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 提交订单库存更新组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/30 17:46
 **/
@Component
@Scope("prototype")
public class SubmitOrderWmsStockUpdater extends AbstractWmsStockUpdater {

    /**
     * 调度结果
     */
    private SaleDeliveryScheduleResult scheduleResult;

    /**
     * 商品库存管理的DAO组件
     */
    @Autowired
    private WmsGoodsStockDAO goodsStockDAO;

    /**
     * 货位库存管理的DAO组件
     */
    @Autowired
    private WmsGoodsAllocationStockDAO goodsAllocationStockDAO;

    /**
     * 货位库存明细管理的DAO组件
     */
    @Autowired
    private GoodsAllocationStockDetailDAO stockDetailDAO;

    /**
     * 更新商品库存
     */
    @Override
    protected void updateGoodsStock() throws Exception {
        OrderItemDTO orderItem = scheduleResult.getOrderItem();

        WmsGoodsStockDO goodsStock = goodsStockDAO.getBySkuId(orderItem.getGoodsSkuId());

        Long availableStockQuantity = goodsStock.getAvailableStockQuantity()
                - orderItem.getPurchaseQuantity();
        goodsStock.setAvailableStockQuantity(availableStockQuantity);

        Long lockedStockQuantity = goodsStock.getLockedStockQuantity()
                + orderItem.getPurchaseQuantity();
        goodsStock.setLockedStockQuantity(lockedStockQuantity);

        goodsStockDAO.update(goodsStock);
    }

    /**
     * 更新货位库存
     */
    @Override
    protected void updateGoodsAllocationStock() throws Exception {
        List<ScheduleOrderPickingItemDTO> pickingItems = scheduleResult.getPickingItems();

        for (ScheduleOrderPickingItemDTO pickingItem : pickingItems) {
            WmsGoodsAllocationStockDO goodsAllocationStock = goodsAllocationStockDAO
                    .getBySkuId(pickingItem.getGoodsAllocationId(), pickingItem.getGoodsSkuId());

            Long availableStockQuantity = goodsAllocationStock.getAvailableStockQuantity()
                    - pickingItem.getPickingCount();
            goodsAllocationStock.setAvailableStockQuantity(availableStockQuantity);

            Long lockedStockQuantity = goodsAllocationStock.getLockedStockQuantity()
                    + pickingItem.getPickingCount();
            goodsAllocationStock.setLockedStockQuantity(lockedStockQuantity);

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
            GoodsAllocationStockDetailDO stockDetail = stockDetailDAO.getById(
                    sendOutDetail.getGoodsAllocationStockDetailId());

            Long currentStockQuantity = stockDetail.getCurrentStockQuantity()
                    - sendOutDetail.getSendOutCount();
            stockDetail.setCurrentStockQuantity(currentStockQuantity);

            Long lockedStockQuantity = stockDetail.getLockedStockQuantity()
                    + sendOutDetail.getSendOutCount();
            stockDetail.setLockedStockQuantity(lockedStockQuantity);

            stockDetailDAO.update(stockDetail);
        }
    }

    /**
     * 设置需要的参数
     */
    @Override
    public void setParameter(Object parameter) {
        this.scheduleResult = (SaleDeliveryScheduleResult) parameter;
    }

}
