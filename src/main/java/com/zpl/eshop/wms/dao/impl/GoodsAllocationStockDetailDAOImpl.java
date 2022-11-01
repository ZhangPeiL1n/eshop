package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.GoodsAllocationStockDetailDAO;
import com.zpl.eshop.wms.domain.GoodsAllocationStockDetailDO;
import com.zpl.eshop.wms.mapper.GoodsAllocationStockDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * wms中心商品货位库存明细DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 19:34
 **/
@Repository
public class GoodsAllocationStockDetailDAOImpl implements GoodsAllocationStockDetailDAO {

    /**
     * wms商品货位库存明细mapper组件
     */
    @Autowired
    private GoodsAllocationStockDetailMapper goodsAllocationStockDetailMapper;

    /**
     * 根据商品sku id查询货位库存明细
     *
     * @param goodsSkuId 商品sku id
     * @return 货位库存明细
     */
    @Override
    public List<GoodsAllocationStockDetailDO> listByGoodsSkuId(Long goodsSkuId) {
        return goodsAllocationStockDetailMapper.listByGoodsSkuId(goodsSkuId);
    }

    /**
     * 根据id查询货位库存明细
     *
     * @param id 货位库粗明细id
     * @return 货位库存明细
     */
    @Override
    public GoodsAllocationStockDetailDO getById(Long id) {
        return goodsAllocationStockDetailMapper.getById(id);
    }

    /**
     * 更新货位库存明细
     *
     * @param stockDetail 货位库存明细
     */
    @Override
    public void update(GoodsAllocationStockDetailDO stockDetail) {
        goodsAllocationStockDetailMapper.update(stockDetail);
    }

    /**
     * 新增货位库存明细
     *
     * @param stockDetail 货位库存明细
     */
    @Override
    public void save(GoodsAllocationStockDetailDO stockDetail) {
        goodsAllocationStockDetailMapper.save(stockDetail);
    }

}