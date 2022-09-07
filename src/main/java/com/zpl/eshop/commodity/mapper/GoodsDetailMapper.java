package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.GoodsDetailDO;
import org.apache.ibatis.annotations.*;

/**
 * 商品详情页Mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 22:06
 **/
@Mapper
public interface GoodsDetailMapper {

    /**
     * 根据商品id查询商品详情
     *
     * @param goodsId 商品id
     * @return 商品详情
     */
    @Select("SELECT "
            + "id,"
            + "goods_id,"
            + "detail_content,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM commodity_goods_detail "
            + "WHERE goods_id=#{goodsId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "detail_content", property = "detailContent"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    GoodsDetailDO getByGoodsId(@Param("goodsId") Long goodsId);

    /**
     * 新增商品详情
     *
     * @param detail 商品详情
     */
    @Insert("INSERT INTO commodity_goods_detail(" +
            "goods_id," +
            "gmt_create," +
            "gmt_modified" +
            ") VALUES (" +
            "#{goodsId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(GoodsDetailDO detail);

    /**
     * 更新商品详情
     *
     * @param detail 商品详情
     */
    @Update("UPDATE commodity_goods_detail SET " +
            "detail_content = #{detailContent} " +
            "WHERE id = #{id}")
    void update(GoodsDetailDO detail);

    /**
     * 删除商品详情
     *
     * @param id 商品详情id
     */
    @Delete("DELETE FROM commodity_goods_detail where id = #{id}")
    void remove(@Param("id") Long id);
}
