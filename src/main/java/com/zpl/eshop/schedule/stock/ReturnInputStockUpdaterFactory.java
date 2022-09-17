package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.GoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.GoodsStockDAO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockDO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockId;
import com.zpl.eshop.schedule.domain.GoodsStockDO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderItemDTO;
import com.zpl.eshop.wms.domain.ReturnGoodsInputOrderPutOnItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 退货入库库存更新命令工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 22:20
 **/
@Component
public class ReturnInputStockUpdaterFactory extends AbstractStockUpdaterFactory<ReturnGoodsInputOrderDTO> {


    /**
     * 构造函数
     *
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     */
    @Autowired
    public ReturnInputStockUpdaterFactory(GoodsStockDAO goodsStockDAO, GoodsAllocationStockDAO goodsAllocationStockDAO, DateProvider dateProvider) {
        super(goodsStockDAO, goodsAllocationStockDAO, dateProvider);
    }

    /**
     * 获取商品skuId集合
     *
     * @return 商品skuId集合
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(ReturnGoodsInputOrderDTO parameter) throws Exception {
        // 拿到退货入库单条目集合
        List<ReturnGoodsInputOrderItemDTO> items = parameter.getItems();
        // 卫语句,没有就返回空集合
        if (items == null || items.size() == 0) {
            return new ArrayList<>();
        }

        // 构造商品skuId集合
        ArrayList<Long> goodsSkuIds = new ArrayList<>(items.size());
        for (ReturnGoodsInputOrderItemDTO item : items) {
            goodsSkuIds.add(item.getGoodsSkuId());
        }
        return goodsSkuIds;
    }

    @Override
    protected List<GoodsAllocationStockId> getGoodsAllocationIds(ReturnGoodsInputOrderDTO parameter) throws Exception {
        List<ReturnGoodsInputOrderItemDTO> items = parameter.getItems();
        // 卫语句,没有就返回空集合
        if (items == null || items.size() == 0) {
            return new ArrayList<>();
        }

        // 构造商品skuId集合
        List<GoodsAllocationStockId> goodsAllocationStockIds = new ArrayList<>(items.size());
        for (ReturnGoodsInputOrderItemDTO item : items) {
            for (ReturnGoodsInputOrderPutOnItemDTO putOnItem : item.getPutOnItems()) {
                GoodsAllocationStockId goodsAllocationStockId = new GoodsAllocationStockId();
                goodsAllocationStockId.setGoodsSkuId(item.getGoodsSkuId());
                goodsAllocationStockId.setGoodsAllocationId(putOnItem.getGoodsAllocationId());
                goodsAllocationStockIds.add(goodsAllocationStockId);
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
    protected StockUpdater create(List<GoodsStockDO> goodsStocks, List<GoodsAllocationStockDO> goodsAllocationStocks, ReturnGoodsInputOrderDTO parameter) throws Exception {
        List<ReturnGoodsInputOrderItemDTO> items = parameter.getItems();

        Map<Long, ReturnGoodsInputOrderItemDTO> itemMap = new HashMap<>();
        Map<GoodsAllocationStockId, ReturnGoodsInputOrderPutOnItemDTO> putOnItemMap = new HashMap<>();

        // 卫语句,没有就返回空集合
        if (items != null && items.size() > 0) {
            for (ReturnGoodsInputOrderItemDTO item : items) {
                itemMap.put(item.getGoodsSkuId(), item);
                item.getPutOnItems().forEach(putOnItem -> {
                    putOnItemMap.put(new GoodsAllocationStockId(putOnItem.getGoodsAllocationId(), item.getGoodsSkuId()), putOnItem);
                });
            }
        }
        return new ReturnInputStockUpdater(goodsStocks, goodsAllocationStocks, goodsStockDAO, goodsAllocationStockDAO, dateProvider, itemMap, putOnItemMap);
    }
}
