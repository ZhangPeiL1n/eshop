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
     * 根据商品id查询属性值
     *
     * @param goodsId 商品id
     * @return 属性值
     * @throws Exception
     */
    List<GoodsPropertyValueDTO> listByGoodsId(Long goodsId) throws Exception;

    /**
     * 新增商品属性值
     *
     * @param propertyValues 商品属性值
     * @throws Exception
     */
    void batchSave(List<GoodsPropertyValueDTO> propertyValues) throws Exception;

    /**
     * 根据商品id删除图片
     *
     * @param goodsId 商品id
     * @throws Exception
     */
    void removeByGoodsId(Long goodsId) throws Exception;
}
