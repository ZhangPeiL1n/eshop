package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.GoodsDAO;
import com.zpl.eshop.commodity.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品管理DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/7/5 23:08
 **/
@Repository
public class GoodsDAOImpl implements GoodsDAO {

    /**
     * 商品管理模块Mapper组件
     */
    @Autowired
    private GoodsMapper goodsMapper;


    /**
     * 查询关联类目的商品总数
     *
     * @param categoryId 类目id
     * @return 商品数量
     */
    @Override
    public Long countByCategoryId(Long categoryId) throws Exception {
        return goodsMapper.countByCategoryId(categoryId);
    }

    /**
     * 查询关联品牌的商品总数
     *
     * @param brandId 品牌id
     * @return 商品数量
     */
    @Override
    public Long countByBrandId(Long brandId) {
        return goodsMapper.countByBrandId(brandId);
    }
}
