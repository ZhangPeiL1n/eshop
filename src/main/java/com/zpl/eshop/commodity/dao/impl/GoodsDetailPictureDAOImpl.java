package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.GoodsDetailPictureDAO;
import com.zpl.eshop.commodity.domain.GoodsDetailPictureDO;
import com.zpl.eshop.commodity.mapper.GoodsDetailPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品详情图片管理模块DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:19
 **/
@Repository
public class GoodsDetailPictureDAOImpl implements GoodsDetailPictureDAO {

    /**
     * 商品详情图片管理Mapper组件
     */
    @Autowired
    private GoodsDetailPictureMapper goodsDetailPictureMapper;

    /**
     * 新增图片
     *
     * @param picture 图片
     */
    @Override
    public Long save(GoodsDetailPictureDO picture) {
        goodsDetailPictureMapper.save(picture);
        return picture.getId();
    }
}
