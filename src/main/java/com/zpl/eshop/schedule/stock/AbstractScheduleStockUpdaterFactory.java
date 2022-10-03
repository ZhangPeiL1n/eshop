package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsStockDAO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockId;
import com.zpl.eshop.schedule.domain.SchecduleGoodsStockDO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存更新组件工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 22:09
 **/
public abstract class AbstractScheduleStockUpdaterFactory<T> implements ScheduleStockUpdaterFactory<T> {

    private final Logger logger = LoggerFactory.getLogger(AbstractScheduleStockUpdaterFactory.class);

    /**
     * 商品库存管理模块DAO组件
     */
    protected ScheduleGoodsStockDAO scheduleGoodsStockDAO;

    /**
     * 商品货位库存管理DAO组件
     */
    protected ScheduleGoodsAllocationStockDAO scheduleGoodsAllocationStockDAO;

    /**
     * 日期辅助组件
     */
    protected DateProvider dateProvider;

    /**
     * 构造函数
     *
     * @param scheduleGoodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider          日期辅助组件
     */
    public AbstractScheduleStockUpdaterFactory(
            ScheduleGoodsStockDAO scheduleGoodsStockDAO,
            ScheduleGoodsAllocationStockDAO scheduleGoodsAllocationStockDAO,
            DateProvider dateProvider) {
        this.scheduleGoodsStockDAO = scheduleGoodsStockDAO;
        this.scheduleGoodsAllocationStockDAO = scheduleGoodsAllocationStockDAO;
        this.dateProvider = dateProvider;
    }

    /**
     * 创建库存更新命令
     *
     * @param parameter 参数
     * @return 库存更新命令
     */
    @Override
    public ScheduleStockUpdater create(T parameter) {
        try {
            List<Long> goodsSkuIds = getGoodsSkuIds(parameter);
            List<SchecduleGoodsStockDO> goodsStocks = getGoodsStocks(goodsSkuIds);
            List<GoodsAllocationStockId> goodsAllocationStockIds = getGoodsAllocationIds(parameter);
            List<ScheduleGoodsAllocationStockDO> goodsAllocationStocks = getGoodsAllocationStocks(goodsAllocationStockIds);
            return create(goodsStocks, goodsAllocationStocks, parameter);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 获取商品skuId集合
     *
     * @param parameter 库存来源单：采购入库单或者退货入库单
     * @return 商品skuId集合
     * @throws Exception
     */
    protected abstract List<Long> getGoodsSkuIds(T parameter) throws Exception;

    /**
     * 获取货位id
     *
     * @param parameter 库存来源单：采购入库单或者退货入库单
     * @return 商品货位id集合
     * @throws Exception
     */
    protected abstract List<GoodsAllocationStockId> getGoodsAllocationIds(T parameter) throws Exception;

    /**
     * 创建库存更新命令
     *
     * @param goodsStocks 商品库存对象DO集合
     * @param parameter   库存来源单：采购入库单或者退货入库单
     * @return 商品更新命令
     * @throws Exception
     */
    protected abstract ScheduleStockUpdater create(List<SchecduleGoodsStockDO> goodsStocks,
                                                   List<ScheduleGoodsAllocationStockDO> goodsAllocationStocks,
                                                   T parameter) throws Exception;

    /**
     * 获取商品库存DO对象集合
     *
     * @param goodsSkuIds 商品skuId集合
     * @return 商品库存DO对象集合
     */
    private List<SchecduleGoodsStockDO> getGoodsStocks(List<Long> goodsSkuIds) throws Exception {
        List<SchecduleGoodsStockDO> goodsStocks = new ArrayList<>(goodsSkuIds.size());
        for (Long goodsSkuId : goodsSkuIds) {
            SchecduleGoodsStockDO goodsStock = scheduleGoodsStockDAO.getBySkuId(goodsSkuId);
            // 如果没有库存对象就新建一个
            if (goodsStock == null) {
                goodsStock = createGoodsStockDO(goodsSkuId);
            }
            goodsStocks.add(goodsStock);
        }
        return goodsStocks;
    }

    /**
     * 获取货位库存DO对象
     *
     * @param goodsAllocationStockIds 货位id集合
     * @return 货位对象
     */
    private List<ScheduleGoodsAllocationStockDO> getGoodsAllocationStocks(List<GoodsAllocationStockId> goodsAllocationStockIds) throws Exception {
        List<ScheduleGoodsAllocationStockDO> goodsAllocationStocks = new ArrayList<>(goodsAllocationStockIds.size());
        for (GoodsAllocationStockId goodsAllocationStockId : goodsAllocationStockIds) {
            ScheduleGoodsAllocationStockDO goodsAllocationStock = scheduleGoodsAllocationStockDAO.getBySkuId(goodsAllocationStockId.getGoodsAllocationId(), goodsAllocationStockId.getGoodsSkuId());
            // 如果没有库存对象就新建一个
            if (goodsAllocationStock == null) {
                goodsAllocationStock = createGoodsAllocationStockDO(goodsAllocationStockId.getGoodsAllocationId(), goodsAllocationStockId.getGoodsSkuId());
            }
            goodsAllocationStocks.add(goodsAllocationStock);
        }
        return goodsAllocationStocks;
    }

    /**
     * 创建商品库存DO对象
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO对象
     * @throws Exception
     */
    private SchecduleGoodsStockDO createGoodsStockDO(Long goodsSkuId) throws Exception {
        SchecduleGoodsStockDO goodsStock = new SchecduleGoodsStockDO();
        goodsStock.setGoodsSkuId(goodsSkuId);
        goodsStock.setAvailableStockQuantity(0L);
        goodsStock.setLockedStockQuantity(0L);
        goodsStock.setOutputStockQuantity(0L);
        goodsStock.setGmtCreate(dateProvider.getCurrentTime());
        goodsStock.setGmtModified(dateProvider.getCurrentTime());
        scheduleGoodsStockDAO.save(goodsStock);

        return goodsStock;
    }

    private ScheduleGoodsAllocationStockDO createGoodsAllocationStockDO(Long goodsAllocationId, Long goodsSkuId) throws Exception {
        ScheduleGoodsAllocationStockDO goodsAllocationStock = new ScheduleGoodsAllocationStockDO();
        goodsAllocationStock.setGoodsAllocationId(goodsAllocationId);
        goodsAllocationStock.setGoodsSkuId(goodsSkuId);
        goodsAllocationStock.setAvailableStockQuantity(0L);
        goodsAllocationStock.setLockedStockQuantity(0L);
        goodsAllocationStock.setOutputStockQuantity(0L);
        goodsAllocationStock.setGmtCreate(dateProvider.getCurrentTime());
        goodsAllocationStock.setGmtModified(dateProvider.getCurrentTime());
        scheduleGoodsAllocationStockDAO.save(goodsAllocationStock);

        return goodsAllocationStock;
    }
}
