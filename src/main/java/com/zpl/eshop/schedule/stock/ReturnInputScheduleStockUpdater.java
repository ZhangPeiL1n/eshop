package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsStockDAO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockId;
import com.zpl.eshop.schedule.domain.SchecduleGoodsStockDO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderItemDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderPutOnItemDTO;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 退货入库更新库存命令
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 21:16
 **/
public class ReturnInputScheduleStockUpdater extends AbstractScheduleStockUpdater {

    /**
     * 退货入库单
     */
    @Setter
    private Map<Long, ReturnGoodsInputOrderItemDTO> itemMap;

    /**
     * 退货入库单上架条目
     */
    @Setter
    private Map<GoodsAllocationStockId, ReturnGoodsInputOrderPutOnItemDTO> putOnItemMap;

    /**
     * 构造函数
     *
     * @param goodsStocks           商品库存对象
     * @param scheduleGoodsStockDAO 商品库存管理模块DAO组件
     * @param dateProvider          日期辅助组件
     * @param itemMap               采购入库单条目DTO Map
     */
    public ReturnInputScheduleStockUpdater(
            List<SchecduleGoodsStockDO> goodsStocks,
            List<ScheduleGoodsAllocationStockDO> goodsAllocationStocks,
            ScheduleGoodsStockDAO scheduleGoodsStockDAO,
            ScheduleGoodsAllocationStockDAO scheduleGoodsAllocationStockDAO,
            DateProvider dateProvider) {
        super(goodsStocks, goodsAllocationStocks, scheduleGoodsStockDAO, scheduleGoodsAllocationStockDAO, dateProvider);

    }


    @Override
    protected void updateGoodsAvailableStockQuantity() throws Exception {
        goodsStocks.forEach(goodsStock -> {
            ReturnGoodsInputOrderItemDTO item = itemMap.get(goodsStock.getGoodsSkuId());
            goodsStock.setAvailableStockQuantity(goodsStock.getAvailableStockQuantity() + item.getArrivalCount());
        });
    }

    @Override
    protected void updateGoodsLockedStockQuantity() throws Exception {

    }

    @Override
    protected void updateGoodsOutputStockQuantity() throws Exception {
        goodsStocks.forEach(goodsStock -> {
            ReturnGoodsInputOrderItemDTO item = itemMap.get(goodsStock.getGoodsSkuId());
            goodsStock.setOutputStockQuantity(goodsStock.getOutputStockQuantity() - item.getArrivalCount());
        });
    }

    @Override
    protected void updateGoodsAllocationAvailableStockQuantity() throws Exception {
        goodsAllocationStocks.forEach(goodsAllocationStock -> {
            ReturnGoodsInputOrderPutOnItemDTO putOnItem = putOnItemMap.get(new GoodsAllocationStockId(goodsAllocationStock.getGoodsAllocationId(), goodsAllocationStock.getGoodsSkuId()));
            goodsAllocationStock.setAvailableStockQuantity(goodsAllocationStock.getAvailableStockQuantity() + putOnItem.getPutOnShelvesCount());
        });
    }

    @Override
    protected void updateGoodsAllocationLockedStockQuantity() throws Exception {

    }

    @Override
    protected void updateGoodsAllocationOutputStockQuantity() throws Exception {
        goodsAllocationStocks.forEach(goodsAllocationStock -> {
            ReturnGoodsInputOrderPutOnItemDTO putOnItem = putOnItemMap.get(new GoodsAllocationStockId(goodsAllocationStock.getGoodsAllocationId(), goodsAllocationStock.getGoodsSkuId()));
            goodsAllocationStock.setOutputStockQuantity(goodsAllocationStock.getOutputStockQuantity() - putOnItem.getPutOnShelvesCount());
        });
    }

    @Override
    protected void updateGoodsAllocationDetailLockedStockQuantity() throws Exception {

    }

    @Override
    protected void updateGoodsAllocationDetailCurrentStockQuantity() throws Exception {

    }
}
