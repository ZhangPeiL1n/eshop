package com.zpl.eshop.schedule.dao;


import com.zpl.eshop.schedule.domain.GoodsAllocationStockDO;
import org.springframework.stereotype.Repository;

/**
 * 调度中心货位库存管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/17 18:50
 **/
@Repository
public interface GoodsAllocationStockDAO {
    /**
     * 根据货位 skuId 获取库存
     *
     * @param goodsSkuId 货位skuId
     * @return 货位库存DO
     */
    GoodsAllocationStockDO getBySkuId(Long goodsAllocationId, Long goodsSkuId);

    /**
     * 新增货位库存
     *
     * @param goodsAllocationStockDO 货位库存DO对象
     */
    void save(GoodsAllocationStockDO goodsAllocationStockDO);

    /**
     * 更新货位库存
     *
     * @param goodsAllocationStockDO 货位库存
     */
    void update(GoodsAllocationStockDO goodsAllocationStockDO);

}
