package com.zpl.eshop.schedule.dao.impl;


import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDAO;
import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDO;
import com.zpl.eshop.schedule.mapper.ScheduleGoodsAllocationStockMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 调度中心货位库存管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/17 18:50
 **/
public class ScheduleGoodsAllocationStockDAOImpl implements ScheduleGoodsAllocationStockDAO {

    /**
     * 货位货位库存管理Mapper
     */
    @Autowired
    private ScheduleGoodsAllocationStockMapper scheduleGoodsAllocationStockMapper;

    /**
     * 根据货位 skuId 获取库存
     *
     * @param goodsSkuId 货位skuId
     * @return 货位库存DO
     */
    @Override
    public ScheduleGoodsAllocationStockDO getBySkuId(Long goodsAllocationId, Long goodsSkuId) {
        return scheduleGoodsAllocationStockMapper.getBySkuId(goodsAllocationId, goodsSkuId);
    }

    /**
     * 新增货位库存
     *
     * @param goodsAllocationStockD 货位库存DO对象
     */
    @Override
    public void save(ScheduleGoodsAllocationStockDO goodsAllocationStockD) {
        scheduleGoodsAllocationStockMapper.save(goodsAllocationStockD);
    }

    /**
     * 更新货位库存
     *
     * @param goodsAllocationStockD 货位库存
     */
    @Override
    public void update(ScheduleGoodsAllocationStockDO goodsAllocationStockD) {
        scheduleGoodsAllocationStockMapper.update(goodsAllocationStockD);
    }

}
