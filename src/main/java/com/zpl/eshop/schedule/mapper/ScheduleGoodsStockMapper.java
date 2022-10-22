package com.zpl.eshop.schedule.mapper;


import com.zpl.eshop.schedule.domain.ScheduleGoodsStockDO;
import org.apache.ibatis.annotations.*;

/**
 * 调度中心mapper 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/9/15 22:18
 **/
@Mapper
public interface ScheduleGoodsStockMapper {


    /**
     * 根据商品 skuId 获取库存
     *
     * @param goodsSkuId 商品skuId
     * @return 商品库存DO
     */
    @Select("SELECT" +
            " id," +
            " goods_sku_id," +
            " available_stock_quantity," +
            " locked_stock_quantity," +
            " output_stock_quantity," +
            " gmt_create," +
            " gmt_modified" +
            " FROM schedule_goods_stock" +
            " WHERE goods_sku_id = #{goodsSkuId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "goods_sku_id", property = "goodsSkuId"),
            @Result(column = "available_stock_quantity", property = "availableStockQuantity"),
            @Result(column = "locked_stock_quantity", property = "lockedStockQuantity"),
            @Result(column = "output_stock_quantity", property = "outputStockQuantity"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    ScheduleGoodsStockDO getBySkuId(@Param("goodsSkuId") Long goodsSkuId);

    /**
     * 新增商品库存
     *
     * @param scheduleGoodsStockDO 商品库存DO对象
     */
    @Insert("INSERT INTO schedule_goods_stock(" +
            "goods_sku_id," +
            "available_stock_quantity," +
            "locked_stock_quantity," +
            "output_stock_quantity," +
            "gmt_create," +
            "gmt_modified" +
            ")VALUES(" +
            "#{goodsSkuId}," +
            "#{availableStockQuantity}," +
            "#{lockedStockQuantity}," +
            "#{outputStockQuantity}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(ScheduleGoodsStockDO scheduleGoodsStockDO);

    /**
     * 更新商品库存
     *
     * @param scheduleGoodsStockDO 商品库存
     */
    @Update("UPDATE schedule_goods_stock SET" +
            " goods_sku_id = #{goodsSkuId}," +
            " available_stock_quantity = #{availableStockQuantity}," +
            " locked_stock_quantity = #{lockedStockQuantity}," +
            " output_stock_quantity = #{outputStockQuantity}," +
            " gmt_create = #{gmtCreate}," +
            " gmt_modified = #{gmtModified}" +
            " WHERE id = #{id}")
    void update(ScheduleGoodsStockDO scheduleGoodsStockDO);
}