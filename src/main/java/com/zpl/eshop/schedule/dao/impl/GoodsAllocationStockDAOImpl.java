package com.zpl.eshop.schedule.dao.impl;


import com.zpl.eshop.schedule.dao.GoodsAllocationStockDAO;
import com.zpl.eshop.schedule.domain.GoodsAllocationStockDO;
import com.zpl.eshop.schedule.mapper.GoodsAllocationStockMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 调度中心货位库存管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/9/17 18:50
 **/
public class GoodsAllocationStockDAOImpl implements GoodsAllocationStockDAO {

    /**
     * 货位货位库存管理Mapper
     */
    @Autowired
    private GoodsAllocationStockMapper goodsAllocationStockMapper;

    /**
     * 根据货位 skuId 获取库存
     *
     * @param goodsSkuId 货位skuId
     * @return 货位库存DO
     */
    @Override
    public GoodsAllocationStockDO getBySkuId(Long goodsAllocationId, Long goodsSkuId) {
        return goodsAllocationStockMapper.getBySkuId(goodsAllocationId, goodsSkuId);
    }

    /**
     * 新增货位库存
     *
     * @param goodsAllocationStockD 货位库存DO对象
     */
    @Override
    public void save(GoodsAllocationStockDO goodsAllocationStockD) {
        goodsAllocationStockMapper.save(goodsAllocationStockD);
    }

    /**
     * 更新货位库存
     *
     * @param goodsAllocationStockD 货位库存
     */
    @Override
    public void update(GoodsAllocationStockDO goodsAllocationStockD) {
        goodsAllocationStockMapper.update(goodsAllocationStockD);
    }

}
