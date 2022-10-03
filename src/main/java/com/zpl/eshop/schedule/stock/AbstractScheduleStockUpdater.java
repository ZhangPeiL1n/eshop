package com.zpl.eshop.schedule.stock;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDAO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsStockDAO;
import com.zpl.eshop.schedule.domain.SchecduleGoodsStockDO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 商品库存更新命令的抽象基类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 22:31
 **/
public abstract class AbstractScheduleStockUpdater implements ScheduleStockUpdater {

    private final Logger logger = LoggerFactory.getLogger(AbstractScheduleStockUpdater.class);

    /**
     * 商品库存对象
     */
    protected List<SchecduleGoodsStockDO> goodsStocks;

    /**
     * 商品货位库存对象
     */
    protected List<ScheduleGoodsAllocationStockDO> goodsAllocationStocks;

    /**
     * 商品库存管理模块DAO组件
     */
    protected ScheduleGoodsStockDAO scheduleGoodsStockDAO;

    /**
     * 商品货位库尊管理模块DAO组件
     */
    protected ScheduleGoodsAllocationStockDAO scheduleGoodsAllocationStockDAO;

    /**
     * 日期辅助组件
     */
    protected DateProvider dateProvider;

    /**
     * 构造函数
     *
     * @param goodsStocks           商品库存对象集合
     * @param scheduleGoodsStockDAO 商品库存管理模块DAO组件
     * @param dateProvider          日期辅助组件
     */
    public AbstractScheduleStockUpdater(List<SchecduleGoodsStockDO> goodsStocks,
                                        List<ScheduleGoodsAllocationStockDO> goodsAllocationStocks,
                                        ScheduleGoodsStockDAO scheduleGoodsStockDAO,
                                        ScheduleGoodsAllocationStockDAO scheduleGoodsAllocationStockDAO,
                                        DateProvider dateProvider) {
        this.goodsStocks = goodsStocks;
        this.goodsAllocationStocks = goodsAllocationStocks;
        this.scheduleGoodsStockDAO = scheduleGoodsStockDAO;
        this.scheduleGoodsAllocationStockDAO = scheduleGoodsAllocationStockDAO;
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
            updateGoodsAllocationDetailCurrentStockQuantity();
            updateGoodsAllocationDetailLockedStockQuantity();
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
     * 更新商品货位锁定库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsAllocationDetailLockedStockQuantity() throws Exception;

    /**
     * 更新商品货位已销售库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateGoodsAllocationDetailCurrentStockQuantity() throws Exception;


    /**
     * 设置库存修改时间
     *
     * @throws Exception 交由基类处理
     */
    private void updateGmtModified() throws Exception {
        for (SchecduleGoodsStockDO schecduleGoodsStockDO : goodsStocks) {
            schecduleGoodsStockDO.setGmtModified(dateProvider.getCurrentTime());
        }
        for (ScheduleGoodsAllocationStockDO goodsAllocationStock : goodsAllocationStocks) {
            goodsAllocationStock.setGmtModified(dateProvider.getCurrentTime());
        }
    }

    /**
     * 实际执行更新商品库存操作
     */
    private void executeUpdateStock() {
        for (SchecduleGoodsStockDO goodsStock : goodsStocks) {
            scheduleGoodsStockDAO.update(goodsStock);
        }

        for (ScheduleGoodsAllocationStockDO goodsAllocationStock : goodsAllocationStocks) {
            scheduleGoodsAllocationStockDAO.update(goodsAllocationStock);
        }
    }
}
