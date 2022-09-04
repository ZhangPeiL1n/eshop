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
     */
    void update(GoodsDTO goods) throws Exception;
}
