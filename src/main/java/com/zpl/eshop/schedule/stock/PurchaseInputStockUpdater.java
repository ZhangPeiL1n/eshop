package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.GoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.GoodsStockDAO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockDO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockId;
import com.zpl.eshop.schedule.domain.GoodsStockDO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderPutOnItemDTO;

import java.util.List;
import java.util.Map;

/**
 * 采购入库更新销库存命令
 * <p>
 * 采购入库只需要关心采购入库单的中各个商品条目的采购数量就行
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 23:03
 **/
public class PurchaseInputStockUpdater extends AbstractStockUpdater {

    /**
     * 采购入库单条目DTO集合
     */
    private final Map<Long, PurchaseInputOrderItemDTO> itemMap;

    /**
     * 上架条目
     */
    private final Map<GoodsAllocationStockId, PurchaseInputOrderPutOnItemDTO> putOnItemMap;

    /**
     * 构造函数
     *
     * @param goodsStocks   商品库存对象
     * @param goodsStockDAO 商品库存管理模块DAO组件
     * @param dateProvider  日期辅助组件
     * @param itemMap       采购入库单条目DTO Map
     */
    public PurchaseInputStockUpdater(
            List<GoodsStockDO> goodsStocks,
            List<GoodsAllocationStockDO> goodsAllocationStocks,
            GoodsStockDAO goodsStockDAO,
            GoodsAllocationStockDAO goodsAllocationStockDAO,
            DateProvider dateProvider,
            Map<Long, PurchaseInputOrderItemDTO> itemMap,
            Map<GoodsAllocationStockId, PurchaseInputOrderPutOnItemDTO> putOnItemMap) {
        super(goodsStocks, goodsAllocationStocks, goodsStockDAO, goodsAllocationStockDAO, dateProvider);
        this.itemMap = itemMap;
        this.putOnItemMap = putOnItemMap;
    }


    @Override
    protected void updateGoodsAvailableStockQuantity() throws Exception {
        goodsStocks.forEach(goodsStock -> {
            PurchaseInputOrderItemDTO item = itemMap.get(goodsStock.getGoodsSkuId());
            goodsStock.setAvailableStockQuantity(goodsStock.getAvailableStockQuantity() + item.getArrivalCount());
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
            PurchaseInputOrderPutOnItemDTO putOnItem = putOnItemMap.get(new GoodsAllocationStockId(goodsAllocationStock.getGoodsAllocationId(), goodsAllocationStock.getGoodsSkuId()));
            goodsAllocationStock.setAvailableStockQuantity(goodsAllocationStock.getAvailableStockQuantity() + putOnItem.getPutOnShelvesCount());
        });
    }

    @Override
    protected void updateGoodsAllocationLockedStockQuantity() throws Exception {

    }

    @Override
    protected void updateGoodsAllocationOutputStockQuantity() throws Exception {

    }
}
