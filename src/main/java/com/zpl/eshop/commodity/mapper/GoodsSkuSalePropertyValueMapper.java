package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.GoodsSkuSalePropertyValueDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品sku销售属性管理Mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:47
 **/
@Mapper
public interface GoodsSkuSalePropertyValueMapper {

    /**
     * 新增商品sku销售属性
     *
     * @param propertyValue 商品sku销售属性
     */
    @Insert("INSERT INTO commodity_goods_sku_sale_property_value(" +
            "goods_sku_id," +
            "relation_id," +
            "property_value," +
            "gmt_create," +
            "gmt_modified" +
            ") VALUES (" +
            "#{goodsSkuId}," +
            "#{relationId}," +
            "#{propertyValue}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    void save(GoodsSkuSalePropertyValueDO propertyValue);
}
