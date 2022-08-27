package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsSkuSalePropertyValueDO;

/**
 * 商品sku销售属性管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:50
 **/
public interface GoodsSkuSalePropertyValueDAO {

    /**
     * 新增商品sku销售属性
     *
     * @param propertyValue 商品sku销售属性
     */
    void save(GoodsSkuSalePropertyValueDO propertyValue);
}
