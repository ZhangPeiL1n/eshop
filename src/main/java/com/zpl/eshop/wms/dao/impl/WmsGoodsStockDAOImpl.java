package com.zpl.eshop.wms.dao.impl;

import com.zpl.eshop.wms.dao.WmsGoodsStockDAO;
import com.zpl.eshop.wms.domain.WmsGoodsStockDO;
import com.zpl.eshop.wms.mapper.WmsGoodsStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * wms中心商品管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 17:07
 **/
@Repository
public class WmsGoodsStockDAOImpl implements WmsGoodsStockDAO {

    /**
     * wms中心商品管理Mapper组件
     */
    @Autowired
    private WmsGoodsStockMapper wmsGoodsStockMapper;

    /**
     * 根据商品sku id查询商品库存
     *
     * @param goodsSkuId 商品sku id
     * @return 商品库存
     */
    @Override
    public WmsGoodsStockDO getBySkuId(Long goodsSkuId) {
        return wmsGoodsStockMapper.getBySkuId(goodsSkuId);
    }

    /**
     * 新增商品库存
     *
     * @param goodsStockDO 商品库存DO对象
     */
    @Override
    public void save(WmsGoodsStockDO goodsStockDO) {
        wmsGoodsStockMapper.save(goodsStockDO);
    }

    /**
     * 更新商品库存
     *
     * @param goodsStockDO 商品库存DO对象
     */
    @Override
    public void update(WmsGoodsStockDO goodsStockDO) {
        wmsGoodsStockMapper.update(goodsStockDO);
    }
}
