package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.GoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.GoodsStockDAO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockDO;
import com.zpl.eshop.schedule.domain.GoodsStockDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 商品库存更新命令的抽象基类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 22:31
 **/
public abstract class AbstractStockUpdater implements StockUpdater {

    private final Logger logger = LoggerFactory.getLogger(AbstractStockUpdater.class);

    /**
     * 商品库存对象
     */
    protected List<GoodsStockDO> goodsStocks;

    /**
     * 商品货位库存对象
     */
    protected List<GoodsAllocationStockDO> goodsAllocationStocks;

    /**
     * 商品库存管理模块DAO组件
     */
    protected GoodsStockDAO goodsStockDAO;

    /**
     * 商品货位库尊管理模块DAO组件
     */
    protected GoodsAllocationStockDAO goodsAllocationStockDAO;

    /**
     * 日期辅助组件
     */
    protected DateProvider dateProvider;

    /**
     * 构造函数
     *
     * @param goodsStocks   商品库存对象集合
     * @param goodsStockDAO 商品库存管理模块DAO组件
     * @param dateProvider  日期辅助组件
     */
    public AbstractStockUpdater(List<GoodsStockDO> goodsStocks,
                                List<GoodsAllocationStockDO> goodsAllocationStocks,
                                GoodsStockDAO goodsStockDAO,
                                GoodsAllocationStockDAO goodsAllocationStockDAO,
                                DateProvider dateProvider) {
        this.goodsStocks = goodsStocks;
        this.goodsAllocationStocks = goodsAllocationStocks;
        this.goodsStockDAO = goodsStockDAO;
        this.goodsAllocationStockDAO = goodsAllocationStockDAO;
        this.dateProvider = dateProvider;
    }

    @Override
    public Boolean update() {
        try {
            updateGoodsAvailableStockQuantity();
            updateGoodsLockedStockQuantity();
            updateGoodsOutputStockQuantity();
            updateGoodsAllocationAvailableStockQuantity();
            updateGoodsAllocationLockedStockQuantity();
            updateGoodsAllocationOutputStockQuantity();
            updateGmtModified();
            executeUpdateStock();
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }


    /**
     * 更新商品销售库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsAvailableStockQuantity() throws Exception;

    /**
     * 更新商品锁定库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsLockedStockQuantity() throws Exception;

    /**
     * 更新商品已销售库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsOutputStockQuantity() throws Exception;

    /**
     * 更新商品货位销售库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsAllocationAvailableStockQuantity() throws Exception;

    /**
     * 更新商品货位锁定库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsAllocationLockedStockQuantity() throws Exception;

    /**
     * 更新商品货位已销售库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsAllocationOutputStockQuantity() throws Exception;


    /**
     * 设置库存修改时间
     *
     * @throws Exception 交由基类处理
     */
    private void updateGmtModified() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStocks) {
            goodsStockDO.setGmtModified(dateProvider.getCurrentTime());
        }
        for (GoodsAllocationStockDO goodsAllocationStock : goodsAllocationStocks) {
            goodsAllocationStock.setGmtModified(dateProvider.getCurrentTime());
        }
    }

    /**
     * 实际执行更新商品库存操作
     */
    private void executeUpdateStock() {
        for (GoodsStockDO goodsStock : goodsStocks) {
            goodsStockDAO.update(goodsStock);
        }

        for (GoodsAllocationStockDO goodsAllocationStock : goodsAllocationStocks) {
            goodsAllocationStockDAO.update(goodsAllocationStock);
        }
    }
}
