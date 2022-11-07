package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.WmsGoodsAllocationStockDAO;
import com.zpl.eshop.wms.domain.WmsGoodsAllocationStockDO;
import com.zpl.eshop.wms.mapper.WmsGoodsAllocationStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/7 19:27
 **/
@Repository
public class WmsGoodsAllocationStockDAOImpl implements WmsGoodsAllocationStockDAO {

    /**
     * wms中心商品货位库存Mapper
     */
    @Autowired
    private WmsGoodsAllocationStockMapper wmsGoodsAllocationStockMapper;

    /**
     * 根据商品sku id查询商品库存
     *
     * @param goodsSkuId 商品sku id
     * @return 商品库存
     * @throws Exception
     */

    @Override
    public WmsGoodsAllocationStockDO getBySkuId(Long goodsAllocationId, Long goodsSkuId) throws Exception {
        return wmsGoodsAllocationStockMapper.getBySkuId(goodsAllocationId, goodsSkuId);
    }

    /**
     * 新增商品库存
     *
     * @param goodsAllocationStock 商品库存DO对象
     * @throws Exception
     */
    @Override

    public void save(WmsGoodsAllocationStockDO goodsAllocationStock) throws Exception {
        wmsGoodsAllocationStockMapper.save(goodsAllocationStock);
    }

    /**
     * 更新商品库存
     *
     * @param goodsAllocationStock 商品库存DO对象
     */
    @Override
    public void update(WmsGoodsAllocationStockDO goodsAllocationStock) throws Exception {
        wmsGoodsAllocationStockMapper.update(goodsAllocationStock);
    }
}
