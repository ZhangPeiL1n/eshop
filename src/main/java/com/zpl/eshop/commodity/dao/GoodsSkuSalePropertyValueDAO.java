package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsSkuSalePropertyValueDO;

import java.util.List;

/**
 * 商品sku销售属性管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:50
 **/
public interface GoodsSkuSalePropertyValueDAO {

    /**
     * 根据商品sku id查询属性值
     *
     * @param goodsSkuId 商品sku id
     * @return 属性值
     */
    List<GoodsSkuSalePropertyValueDO> listByGoodsSkuId(Long goodsSkuId);

    /**
     * 新增商品sku销售属性
     *
     * @param propertyValue 商品sku销售属性
     */
    void save(GoodsSkuSalePropertyValueDO propertyValue);

    /**
     * 根据商品sku id删除属性值
     *
     * @param goodsSkuId 商品sku id
     */
    void removeByGoodsSkuId(Long goodsSkuId);
}
