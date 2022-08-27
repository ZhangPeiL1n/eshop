package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.GoodsSkuDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 商品SKU管理mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:33
 **/
@Mapper
public interface GoodsSkuMapper {

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
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(GoodsSkuDO goodsSku);
}
