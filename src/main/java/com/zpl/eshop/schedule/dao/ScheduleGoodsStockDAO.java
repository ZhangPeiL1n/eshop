package com.zpl.eshop.schedule.dao;


import com.zpl.eshop.schedule.domain.ScheduleGoodsStockDO;

/**
 * 调度中心商品库存管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/17 18:50
 **/
public interface ScheduleGoodsStockDAO {
    /**
     * 根据商品 skuId 获取库存
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO
     * @throws Exception
     */
    ScheduleGoodsStockDO getBySkuId(Long goodsSkuId) throws Exception;

    /**
     * 新增商品库存
     *
     * @param goodsStock 商品库存DO对象
     * @throws Exception
     */
    void save(ScheduleGoodsStockDO goodsStock) throws Exception;

    /**
     * 更新商品库存
     *
     * @param goodsStock 商品库存
     * @throws Exception
     */
    void update(ScheduleGoodsStockDO goodsStock) throws Exception;

}
