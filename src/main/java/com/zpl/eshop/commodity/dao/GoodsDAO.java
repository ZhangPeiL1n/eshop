package com.zpl.eshop.commodity.dao;

/**
 * 商品管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/7/5 23:07
 **/
public interface GoodsDAO {

    /**
     * 查询关联类目的商品总数
     *
     * @param categoryId 类目id
     * @return 商品数量
     */
    Long countByCategoryId(Long categoryId) throws Exception;

    /**
     * 查询关联品牌的商品总数
     *
     * @param brandId 品牌id
     * @return 商品数量
     */
    Long countByBrandId(Long brandId);
}
