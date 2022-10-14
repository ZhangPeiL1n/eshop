package com.zpl.eshop.wms.mapper;

import com.zpl.eshop.wms.domain.SendOutOrderItemDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 发货单条目Mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:09
 **/
@Mapper
public interface SendOutOrderItemMapper {

    /**
     * 新增发货单条目
     *
     * @param sendOutOrderItem 发货单条目
     */
    @Insert("INSERT INTO wms_send_out_order_item("
            + "send_out_order_id,"
            + "goods_id,"
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
            + "#{sendOutOrderId},"
            + "#{goodsId},"
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
    void save(SendOutOrderItemDO sendOutOrderItem);
}
