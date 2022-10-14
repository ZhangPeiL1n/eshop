package com.zpl.eshop.wms.mapper;

import com.zpl.eshop.wms.domain.SaleDeliveryOrderItemDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 销售出库单条目管理Mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:06
 **/
@Mapper
public interface SaleDeliveryOrderItemMapper {

    /**
     * 新增销售出库单条目
     *
     * @param saleDeliveryOrderItem 销售出库单条目
     */
    @Insert("INSERT INTO wms_sale_delivery_order_item("
            + "sale_delivery_order_id,"
            + "goods_sku_id,"
            + "goods_sku_code,"
            + "goods_name,"
            + "sale_properties,"
            + "goods_gross_weight,"
            + "purchase_quantity,"
            + "purchase_price,"
            + "promotion_activity_id,"
            + "goods_length,"
            + "goods_width,"
            + "goods_height,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{saleDeliveryOrderId},"
            + "#{goodsSkuId},"
            + "#{goodsSkuCode},"
            + "#{goodsName},"
            + "#{saleProperties},"
            + "#{goodsGrossWeight},"
            + "#{purchaseQuantity},"
            + "#{purchasePrice},"
            + "#{promotionActivityId},"
            + "#{goodsLength},"
            + "#{goodsWidth},"
            + "#{goodsHeight},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(SaleDeliveryOrderItemDO saleDeliveryOrderItem);

}
