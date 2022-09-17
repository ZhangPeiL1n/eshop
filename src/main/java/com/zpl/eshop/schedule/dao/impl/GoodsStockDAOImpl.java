package com.zpl.eshop.schedule.dao.impl;


import com.zpl.eshop.schedule.dao.GoodsStockDAO;
import com.zpl.eshop.schedule.domain.GoodsStockDO;
import com.zpl.eshop.schedule.mapper.GoodsStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 调度中心商品库存管理组件
 *
 * @author ZhangPeiL1n
 * @date 2022/9/17 18:58
 **/
@Repository
public class GoodsStockDAOImpl implements GoodsStockDAO {

    /**
     * 商品库存管理模块mapper组件
     */
    @Autowired
    private GoodsStockMapper goodsStockMapper;


    /**
     * 根据商品 skuId 获取库存
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO
     */
    @Override
    public GoodsStockDO getBySkuId(Long goodsSkuId) {
        return goodsStockMapper.getBySkuId(goodsSkuId);
    }

    /**
     * 新增商品库存
     *
     * @param goodsStockDO 商品库存DO对象
     */
    @Override
    public void save(GoodsStockDO goodsStockDO) {
        goodsStockMapper.save(goodsStockDO);
    }

    /**
     * 更新商品库存
     *
     * @param goodsStockDO 商品库存
     */
    @Override
    public void update(GoodsStockDO goodsStockDO) {
        goodsStockMapper.update(goodsStockDO);
    }
}
