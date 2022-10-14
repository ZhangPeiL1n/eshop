package com.zpl.eshop.wms.mapper;

import com.zpl.eshop.wms.domain.SaleDeliveryOrderSendOutDetailDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 销售出库单发货明细
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:08
 **/
@Mapper
public interface SaleDeliveryOrderSendOutDetailMapper {

    /**
     * 新增销售出库单发货明细
     *
     * @param sendOutDetail 销售出库单发货明细
     */
    @Insert("INSERT INTO wms_sale_delivery_order_send_out_detail("
            + "sale_delivery_order_item_id,"
            + "goods_allocation_stock_detail_id,"
            + "send_out_count,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{saleDeliveryOrderItemId},"
            + "#{goodsAllocationStockDetailId},"
            + "#{sendOutCount},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(SaleDeliveryOrderSendOutDetailDO sendOutDetail);
}
