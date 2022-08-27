package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.GoodsPictureDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品图片管理Mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 21:55
 **/
@Mapper
public interface GoodsPictureMapper {

    /**
     * 新增图片
     *
     * @param picture 图片
     */
    @Insert("INSERT INTO commodity_goods_picture(" +
            "goods_id," +
            "picture_path," +
            "gmt_create," +
            "gmt_modified" +
            ") VALUES (" +
            "#{goodsId}," +
            "#{picturePath}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    void save(GoodsPictureDO picture);
}
