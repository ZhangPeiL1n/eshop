package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.GoodsSkuDAO;
import com.zpl.eshop.commodity.domain.GoodsSkuDO;
import com.zpl.eshop.commodity.mapper.GoodsSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品sku管理DAO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:45
 **/
@Repository
public class GoodsSkuDAOImpl implements GoodsSkuDAO {

    /**
     * 商品sku管理Mapper
     */
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    /**
     * 新增商品SKU
     *
     * @param goodsSku 商品sku
     */
    @Override
    public Long save(GoodsSkuDO goodsSku) {
        goodsSkuMapper.save(goodsSku);
        return goodsSku.getId();
    }
}
