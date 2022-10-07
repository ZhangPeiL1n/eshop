package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDetailDAO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsStockDAO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDetailDO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsStockDO;
import com.zpl.eshop.wms.domain.GoodsAllocationStockDetailDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderPutOnItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 采购入库库存更新组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 23:03
 **/
@Component
@Scope("prototype")
public class PurchaseInputScheduleStockUpdater extends AbstractScheduleStockUpdater {

    /**
     * 采购入库单
     */
    private PurchaseInputOrderDTO purchaseInputOrder;

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
     * 更新商品库存
     *
     * @throws Exception
     */
    @Override
    protected void updateGoodsStock() throws Exception {
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItems = purchaseInputOrder.getItems();
        for (PurchaseInputOrderItemDTO purchaseInputOrderItem : purchaseInputOrderItems) {
            ScheduleGoodsStockDO goodsStock = goodsStockDAO.getBySkuId(purchaseInputOrderItem.getGoodsSkuId());
            goodsStock.setAvailableStockQuantity(
                    goodsStock.getAvailableStockQuantity() + purchaseInputOrderItem.getArrivalCount());
            goodsStockDAO.update(goodsStock);
        }
    }

    /**
     * 更新货位库存
     *
     * @throws Exception
     */
    @Override
    protected void updateGoodsAllocationStock() throws Exception {
        List<PurchaseInputOrderItemDTO> items = purchaseInputOrder.getItems();
        for (PurchaseInputOrderItemDTO item : items) {
            for (PurchaseInputOrderPutOnItemDTO putOnItem : item.getPutOnItems()) {
                ScheduleGoodsAllocationStockDO goodsAllocationStock = goodsAllocationStockDAO.getBySkuId(
                        putOnItem.getGoodsAllocationId(), putOnItem.getGoodsSkuId());
                goodsAllocationStock.setAvailableStockQuantity(
                        goodsAllocationStock.getAvailableStockQuantity() + putOnItem.getPutOnShelvesCount());
                goodsAllocationStockDAO.update(goodsAllocationStock);
            }
        }
    }

    /**
     * 更新货位库存明细
     */
    @Override
    protected void updateGoodsAllocationStockDetail() throws Exception {
        List<PurchaseInputOrderItemDTO> items = purchaseInputOrder.getItems();
        for (PurchaseInputOrderItemDTO item : items) {
            for (GoodsAllocationStockDetailDTO stockDetail : item.getStockDetails()) {
                stockDetailDAO.save(stockDetail.clone(ScheduleGoodsAllocationStockDetailDO.class));
            }
        }

    }

    @Override
    public void setParameter(Object parameter) {
        this.purchaseInputOrder = (PurchaseInputOrderDTO) parameter;
    }
}
