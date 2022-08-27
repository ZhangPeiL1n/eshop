package com.zpl.eshop.commodity.service;

import com.zpl.eshop.commodity.domain.GoodsPropertyValueDTO;

import java.util.List;

/**
 * 商品属性值管理Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 17:55
 **/
public interface GoodsPropertyValueService {

    /**
     * 新增商品属性值
     *
     * @param propertyValues 商品属性值
     */
    void batchSave(List<GoodsPropertyValueDTO> propertyValues);
}
