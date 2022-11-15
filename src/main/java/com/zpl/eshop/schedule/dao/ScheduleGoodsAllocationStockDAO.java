package com.zpl.eshop.schedule.dao;


import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDO;

/**
 * 调度中心货位库存管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/17 18:50
 **/
public interface ScheduleGoodsAllocationStockDAO {
    /**
     * 根据货位 skuId 获取库存
     *
     * @param goodsAllocationId 商品货位id
     * @param goodsSkuId        货位skuId
     * @return 货位库存DO
     * @throws Exception
     */
    ScheduleGoodsAllocationStockDO getBySkuId(Long goodsAllocationId, Long goodsSkuId) throws Exception;

    /**
     * 新增货位库存
     *
     * @param goodsAllocationStock 货位库存DO对象
     * @throws Exception
     */
    void save(ScheduleGoodsAllocationStockDO goodsAllocationStock) throws Exception;

    /**
     * 更新货位库存
     *
     * @param goodsAllocationStock 货位库存
     * @throws Exception
     */
    void update(ScheduleGoodsAllocationStockDO goodsAllocationStock) throws Exception;
}
