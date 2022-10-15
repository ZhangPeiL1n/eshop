package com.zpl.eshop.order.mapper;

import com.zpl.eshop.order.domain.ReturnGoodsApplyDO;
import org.apache.ibatis.annotations.*;

/**
 * 退货申请管理Mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/10/15 19:40
 **/
@Mapper
public interface ReturnGoodsApplyMapper {

    /**
     * 新增退货申请
     *
     * @param apply 退货申请
     */
    @Insert("INSERT INTO order_return_goods_apply("
            + "order_info_id,"
            + "return_goods_reason,"
            + "return_goods_comment,"
            + "return_goods_apply_status,"
            + "return_goods_logistic_code,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{orderInfoId},"
            + "#{returnGoodsReason},"
            + "#{returnGoodsComment},"
            + "#{returnGoodsApplyStatus},"
            + "#{returnGoodsLogisticCode},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(ReturnGoodsApplyDO apply);

    /**
     * 根据id查询退货申请
     *
     * @param id 退货申请id
     * @return 退货申请
     */
    @Select("SELECT "
            + "id,"
            + "order_info_id,"
            + "return_goods_reason,"
            + "return_goods_comment,"
            + "return_goods_apply_status,"
            + "return_goods_logistic_code,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM order_return_goods_apply "
            + "WHERE order_info_id=#{orderInfoId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "order_info_id", property = "orderInfoId"),
            @Result(column = "return_goods_reason", property = "returnGoodsReason"),
            @Result(column = "return_goods_comment", property = "returnGoodsComment"),
            @Result(column = "return_goods_apply_status", property = "returnGoodsApplyStatus"),
            @Result(column = "return_goods_logistic_code", property = "returnGoodsLogisticCode"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    ReturnGoodsApplyDO getByOrderInfoId(@Param("orderInfoId") Long orderInfoId);

    /**
     * 更新退货申请的状态
     *
     * @param apply 退货申请
     */
    @Update("UPDATE order_return_goods_apply SET "
            + "return_goods_apply_status=#{returnGoodsApplyStatus}, "
            + "gmt_modified=#{gmtModified} "
            + "WHERE id=#{id}")
    void updateStatus(ReturnGoodsApplyDO apply);
}
