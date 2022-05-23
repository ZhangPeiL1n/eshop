package com.zpl.eshop.inventory.mapper;

import com.zpl.eshop.inventory.domain.GoodsStockDO;
import org.apache.ibatis.annotations.*;

/**
 * 商品 sku 库存管理模块的 mapper 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/23 22:18
 **/
@Mapper
public interface GoodsStockMapper {


    /**
     * 根据商品 skuId 获取库存
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO
     */
    @Select("SELECT" +
            " id," +
            " goods_sku_id," +
            " sale_stock_quantity," +
            " locked_stock_quantity," +
            " saled_stock_quantity," +
            " stock_status," +
            " gmt_create," +
            " gmt_modified" +
            " FROM inventory_goods_stock" +
            " WHERE goods_sku_id = #{goodsSkuId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "goods_sku_id", property = "goodsSkuId"),
            @Result(column = "sale_stock_quantity", property = "saleStockQuantity"),
            @Result(column = "locked_stock_quantity", property = "lockedStockQuantity"),
            @Result(column = "saled_stock_quantity", property = "saledStockQuantity"),
            @Result(column = "stock_status", property = "stockStatus"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    GoodsStockDO getGoodsStockBySkuId(@Param("goodsSkuId") Long goodsSkuId);

    /**
     * 新增商品库存
     *
     * @param goodsStockDO 商品库存DO对象
     */
    @Insert("INSERT INTO inventory_goods_stock(" +
            "goods_sku_id," +
            "sale_stock_quantity," +
            "locked_stock_quantity," +
            "saled_stock_quantity," +
            "stock_status," +
            "gmt_create," +
            "gmt_modified" +
            ")VALUES(" +
            "#{goodsSkuId}," +
            "#{saleStockQuantity}," +
            "#{lockedStockQuantity}," +
            "#{saledStockQuantity}," +
            "#{stockStatus}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void saveGoodsStock(GoodsStockDO goodsStockDO);

    /**
     * 更新商品库存
     *
     * @param goodsStockDO 商品库存
     */
    @Update("UPDATE inventory_goods_stock SET" +
            " goods_sku_id = #{goodsSkuId}," +
            " sale_stock_quantity = #{saleStockQuantity}," +
            " locked_stock_quantity = #{lockedStockQuantity}," +
            " saled_stock_quantity = #{saledStockQuantity}," +
            " stock_status = #{stockStatus}," +
            " gmt_create = #{gmtCreate}," +
            " gmt_modified = #{gmtModified}" +
            " WHERE id = #{id}")
    void updateGoodsStock(GoodsStockDO goodsStockDO);
}
