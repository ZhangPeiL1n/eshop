package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsDetailPictureDO;

import java.util.List;

/**
 * 商品详情图片管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:17
 **/
public interface GoodsDetailPictureDAO {

    /**
     * 根据id查询商品图片
     *
     * @param id 商品图片id
     * @return 商品图片
     * @throws Exception
     */
    GoodsDetailPictureDO getById(Long id) throws Exception;

    /**
     * 根据id查询商品图片
     *
     * @param goodsDetailId 商品详情id
     * @return 商品详情图片集合
     * @throws Exception
     */
    List<GoodsDetailPictureDO> listByGoodsDetailId(Long goodsDetailId) throws Exception;

    /**
     * 新增图片
     *
     * @param picture 图片
     * @return id
     * @throws Exception
     */
    Long save(GoodsDetailPictureDO picture) throws Exception;

    /**
     * 根据商品id删除图片
     *
     * @param goodsDetailId 商品详情id
     * @throws Exception
     */
    void removeByGoodsDetailId(Long goodsDetailId) throws Exception;
}
