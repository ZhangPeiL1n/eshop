package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsStockDAO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockId;
import com.zpl.eshop.schedule.domain.SchecduleGoodsStockDO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDTO;
import com.zpl.eshop.wms.domain.PurchaseInputOrderPutOnItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购入库库存更新命令工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 22:20
 **/
@Component
public class PurchaseInputScheduleStockUpdaterFactory extends AbstractScheduleStockUpdaterFactory<PurchaseInputOrderDTO> {


    /**
     * 构造函数
     *
     * @param scheduleGoodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider          日期辅助组件
     */
    @Autowired
    public PurchaseInputScheduleStockUpdaterFactory(ScheduleGoodsStockDAO scheduleGoodsStockDAO, ScheduleGoodsAllocationStockDAO scheduleGoodsAllocationStockDAO, DateProvider dateProvider) {
        super(scheduleGoodsStockDAO, scheduleGoodsAllocationStockDAO, dateProvider);
    }

    /**
     * 获取商品skuId集合
     *
     * @return 商品skuId集合
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(PurchaseInputOrderDTO parameter) throws Exception {
        List<PurchaseInputOrderItemDTO> items = parameter.getItems();
        // 卫语句,没有就返回空集合
        if (items == null || items.size() == 0) {
            return new ArrayList<>();
        }

        // 构造商品skuId集合
        List<Long> goodsSkuIds = new ArrayList<>(items.size());
        for (PurchaseInputOrderItemDTO item : items) {
            goodsSkuIds.add(item.getGoodsSkuId());
        }
        return goodsSkuIds;
    }

    /**
     * 获取货位库存id
     *
     * @param parameter 库存来源单：采购入库单或者退货入库单
     * @return 货位库存id
     * @throws Exception
     */
    @Override
    protected List<GoodsAllocationStockId> getGoodsAllocationIds(PurchaseInputOrderDTO parameter) throws Exception {
        List<PurchaseInputOrderItemDTO> items = parameter.getItems();
        // 卫语句,没有就返回空集合
        if (items == null || items.size() == 0) {
            return new ArrayList<>();
        }

        // 构造商品skuId集合
        List<GoodsAllocationStockId> goodsAllocationStockIds = new ArrayList<>(items.size());
        for (PurchaseInputOrderItemDTO item : items) {
            for (PurchaseInputOrderPutOnItemDTO putOnItem : item.getPutOnItems()) {
                GoodsAllocationStockId id = new GoodsAllocationStockId(putOnItem.getGoodsAllocationId(), item.getGoodsSkuId());
                goodsAllocationStockIds.add(id);
            }
        }
        return goodsAllocationStockIds;
    }

    /**
     * 创建库存更新命令
     *
     * @param goodsStocks 商品库存对象DO集合
     * @return 商品更新命令
     * @throws Exception
     */
    @Override
    protected ScheduleStockUpdater create(List<SchecduleGoodsStockDO> goodsStocks, List<ScheduleGoodsAllocationStockDO> goodsAllocationStocks, PurchaseInputOrderDTO parameter) throws Exception {
        List<PurchaseInputOrderItemDTO> items = parameter.getItems();

        Map<Long, PurchaseInputOrderItemDTO> itemMap = new HashMap<>();
        Map<GoodsAllocationStockId, PurchaseInputOrderPutOnItemDTO> putOnItemMap = new HashMap<>();

        // 卫语句,没有就返回空集合
        if (items != null && items.size() > 0) {
            for (PurchaseInputOrderItemDTO item : items) {
                itemMap.put(item.getGoodsSkuId(), item);
                item.getPutOnItems().forEach(putOnItem -> {
                    GoodsAllocationStockId id = new GoodsAllocationStockId(putOnItem.getGoodsAllocationId(), item.getGoodsSkuId());
                    putOnItemMap.put(id, putOnItem);
                });
            }
        }
        PurchaseInputScheduleStockUpdater updater = new PurchaseInputScheduleStockUpdater(goodsStocks, goodsAllocationStocks, scheduleGoodsStockDAO, scheduleGoodsAllocationStockDAO, dateProvider);
        updater.setItemMap(itemMap);
        updater.setPutOnItemMap(putOnItemMap);

        return updater;
    }

}
