package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.GoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.GoodsStockDAO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockDO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockId;
import com.zpl.eshop.schedule.domain.GoodsStockDO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderPutOnItemDTO;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 采购入库库存更新组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 23:03
 **/
public class PurchaseInputStockUpdater extends AbstractStockUpdater {

    /**
     * 采购入库单条目
     */
    @Setter
    private Map<Long, PurchaseInputOrderItemDTO> itemMap;

    /**
     * 采购入库单上架条目
     */
    @Setter
    private Map<GoodsAllocationStockId, PurchaseInputOrderPutOnItemDTO> putOnItemMap;

    /**
     * 构造函数
     *
     * @param goodsStocks   商品库存对象
     * @param goodsStockDAO 商品库存管理模块DAO组件
     * @param dateProvider  日期辅助组件
     */
    public PurchaseInputStockUpdater(
            List<GoodsStockDO> goodsStocks,
            List<GoodsAllocationStockDO> goodsAllocationStocks,
            GoodsStockDAO goodsStockDAO,
            GoodsAllocationStockDAO goodsAllocationStockDAO,
            DateProvider dateProvider) {
        super(goodsStocks, goodsAllocationStocks, goodsStockDAO, goodsAllocationStockDAO, dateProvider);
    }


    @Override
    protected void updateGoodsAvailableStockQuantity() throws Exception {
        goodsStocks.forEach(goodsStock -> {
            PurchaseInputOrderItemDTO item = itemMap.get(goodsStock.getGoodsSkuId());
            Long availableStockQuantity = goodsStock.getAvailableStockQuantity() + item.getArrivalCount();
            goodsStock.setAvailableStockQuantity(availableStockQuantity);
        });
    }

    @Override
    protected void updateGoodsLockedStockQuantity() throws Exception {

    }

    @Override
    protected void updateGoodsOutputStockQuantity() throws Exception {

    }

    @Override
    protected void updateGoodsAllocationAvailableStockQuantity() throws Exception {
        goodsAllocationStocks.forEach(goodsAllocationStock -> {
            GoodsAllocationStockId id = new GoodsAllocationStockId(goodsAllocationStock.getGoodsAllocationId(), goodsAllocationStock.getGoodsSkuId());
            PurchaseInputOrderPutOnItemDTO putOnItem = putOnItemMap.get(id);
            Long availableStockQuantity = goodsAllocationStock.getAvailableStockQuantity() + putOnItem.getPutOnShelvesCount();
            goodsAllocationStock.setAvailableStockQuantity(availableStockQuantity);
        });
    }

    @Override
    protected void updateGoodsAllocationLockedStockQuantity() throws Exception {

    }

    @Override
    protected void updateGoodsAllocationOutputStockQuantity() throws Exception {

    }
}
