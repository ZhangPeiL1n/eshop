package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.GoodsDetailPictureDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品详情图片管理Mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:11
 **/
@Mapper
public interface GoodsDetailPictureMapper {

    /**
     * 根据id查询商品图片
     *
     * @param id 商品图片id
     * @return 商品图片
     */
    @Select("SELECT "
            + "id,"
            + "goods_detail_id,"
            + "picture_path,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM commodity_goods_detail_picture "
            + "WHERE id=#{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "goods_detail_id", property = "goodsDetailId"),
            @Result(column = "picture_path", property = "picturePath"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    GoodsDetailPictureDO getById(@Param("id") Long id);

    /**
     * 根据商品详情id查询商品详情图片
     *
     * @param goodsDetailId 商品详情id
     * @return 商品图片
     */
    @Select("SELECT "
            + "id,"
            + "goods_detail_id,"
            + "picture_path,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM commodity_goods_detail_picture "
            + "WHERE goods_detail_id=#{goodsDetailId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "goods_detail_id", property = "goodsDetailId"),
            @Result(column = "picture_path", property = "picturePath"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<GoodsDetailPictureDO> listByGoodsDetailId(@Param("goodsDetailId") Long goodsDetailId);

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
    @Options(keyProperty = "id", keyColumn = "id", useGeneratedKeys = true)
    void save(GoodsDetailPictureDO picture);

    /**
     * 根据商品id删除图片
     *
     * @param goodsDetailId 商品详情id
     */
    @Delete("DELETE FROM commodity_goods_detail_picture where goods_detail_id = #{goodsDetailId}")
    void removeByGoodsDetailId(@Param("goodsDetailId") Long goodsDetailId);

}