package com.zpl.eshop.inventory.mapper;

import com.zpl.eshop.inventory.domain.StockUpdateMessageDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 离线存储更新库存消息Mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 22:36
 **/
@Mapper
public interface StockUpdateMessageMapper {

    /**
     * 保存库存更新消息
     *
     * @param stockUpdateMessageDO 库存更新消息DO对象
     */
    @Insert("INSERT INTO inventory_offline_stock_update_message(" +
            "message_id, " +
            "operation, " +
            "parameter, " +
            "parameter_clazz," +
            "gmt_create, " +
            "gmt_modified" +
            ") values (" +
            "#{message_id}," +
            "#{operation}," +
            "#{parameter}," +
            "#{parameterClazz}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    void save(StockUpdateMessageDO stockUpdateMessageDO);


    /**
     * 离线恢复线程使用，每次批量查询50条
     *
     * @return 50条数据
     */
    @Select("SELECT " +
            "id," +
            "message_id," +
            "operation," +
            "parameter," +
            "parameter_clazz," +
            "gmt_create," +
            "gmt_modified " +
            "FROM inventory_offline_stock_update_message " +
            "ORDER BY id " +
            "limit 50")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "message_id", property = "messageId"),
            @Result(column = "operation", property = "operation"),
            @Result(column = "parameter", property = "parameter"),
            @Result(column = "parameter_clazz", property = "parameterClazz"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<StockUpdateMessageDO> listByBatch();

    /**
     * 批量删除
     *
     * @param messageIds 库存更新消息的消息id集合字符串
     */
    @Delete("DELETE FROM inventory_offline_stock_update_message " +
            "WHERE message_id in (${messageIds})")
    void removeByBatch(@Param("messageIds") String messageIds);

    /**
     * 查询库存消息表的记录数
     *
     * @return 记录数
     */
    @Select("SELECT COUNT(*) FROM inventory_offline_stock_update_message")
    Long count();

}
