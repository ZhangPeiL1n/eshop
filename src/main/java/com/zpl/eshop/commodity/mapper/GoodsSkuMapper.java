package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.GoodsSkuDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品SKU管理mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:33
 **/
@Mapper
public interface GoodsSkuMapper {

    /**
     * 根据商品id查询商品sku
     *
     * @param goodsId 商品id
     * @return 商品sku
     */
    @Select("SELECT "
            + "id,"
            + "goods_id,"
            + "sku_code,"
            + "purchase_price,"
            + "sale_price,"
            + "discount_price,"
            + "sale_properties,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM commodity_goods_sku "
            + "WHERE goods_id=#{goodsId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "sku_code", property = "skuCode"),
            @Result(column = "purchase_price", property = "purchasePrice"),
            @Result(column = "sale_price", property = "salePrice"),
            @Result(column = "discount_price", property = "discountPrice"),
            @Result(column = "sale_properties", property = "saleProperties"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<GoodsSkuDO> listByGoodsId(@Param("goodsId") Long goodsId);

    /**
     * 新增商品SKU
     *
     * @param goodsSku 商品sku
     */
    @Insert("INSERT INTO commodity_goods_sku(" +
            "goods_id," +
            "sku_code," +
            "purchase_price," +
            "sale_price," +
            "discount_price," +
            "sale_properties," +
            "gmt_create," +
            "gmt_modified" +
            ") VALUES (" +
            "#{goodsId}," +
            "#{skuCode}," +
            "#{purchasePrice}," +
            "#{salePrice}," +
            "#{discountPrice}," +
            "#{saleProperties}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(GoodsSkuDO goodsSku);

    /**
     * 根据商品id删除sku
     *
     * @param goodsId 商品id
     */
    @Delete("DELETE FROM commodity_goods_sku WHERE goods_id=#{goodsId}")
    void removeByGoodsId(@Param("goodsId") Long goodsId);
}
