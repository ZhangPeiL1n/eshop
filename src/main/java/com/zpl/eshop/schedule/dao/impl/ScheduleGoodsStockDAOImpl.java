package com.zpl.eshop.schedule.dao.impl;


import com.zpl.eshop.schedule.dao.ScheduleGoodsStockDAO;
import com.zpl.eshop.schedule.domain.SchecduleGoodsStockDO;
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
     * 商品库存管理模块mapper组件
     */
    @Autowired
    private ScheduleGoodsStockMapper scheduleGoodsStockMapper;


    /**
     * 根据商品 skuId 获取库存
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO
     */
    @Override
    public SchecduleGoodsStockDO getBySkuId(Long goodsSkuId) {
        return scheduleGoodsStockMapper.getBySkuId(goodsSkuId);
    }

    /**
     * 新增商品库存
     *
     * @param schecduleGoodsStockDO 商品库存DO对象
     */
    @Override
    public void save(SchecduleGoodsStockDO schecduleGoodsStockDO) {
        scheduleGoodsStockMapper.save(schecduleGoodsStockDO);
    }

    /**
     * 更新商品库存
     *
     * @param schecduleGoodsStockDO 商品库存
     */
    @Override
    public void update(SchecduleGoodsStockDO schecduleGoodsStockDO) {
        scheduleGoodsStockMapper.update(schecduleGoodsStockDO);
    }
}
