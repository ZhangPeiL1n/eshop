package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.GoodsDetailDAO;
import com.zpl.eshop.commodity.domain.GoodsDetailDO;
import com.zpl.eshop.commodity.mapper.GoodsDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品详情管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:17
 **/
@Repository
public class GoodsDetailDAOImpl implements GoodsDetailDAO {

    /**
     * 商品详情管理模块Mapper组件
     */
    @Autowired
    private GoodsDetailMapper goodsDetailMapper;

    /**
     * 新增商品详情
     *
     * @param detail 商品详情
     */
    @Override
    public Long save(GoodsDetailDO detail) {
        goodsDetailMapper.save(detail);
        return detail.getId();
    }

    /**
     * 更新商品详情
     *
     * @param detail 商品详情
     */
    @Override
    public void update(GoodsDetailDO detail) {
        goodsDetailMapper.update(detail);
    }
}
