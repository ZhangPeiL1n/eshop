package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.GoodsSkuSalePropertyValueDAO;
import com.zpl.eshop.commodity.domain.GoodsSkuSalePropertyValueDO;
import com.zpl.eshop.commodity.mapper.GoodsSkuSalePropertyValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品sku销售属性管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:51
 **/
@Repository
public class GoodsSkuSalePropertyValueDAOImpl implements GoodsSkuSalePropertyValueDAO {

    /**
     * 商品sku销售属性管理Mapper
     */
    @Autowired
    private GoodsSkuSalePropertyValueMapper goodsSkuSalePropertyValueMapper;

    /**
     * 根据商品sku id查询属性值
     *
     * @param goodsSkuId 商品sku id
     * @return 属性值
     */
    @Override
    public List<GoodsSkuSalePropertyValueDO> listByGoodsSkuId(Long goodsSkuId) {
        return goodsSkuSalePropertyValueMapper.listByGoodsSkuId(goodsSkuId);
    }

    /**
     * 新增商品sku销售属性
     *
     * @param propertyValue 商品sku销售属性
     */
    @Override
    public void save(GoodsSkuSalePropertyValueDO propertyValue) {
        goodsSkuSalePropertyValueMapper.save(propertyValue);
    }

    /**
     * 根据商品sku id删除属性值
     *
     * @param goodsSkuId 商品sku id
     */
    @Override
    public void removeByGoodsSkuId(Long goodsSkuId) {
        goodsSkuSalePropertyValueMapper.removeByGoodsSkuId(goodsSkuId);
    }
}
