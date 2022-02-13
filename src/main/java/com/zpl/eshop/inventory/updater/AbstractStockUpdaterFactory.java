package com.zpl.eshop.inventory.updater;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.constant.StockStatus;
import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存更新命令工厂的父类
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
            DateProvider dateProvider) {
        this.goodsStockDAO = goodsStockDAO;
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
            List<GoodsStockDO> goodsStockDOList = createGoodsStockDOList(goodsSkuIds);
            return create(goodsStockDOList, parameter);
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
     * 创建库存更新命令
     *
     * @param goodsStockDOList 商品库存对象DO集合
     * @param parameter        库存来源单：采购入库单或者退货入库单
     * @return 商品更新命令
     * @throws Exception
     */
    protected abstract StockUpdater create(List<GoodsStockDO> goodsStockDOList, T parameter) throws Exception;

    /**
     * 创建商品库存DO对象集合
     *
     * @param goodsSkuIds 商品skuId集合
     * @return 商品库存DO对象集合
     */
    private List<GoodsStockDO> createGoodsStockDOList(List<Long> goodsSkuIds) throws Exception {
        List<GoodsStockDO> goodsStockDOList = new ArrayList<>(goodsSkuIds.size());
        for (Long goodsSkuId : goodsSkuIds) {
            GoodsStockDO goodsStockDO = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);
            // 如果没有库存对象就新建一个
            if (goodsStockDO == null) {
                goodsStockDO = new GoodsStockDO();
                goodsStockDO.setGoodsSkuId(goodsSkuId);
                goodsStockDO.setSaleStockQuantity(0L);
                goodsStockDO.setLockedStockQuantity(0L);
                goodsStockDO.setSaledStockQuantity(0L);
                goodsStockDO.setStockStatus(StockStatus.NOT_IN_STOCK);
                goodsStockDO.setGmtCreate(dateProvider.getCurrentTime());
                goodsStockDO.setGmtModified(dateProvider.getCurrentTime());
                goodsStockDAO.saveGoodsStock(goodsStockDO);
            }
            goodsStockDOList.add(goodsStockDO);
        }
        return goodsStockDOList;
    }
}
