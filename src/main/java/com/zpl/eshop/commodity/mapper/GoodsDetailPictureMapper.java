package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.GoodsDetailPictureDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品详情图片管理Mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:11
 **/
@Mapper
public interface GoodsDetailPictureMapper {

    /**
     * 新增图片
     *
     * @param picture 图片
     */
    @Insert("INSERT INTO commodity_goods_detail_picture(" +
            "goods_detail_id," +
            "picture_path," +
            "gmt_create," +
            "gmt_modified" +
            ") VALUES (" +
            "#{goodsDetailId}," +
            "#{picturePath}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    void save(GoodsDetailPictureDO picture);

}
