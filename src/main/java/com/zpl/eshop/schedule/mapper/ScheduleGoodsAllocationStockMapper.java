package com.zpl.eshop.schedule.mapper;


import com.zpl.eshop.schedule.domain.ScheduleGoodsAllocationStockDO;
import org.apache.ibatis.annotations.*;

/**
 * 调度中心mapper 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/9/15 22:18
 **/
@Mapper
public interface ScheduleGoodsAllocationStockMapper {


    /**
     * 根据商品 skuId 获取库存
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO
     */
    @Select("SELECT" +
            " id," +
            "goods_allocation_id," +
            " goods_sku_id," +
            " available_stock_quantity," +
            " locked_stock_quantity," +
            " deliveried_stock_quantity," +
            " gmt_create," +
            " gmt_modified" +
            " FROM schedule_goods_allocation_stock" +
            " WHERE goods_sku_id = #{goodsSkuId} " +
            "and goods_allocation_id = #{goodsAllocationId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "goods_allocation_id", property = "goodsAllocationId"),
            @Result(column = "goods_sku_id", property = "goodsSkuId"),
            @Result(column = "available_stock_quantity", property = "availableStockQuantity"),
            @Result(column = "locked_stock_quantity", property = "lockedStockQuantity"),
            @Result(column = "deliveried_stock_quantity", property = "outPutStockQuantity"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    ScheduleGoodsAllocationStockDO getBySkuId(@Param("goodsAllocationId") Long goodAllocationId, @Param("goodsSkuId") Long goodsSkuId);

    /**
     * 新增商品库存
     *
     * @param scheduleGoodsAllocationStockDO 商品货位库存DO对象
     */
    @Insert("INSERT INTO schedule_goods_allocation_stock(" +
            "goods_allocation_id," +
            "goods_sku_id," +
            "available_stock_quantity," +
            "locked_stock_quantity," +
            "deliveried_stock_quantity," +
            "gmt_create," +
            "gmt_modified" +
            ")VALUES(" +
            "#{goodsAllcationId}," +
            "#{goodsSkuId}," +
            "#{availableStockQuantity}," +
            "#{lockedStockQuantity}," +
            "#{outPutStockQuantity}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(ScheduleGoodsAllocationStockDO scheduleGoodsAllocationStockDO);

    /**
     * 更新商品库存
     *
     * @param scheduleGoodsAllocationStockDO 商品货位库存
     */
    @Update("UPDATE schedule_goods_allocation_stock SET" +
            " goods_sku_id = #{goodsSkuId}," +
            " available_stock_quantity = #{availableStockQuantity}," +
            " locked_stock_quantity = #{lockedStockQuantity}," +
            " deliveried_stock_quantity = #{outPutStockQuantity}," +
            " gmt_create = #{gmtCreate}," +
            " gmt_modified = #{gmtModified}" +
            " WHERE id = #{id}")
    void update(ScheduleGoodsAllocationStockDO scheduleGoodsAllocationStockDO);
}
