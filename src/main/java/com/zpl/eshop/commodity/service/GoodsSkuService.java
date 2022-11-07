package com.zpl.eshop.commodity.service;

import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.domain.GoodsSkuQuery;

import java.util.List;

/**
 * 商品Sku管理组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 18:23
 **/
public interface GoodsSkuService {

    /**
     * 根据商品id查询商品sku
     *
     * @param goodsId 商品id
     * @return 商品sku
     * @throws Exception
     */
    List<GoodsSkuDTO> listByGoodsId(Long goodsId) throws Exception;

    /**
     * 新增商品SKU
     *
     * @param goodsSkus 商品sku
     * @throws Exception
     */
    void batchSave(List<GoodsSkuDTO> goodsSkus) throws Exception;

    /**
     * 根据商品id删除sku
     *
     * @param goodsId 商品id
     * @throws Exception
     */
    void removeByGoodsId(Long goodsId) throws Exception;

    /**
     * 根据id查询商品sku
     *
     * @param id 商品skuId
     * @return 商品sku
     * @throws Exception
     */
    GoodsSkuDTO getById(Long id) throws Exception;

    /**
     * 分页查询商品sku
     *
     * @param query 查询条件
     * @return 商品sku
     * @throws Exception
     */
    List<GoodsSkuDTO> listByPage(GoodsSkuQuery query) throws Exception;
}
