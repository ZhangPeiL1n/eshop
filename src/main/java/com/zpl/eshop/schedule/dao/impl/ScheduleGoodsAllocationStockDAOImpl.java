package com.zpl.eshop.schedule.dao.impl;


import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDAO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDO;
import com.zpl.eshop.schedule.mapper.ScheduleGoodsAllocationStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 调度中心货位库存管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/17 18:50
 **/
@Repository
public class ScheduleGoodsAllocationStockDAOImpl implements ScheduleGoodsAllocationStockDAO {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 货位货位库存管理Mapper
     */
    @Autowired
    private ScheduleGoodsAllocationStockMapper scheduleGoodsAllocationStockMapper;

    /**
     * 根据货位 skuId 获取库存
     *
     * @param goodsAllocationId 商品货位id
     * @param goodsSkuId        货位skuId
     * @return 货位库存DO
     * @throws Exception
     */
    @Override
    public ScheduleGoodsAllocationStockDO getBySkuId(Long goodsAllocationId, Long goodsSkuId) throws Exception {
        ScheduleGoodsAllocationStockDO goodsAllocationStock = scheduleGoodsAllocationStockMapper.getBySkuId(goodsAllocationId, goodsSkuId);
        if (goodsAllocationStock == null) {
            goodsAllocationStock = new ScheduleGoodsAllocationStockDO();
            goodsAllocationStock.setGoodsSkuId(goodsSkuId);
            goodsAllocationStock.setGoodsAllocationId(goodsAllocationId);
            goodsAllocationStock.setAvailableStockQuantity(0L);
            goodsAllocationStock.setLockedStockQuantity(0L);
            goodsAllocationStock.setOutputStockQuantity(0L);
            save(goodsAllocationStock);
        }
        return goodsAllocationStock;
    }

    /**
     * 新增货位库存
     *
     * @param goodsAllocationStock 货位库存DO对象
     * @throws Exception
     */
    @Override
    public void save(ScheduleGoodsAllocationStockDO goodsAllocationStock) throws Exception {
        goodsAllocationStock.setGmtCreate(dateProvider.getCurrentTime());
        goodsAllocationStock.setGmtModified(dateProvider.getCurrentTime());
        scheduleGoodsAllocationStockMapper.save(goodsAllocationStock);
    }

    /**
     * 更新货位库存
     *
     * @param goodsAllocationStock 货位库存
     * @throws Exception
     */
    @Override
    public void update(ScheduleGoodsAllocationStockDO goodsAllocationStock) throws Exception {
        goodsAllocationStock.setGmtModified(dateProvider.getCurrentTime());
        scheduleGoodsAllocationStockMapper.update(goodsAllocationStock);
    }

}
