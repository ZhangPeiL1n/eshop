package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsPropertyValueDO;

/**
 * 商品属性值管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:27
 **/
public interface GoodsPropertyValueDAO {

    /**
     * 新增商品属性值
     *
     * @param goodsPropertyValue 商品属性值
     */
    void save(GoodsPropertyValueDO goodsPropertyValue);
}
