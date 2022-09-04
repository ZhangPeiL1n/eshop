package com.zpl.eshop.commodity.dao.impl;

import com.zpl.eshop.commodity.dao.GoodsDetailPictureDAO;
import com.zpl.eshop.commodity.domain.GoodsDetailPictureDO;
import com.zpl.eshop.commodity.mapper.GoodsDetailPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 根据id查询商品图片
     *
     * @param id 商品图片id
     * @return 商品图片
     */
    @Override
    public GoodsDetailPictureDO getById(Long id) {
        return goodsDetailPictureMapper.getById(id);
    }

    /**
     * 根据商品详情id查询商品详情图片
     *
     * @param goodsDetailId 商品详情id
     * @return 商品图片
     */
    @Override
    public List<GoodsDetailPictureDO> listByGoodsDetailId(Long goodsDetailId) {
        return goodsDetailPictureMapper.listByGoodsDetailId(goodsDetailId);
    }

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

    /**
     * 根据商品id删除图片
     *
     * @param goodsDetailId 商品详情id
     */
    @Override
    public void removeByGoodsDetailId(Long goodsDetailId) {
        goodsDetailPictureMapper.removeByGoodsDetailId(goodsDetailId);
    }
}
