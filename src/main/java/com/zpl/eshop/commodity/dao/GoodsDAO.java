package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsDO;
import com.zpl.eshop.commodity.domain.GoodsQuery;

import java.util.List;

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

    /**
     * 分页查询商品
     *
     * @param query 查询条件
     * @return 商品集合
     */
    List<GoodsDO> listByPage(GoodsQuery query);

    /**
     * 根据id查询商品
     *
     * @param id 商品id
     * @return
     */
    GoodsDO getById(Long id);

    /**
     * 新增商品
     *
     * @param goods 商品
     * @return 商品id
     */
    Long save(GoodsDO goods);

    /**
     * 更新商品
     *
     * @param goods 商品
     */
    void update(GoodsDO goods);

    /**
     * 更新商品状态
     *
     * @param goods 商品
     */
    void updateStatus(GoodsDO goods);

    /**
     * 删除商品
     *
     * @param id 商品id
     */
    void remove(Long id);
}
