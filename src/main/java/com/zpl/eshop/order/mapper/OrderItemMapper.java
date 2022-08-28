package com.zpl.eshop.order.mapper;

import com.zpl.eshop.order.domain.OrderItemDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 订单条目管理 mapper 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 17:08
 **/
@Mapper
public interface OrderItemMapper {

    /**
     * 新增订单条目
     *
     * @param orderItem 订单条目
     */
    @Insert("INSERT INTO order_item(" +
            "order_info_id, " +
            "goods_id, " +
            "goods_sku_id, " +
            "goods_sku_code, " +
            "goods_name, " +
            "sale_properties, " +
            "goods_gross_weight, " +
            "purchase_quantity, " +
            "purchase_price, " +
            "promotion_activity_id, " +
            "goods_length, " +
            "goods_width, " +
            "goods_height, " +
            "gmt_create, " +
            "gmt_modified" +
            ") VALUES (" +
            "#{orderInfoId}," +
            "#{goodsId}," +
            "#{goodsSkuId}," +
            "#{goodsSkuCode}," +
            "#{goodsName}," +
            "#{saleProperties}," +
            "#{goodsGrossWeight}," +
            "#{purchaseQuantity}," +
            "#{purchasePrice}," +
            "#{promotionActivityId}," +
            "#{goodsLength}," +
            "#{goodsWidth}," +
            "#{goodsHeight}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(OrderItemDO orderItem);
}
