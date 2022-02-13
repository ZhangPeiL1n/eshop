package com.zpl.eshop.inventory.updater;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.constant.StockStatus;
import com.zpl.eshop.inventory.dao.GoodsStockDAO;
import com.zpl.eshop.inventory.domain.GoodsStockDO;
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
    protected List<GoodsStockDO> goodsStockDOList;

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
     * @param goodsStockDOList 商品库存对象集合
     * @param goodsStockDAO    商品库存管理模块DAO组件
     * @param dateProvider     日期辅助组件
     */
    public AbstractStockUpdater(List<GoodsStockDO> goodsStockDOList, GoodsStockDAO goodsStockDAO, DateProvider dateProvider) {
        this.goodsStockDOList = goodsStockDOList;
        this.goodsStockDAO = goodsStockDAO;
        this.dateProvider = dateProvider;
    }

    @Override
    public Boolean updateGoodsStock() {
        try {
            updateSaleStockQuantity();
            updateLockedStockQuantity();
            updateSaledStockQuantity();
            updateStockStatus();
            updateGmtModified();
            executeUpdateGoodsStock();
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
    protected abstract void updateSaleStockQuantity() throws Exception;

    /**
     * 更新商品锁定库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateLockedStockQuantity() throws Exception;

    /**
     * 更新商品已销售库存
     *
     * @throws Exception 交由基类处理
     */
    protected abstract void updateSaledStockQuantity() throws Exception;

    /**
     * 更新商品的库存状态
     *
     * @throws Exception 交由基类处理
     */
    private void updateStockStatus() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            // 库存大于 0 设置为有库存
            if (goodsStockDO.getSaleStockQuantity() > 0) {
                goodsStockDO.setStockStatus(StockStatus.IN_STOCK);
            } else {
                goodsStockDO.setStockStatus(StockStatus.NOT_IN_STOCK);
            }
        }
    }

    /**
     * 更新商品库存修改时间
     *
     * @throws Exception 交由基类处理
     */
    private void updateGmtModified() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            goodsStockDO.setGmtModified(dateProvider.getCurrentTime());
        }
    }

    /**
     * 实际执行更新商品库存操作
     */
    private void executeUpdateGoodsStock() {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            goodsStockDAO.updateGoodsStock(goodsStockDO);
        }
    }
}
