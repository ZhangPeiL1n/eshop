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
     */
    GoodsDetailPictureDO getById(Long id);

    /**
     * 根据id查询商品图片
     *
     * @param id 商品图片id
     * @return 商品图片
     */
    List<GoodsDetailPictureDO> listByGoodsDetailId(Long goodsDetailId);

    /**
     * 新增图片
     *
     * @param picture 图片
     */
    Long save(GoodsDetailPictureDO picture);

    /**
     * 根据商品id删除图片
     *
     * @param goodsDetailId 商品详情id
     */
    void removeByGoodsDetailId(Long goodsDetailId);
}
