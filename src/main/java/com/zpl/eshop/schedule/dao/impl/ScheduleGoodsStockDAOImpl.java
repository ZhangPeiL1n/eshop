package com.zpl.eshop.schedule.dao.impl;


import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.ScheduleGoodsStockDAO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsStockDO;
import com.zpl.eshop.schedule.mapper.ScheduleGoodsStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 调度中心商品库存管理组件
 *
 * @author ZhangPeiL1n
 * @date 2022/9/17 18:58
 **/
@Repository
public class ScheduleGoodsStockDAOImpl implements ScheduleGoodsStockDAO {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 商品库存管理模块mapper组件
     */
    @Autowired
    private ScheduleGoodsStockMapper stockMapper;


    /**
     * 根据商品 skuId 获取库存
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO
     * @throws Exception
     */
    @Override
    public ScheduleGoodsStockDO getBySkuId(Long goodsSkuId) throws Exception {
        ScheduleGoodsStockDO goodsStock = stockMapper.getBySkuId(goodsSkuId);
        if (goodsStock == null) {
            goodsStock = new ScheduleGoodsStockDO();
            goodsStock.setGoodsSkuId(goodsSkuId);
            goodsStock.setAvailableStockQuantity(0L);
            goodsStock.setLockedStockQuantity(0L);
            goodsStock.setOutputStockQuantity(0L);
            save(goodsStock);
        }
        return goodsStock;
    }

    /**
     * 新增商品库存
     *
     * @param goodsStock 商品库存DO对象
     * @throws Exception
     */
    @Override
    public void save(ScheduleGoodsStockDO goodsStock) throws Exception {
        goodsStock.setGmtCreate(dateProvider.getCurrentTime());
        goodsStock.setGmtModified(dateProvider.getCurrentTime());
        stockMapper.save(goodsStock);
    }

    /**
     * 更新商品库存
     *
     * @param goodsStock 商品库存
     * @throws Exception
     */
    @Override
    public void update(ScheduleGoodsStockDO goodsStock) throws Exception {
        goodsStock.setGmtModified(dateProvider.getCurrentTime());
        stockMapper.update(goodsStock);
    }
}
