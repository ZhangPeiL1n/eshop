package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.GoodsPropertyValueDAO;
import com.zpl.eshop.commodity.domain.GoodsPropertyValueDO;
import com.zpl.eshop.commodity.mapper.GoodsPropertyValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品属性值管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:28
 **/
@Repository
public class GoodsPropertyValueDAOImpl implements GoodsPropertyValueDAO {

    /**
     * 商品属性值管理模块Mapper组件
     */
    @Autowired
    private GoodsPropertyValueMapper goodsPropertyValueMapper;

    /**
     * 新增商品属性值
     *
     * @param goodsPropertyValue 商品属性值
     */
    @Override
    public void save(GoodsPropertyValueDO goodsPropertyValue) {
        goodsPropertyValueMapper.save(goodsPropertyValue);
    }
}
