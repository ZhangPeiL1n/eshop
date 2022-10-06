package com.zpl.eshop.wms.mapper;

import com.zpl.eshop.wms.domain.PurchaseInputOrderItemDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 采购入库单条目管理Mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 16:58
 **/
@Mapper
public interface PurchaseInputOrderItemMapper {

    /**
     * 新增采购入库单条目
     *
     * @param purchaseInputOrderItem 采购入库单条目
     * @throws Exception
     */
    @Insert("INSRET INTO wms_purchase_input_order_item("
            + "purchase_input_order_id,"
            + "goods_sku_id,"
            + "purchase_count,"
            + "purchase_price,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{purchaseInputOrderId},"
            + "#{goodsSkuId},"
            + "#{purchaseCount},"
            + "#{purchasePrice},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(PurchaseInputOrderItemDO purchaseInputOrderItem);

    /**
     * 根据采购入库单id查询采购入库单条目
     *
     * @param purchaseInputOrderId 采购入库单id
     * @return 采购入库单条目
     */
    @Select("SELECT "
            + "id,"
            + "purchase_input_order_id,"
            + "goods_sku_id,"
            + "purchase_count,"
            + "purchase_price,"
            + "qualified_count,"
            + "arrival_count,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM wms_purchase_input_order_item "
            + "WHERE purchase_input_order_id=#{purcahseInputOrderId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "purchase_input_order_id", property = "purchaseInputOrderId"),
            @Result(column = "goods_sku_id", property = "goodsSkuId"),
            @Result(column = "purchase_count", property = "purchaseCount"),
            @Result(column = "purcahse_price", property = "purchasePrice"),
            @Result(column = "qualified_count", property = "qualifiedCount"),
            @Result(column = "arrival_count", property = "arrivalCount"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<PurchaseInputOrderItemDO> listByPurchaseInputOrderId(
            @Param("purchaseInputOrderId") Long purchaseInputOrderId);

    /**
     * 更新采购入库单条目
     *
     * @param purchaseInputOrderItem 采购入库单条目
     */
    @Update("UPDATE wms_purchase_input_order_item SET "
            + "qualified_count=#{qualifiedCount},"
            + "arrival_count=#{arrivalCount},"
            + "gmt_modified=#{gmtModified} "
            + "WHERE id=#{id}")
    void update(PurchaseInputOrderItemDO purchaseInputOrderItem);
}
