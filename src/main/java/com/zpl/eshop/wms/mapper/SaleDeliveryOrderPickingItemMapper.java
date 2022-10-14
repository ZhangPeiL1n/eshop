package com.zpl.eshop.wms.mapper;

import com.zpl.eshop.wms.domain.SaleDeliveryOrderPickingItemDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 销售出库单拣货条目管理Mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:07
 **/
@Mapper
public interface SaleDeliveryOrderPickingItemMapper {

    /**
     * 新增销售出库单拣货条目
     *
     * @param pickingItem
     */
    @Insert("INSERT INTO wms_sale_delivery_order_picking_item("
            + "sale_delivery_order_item_id,"
            + "goods_allocation_id,"
            + "goods_sku_id,"
            + "picking_count,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{saleDeliveryOrderItemId},"
            + "#{goodsAllocationId},"
            + "#{goodsSkuId},"
            + "#{pickingCount},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(SaleDeliveryOrderPickingItemDO pickingItem);
}
