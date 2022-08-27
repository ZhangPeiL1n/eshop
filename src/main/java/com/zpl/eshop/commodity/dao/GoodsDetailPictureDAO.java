package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsDetailPictureDO;

/**
 * 商品详情图片管理模块DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:17
 **/
public interface GoodsDetailPictureDAO {

    /**
     * 新增图片
     *
     * @param picture 图片
     */
    Long save(GoodsDetailPictureDO picture);
}
