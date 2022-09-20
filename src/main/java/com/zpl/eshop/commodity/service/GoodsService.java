package com.zpl.eshop.commodity.service;

import com.zpl.eshop.commodity.domain.GoodsDTO;
import com.zpl.eshop.commodity.domain.GoodsQuery;

import java.util.List;

/**
 * 商品管理模块Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 21:23
 **/
public interface GoodsService {

    /**
     * 分页查询商品
     *
     * @param query 查询条件
     * @return 商品集合
     */
    List<GoodsDTO> listByPage(GoodsQuery query) throws Exception;

    /**
     * 根据id查询商品
     *
     * @param id 商品id
     * @return
     */
    GoodsDTO getById(Long id) throws Exception;

    /**
     * 新增商品
     *
     * @param goods 商品
     * @return 商品id
     */
    Long save(GoodsDTO goods) throws Exception;

    /**
     * 更新商品
     *
     * @param goods 商品
     * @return 更新结果
     */
    Boolean update(GoodsDTO goods) throws Exception;

    /**
     * 审核商品
     *
     * @param goodsId 商品id
     * @return 处理结果
     */
    Boolean approve(Long goodsId, Integer approveResult) throws Exception;

    /**
     * 上架商品
     *
     * @param goodsId 商品
     * @return 上架结果
     */
    Boolean putOnShelves(Long goodsId) throws Exception;

    /**
     * 下架商品
     *
     * @param goodsId 商品
     * @return 下架结果
     */
    Boolean pullOffShelves(Long goodsId) throws Exception;

    /**
     * 删除商品
     *
     * @param goodsId 商品id
     * @return 删除结果
     */
    Boolean remove(Long goodsId) throws Exception;
}
