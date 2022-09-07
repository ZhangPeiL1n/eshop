package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.GoodsDAO;
import com.zpl.eshop.commodity.domain.GoodsDO;
import com.zpl.eshop.commodity.domain.GoodsQuery;
import com.zpl.eshop.commodity.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 分页查询商品
     *
     * @param query 查询条件
     * @return 商品集合
     */
    @Override
    public List<GoodsDO> listByPage(GoodsQuery query) {
        return goodsMapper.listByPage(query);
    }

    /**
     * 根据id查询商品
     *
     * @param id 商品id
     * @return
     */
    @Override
    public GoodsDO getById(Long id) {
        return goodsMapper.getById(id);
    }

    /**
     * 新增商品
     *
     * @param goods 商品
     */
    @Override
    public Long save(GoodsDO goods) {
        goodsMapper.save(goods);
        return goods.getId();
    }

    /**
     * 更新商品
     *
     * @param goods 商品
     */
    @Override
    public void update(GoodsDO goods) {
        goodsMapper.update(goods);
    }

    /**
     * 更新商品状态
     *
     * @param goods 商品
     */
    @Override
    public void updateStatus(GoodsDO goods) {
        goodsMapper.updateStatus(goods);
    }
}
