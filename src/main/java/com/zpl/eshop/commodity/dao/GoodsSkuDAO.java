package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsSkuDO;
import com.zpl.eshop.commodity.domain.GoodsSkuQuery;

import java.util.List;

/**
 * 商品sku管理DAO接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:44
 **/
public interface GoodsSkuDAO {

    /**
     * 根据商品id查询商品sku
     *
     * @param goodsId 商品id
     * @return 商品sku
     * @throws Exception
     */
    List<GoodsSkuDO> listByGoodsId(Long goodsId) throws Exception;

    /**
     * 根据id查询商品sku
     *
     * @param id 商品sku id
     * @return 商品sku
     * @throws Exception
     */
    GoodsSkuDO getById(Long id) throws Exception;

    /**
     * 分页查询商品sku
     *
     * @param query 查询条件
     * @return 商品sku
     * @throws Exception
     */
    List<GoodsSkuDO> listByPage(GoodsSkuQuery query) throws Exception;

    /**
     * 新增商品SKU
     *
     * @param goodsSku 商品sku
     * @return 商品skuId
     * @throws Exception
     */
    Long save(GoodsSkuDO goodsSku) throws Exception;

    /**
     * 根据商品id删除sku
     *
     * @param goodsId 商品id
     * @throws Exception
     */
    void removeByGoodsId(Long goodsId) throws Exception;
}
