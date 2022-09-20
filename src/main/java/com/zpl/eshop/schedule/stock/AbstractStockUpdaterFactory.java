package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.GoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.GoodsStockDAO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockDO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockId;
import com.zpl.eshop.schedule.domain.GoodsStockDO;
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
public abstract class AbstractStockUpdaterFactory<T> implements StockUpdaterFactory<T> {

    private final Logger logger = LoggerFactory.getLogger(AbstractStockUpdaterFactory.class);

    /**
     * 商品库存管理模块DAO组件
     */
    protected GoodsStockDAO goodsStockDAO;

    /**
     * 商品货位库存管理DAO组件
     */
    protected GoodsAllocationStockDAO goodsAllocationStockDAO;

    /**
     * 日期辅助组件
     */
    protected DateProvider dateProvider;

    /**
     * 构造函数
     *
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     */
    public AbstractStockUpdaterFactory(
            GoodsStockDAO goodsStockDAO,
            GoodsAllocationStockDAO goodsAllocationStockDAO,
            DateProvider dateProvider) {
        this.goodsStockDAO = goodsStockDAO;
        this.goodsAllocationStockDAO = goodsAllocationStockDAO;
        this.dateProvider = dateProvider;
    }

    /**
     * 创建库存更新命令
     *
     * @param parameter 参数
     * @return 库存更新命令
     */
    @Override
    public StockUpdater create(T parameter) {
        try {
            List<Long> goodsSkuIds = getGoodsSkuIds(parameter);
            List<GoodsStockDO> goodsStocks = getGoodsStocks(goodsSkuIds);
            List<GoodsAllocationStockId> goodsAllocationStockIds = getGoodsAllocationIds(parameter);
            List<GoodsAllocationStockDO> goodsAllocationStocks = getGoodsAllocationStocks(goodsAllocationStockIds);
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
    protected abstract StockUpdater create(List<GoodsStockDO> goodsStocks,
                                           List<GoodsAllocationStockDO> goodsAllocationStocks,
                                           T parameter) throws Exception;

    /**
     * 获取商品库存DO对象集合
     *
     * @param goodsSkuIds 商品skuId集合
     * @return 商品库存DO对象集合
     */
    private List<GoodsStockDO> getGoodsStocks(List<Long> goodsSkuIds) throws Exception {
        List<GoodsStockDO> goodsStocks = new ArrayList<>(goodsSkuIds.size());
        for (Long goodsSkuId : goodsSkuIds) {
            GoodsStockDO goodsStock = goodsStockDAO.getBySkuId(goodsSkuId);
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
    private List<GoodsAllocationStockDO> getGoodsAllocationStocks(List<GoodsAllocationStockId> goodsAllocationStockIds) throws Exception {
        List<GoodsAllocationStockDO> goodsAllocationStocks = new ArrayList<>(goodsAllocationStockIds.size());
        for (GoodsAllocationStockId goodsAllocationStockId : goodsAllocationStockIds) {
            GoodsAllocationStockDO goodsAllocationStock = goodsAllocationStockDAO.getBySkuId(goodsAllocationStockId.getGoodsAllocationId(), goodsAllocationStockId.getGoodsSkuId());
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
    private GoodsStockDO createGoodsStockDO(Long goodsSkuId) throws Exception {
        GoodsStockDO goodsStock = new GoodsStockDO();
        goodsStock.setGoodsSkuId(goodsSkuId);
        goodsStock.setAvailableStockQuantity(0L);
        goodsStock.setLockedStockQuantity(0L);
        goodsStock.setOutputStockQuantity(0L);
        goodsStock.setGmtCreate(dateProvider.getCurrentTime());
        goodsStock.setGmtModified(dateProvider.getCurrentTime());
        goodsStockDAO.save(goodsStock);

        return goodsStock;
    }

    private GoodsAllocationStockDO createGoodsAllocationStockDO(Long goodsAllocationId, Long goodsSkuId) throws Exception {
        GoodsAllocationStockDO goodsAllocationStock = new GoodsAllocationStockDO();
        goodsAllocationStock.setGoodsAllocationId(goodsAllocationId);
        goodsAllocationStock.setGoodsSkuId(goodsSkuId);
        goodsAllocationStock.setAvailableStockQuantity(0L);
        goodsAllocationStock.setLockedStockQuantity(0L);
        goodsAllocationStock.setOutputStockQuantity(0L);
        goodsAllocationStock.setGmtCreate(dateProvider.getCurrentTime());
        goodsAllocationStock.setGmtModified(dateProvider.getCurrentTime());
        goodsAllocationStockDAO.save(goodsAllocationStock);

        return goodsAllocationStock;
    }
}
