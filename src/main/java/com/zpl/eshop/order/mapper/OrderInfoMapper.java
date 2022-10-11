package com.zpl.eshop.order.mapper;

import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.domain.OrderInfoQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单管理的 Mapper 组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 16:59
 **/
@Mapper
public interface OrderInfoMapper {

    /**
     * 新建订单
     *
     * @param order 订单
     */
    @Insert("INSERT INTO order_info(" +
            "user_account_id, " +
            "username, " +
            "order_no, " +
            "order_status, " +
            "consignee, " +
            "delivery_address, " +
            "consignee_cell_phone_number, " +
            "freight, " +
            "pay_type, " +
            "total_amount, " +
            "discount_amount, " +
            "coupon_amount, " +
            "payable_amount, " +
            "invoice_title, " +
            "taxpayer_id, " +
            "order_comment, " +
            "is_published_comment, " +
            "confirm_receipt_time, " +
            "gmt_create, " +
            "gmt_modified" +
            ") VALUES (" +
            "#{userAccountId}," +
            "#{username}," +
            "#{orderNo}," +
            "#{orderStatus}," +
            "#{consignee}," +
            "#{deliveryAddress}," +
            "#{consigneeCellPhoneNumber}," +
            "#{freight}," +
            "#{payType}," +
            "#{totalAmount}," +
            "#{discountAmount}," +
            "#{couponAmount}," +
            "#{payableAmount}," +
            "#{invoiceTitle}," +
            "#{taxpayerId}," +
            "#{orderComment}," +
            "#{publishedComment}," +
            "#{confirmReceiptTime}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyProperty = "id", keyColumn = "id", useGeneratedKeys = true)
    void save(OrderInfoDO order);

    /**
     * 分页查询订单
     *
     * @param query 查询条件
     * @return 订单
     */
    @Select("SELECT "
            + "a.id,"
            + "a.order_no,"
            + "a.consignee,"
            + "a.total_amount,"
            + "a.discount_amount,"
            + "a.coupon_amount,"
            + "a.freight,"
            + "a.payable_amount,"
            + "a.pay_type,"
            + "a.order_status,"
            + "a.user_account_id,"
            + "a.username,"
            + "a.gmt_create "
            + "FROM order_info a,"
            + "("
            + "SELECT id "
            + "FROM order_info "
            + "WHERE user_account_id=#{userAccountId} "
            + "LIMIT #{offset},#{size} "
            + ") b "
            + "WHERE a.id=b.id"
    )
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "username", property = "username"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "consignee", property = "consignee"),
            @Result(column = "total_amount", property = "totalAmount"),
            @Result(column = "discount_amount", property = "discountAmount"),
            @Result(column = "coupon_amount", property = "couponAmount"),
            @Result(column = "freight", property = "freight"),
            @Result(column = "payable_amount", property = "payableAmount"),
            @Result(column = "pay_type", property = "payType"),
            @Result(column = "order_status", property = "orderStatus"),
            @Result(column = "gmt_create", property = "gmtCreate")
    })
    List<OrderInfoDO> listByPage(OrderInfoQuery query);

    /**
     * 查询订单详情
     *
     * @param id 订单id
     * @return 订单
     */
    @Select("SELECT "
            + "id,"
            + "order_no,"
            + "consignee,"
            + "delivery_address,"
            + "consignee_cell_phone_number,"
            + "total_amount,"
            + "discount_amount,"
            + "coupon_amount,"
            + "freight,"
            + "payable_amount,"
            + "pay_type,"
            + "invoice_title,"
            + "taxpayer_id,"
            + "order_status,"
            + "user_account_id,"
            + "username,"
            + "order_comment,"
            + "gmt_create "
            + "FROM order_info "
            + "WHERE id=#{id}"
    )
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "username", property = "username"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "consignee", property = "consignee"),
            @Result(column = "total_amount", property = "totalAmount"),
            @Result(column = "discount_amount", property = "discountAmount"),
            @Result(column = "coupon_amount", property = "couponAmount"),
            @Result(column = "freight", property = "freight"),
            @Result(column = "payable_amount", property = "payableAmount"),
            @Result(column = "pay_type", property = "payType"),
            @Result(column = "order_status", property = "orderStatus"),
            @Result(column = "delivery_address", property = "deliveryAddress"),
            @Result(column = "consignee_cell_phone_number", property = "consigneeCellPhoneNumber"),
            @Result(column = "invoice_title", property = "invoiceTitle"),
            @Result(column = "taxpayer_id", property = "taxpayerId"),
            @Result(column = "order_comment", property = "orderComment"),
            @Result(column = "gmt_create", property = "gmtCreate")
    })
    OrderInfoDO getById(@Param("id") Long id);

    /**
     * 更新订单状态
     *
     * @param order 订单
     */
    @Update("UPDATE order_info SET " +
            "order_status = #{orderStatus}," +
            "gmt_modified = #{gmtModified} " +
            "where id = #{id}")
    void updateStatus(OrderInfoDO order);

    /**
     * 查询所有未付款的订单
     *
     * @return 所有未付款的订单
     */
    @Select("SELECT "
            + "id,"
            + "gmt_create,"
            + "order_no,"
            + "consignee,"
            + "delivery_address,"
            + "consignee_cell_phone_number,"
            + "total_amount,"
            + "discount_amount,"
            + "coupon_amount,"
            + "freight,"
            + "payable_amount,"
            + "pay_type,"
            + "invoice_title,"
            + "taxpayer_id,"
            + "order_status,"
            + "user_account_id,"
            + "username,"
            + "order_comment "
            + "FROM order_info "
            + "WHERE order_status = 1"
    )
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "consignee", property = "consignee"),
            @Result(column = "total_amount", property = "totalAmount"),
            @Result(column = "discount_amount", property = "discountAmount"),
            @Result(column = "coupon_amount", property = "couponAmount"),
            @Result(column = "freight", property = "freight"),
            @Result(column = "payable_amount", property = "payableAmount"),
            @Result(column = "pay_type", property = "payType"),
            @Result(column = "order_status", property = "orderStatus"),
            @Result(column = "delivery_address", property = "deliveryAddress"),
            @Result(column = "consignee_cell_phone_number", property = "consigneeCellPhoneNumber"),
            @Result(column = "invoice_title", property = "invoiceTitle"),
            @Result(column = "taxpayer_id", property = "taxpayerId"),
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "username", property = "username"),
            @Result(column = "order_comment", property = "orderComment")
    })
    List<OrderInfoDO> listAllUnpaid();

    /**
     * 更新订单的确认收货时间
     *
     * @param order 订单
     */
    @Update("UPDATE order_info SET "
            + "confirm_receipt_time=#{confirmReceiptTime},"
            + "gmt_modified=#{gmtModified} "
            + "WHERE id=#{id}")
    void updateConfirmReceiptTime(OrderInfoDO order);

    /**
     * 查询处于待收货状态的订单
     *
     * @return 订单
     */
    @Select("SELECT "
            + "id,"
            + "gmt_create,"
            + "order_no,"
            + "consignee,"
            + "delivery_address,"
            + "consignee_cell_phone_number,"
            + "total_amount,"
            + "discount_amount,"
            + "coupon_amount,"
            + "freight,"
            + "payable_amount,"
            + "pay_type,"
            + "invoice_title,"
            + "taxpayer_id,"
            + "order_status,"
            + "user_account_id,"
            + "username,"
            + "order_comment "
            + "FROM order_info "
            + "WHERE status=#{status}"
    )
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "consignee", property = "consignee"),
            @Result(column = "total_amount", property = "totalAmount"),
            @Result(column = "discount_amount", property = "discountAmount"),
            @Result(column = "coupon_amount", property = "couponAmount"),
            @Result(column = "freight", property = "freight"),
            @Result(column = "payable_amount", property = "payableAmount"),
            @Result(column = "pay_type", property = "payType"),
            @Result(column = "order_status", property = "orderStatus"),
            @Result(column = "delivery_address", property = "deliveryAddress"),
            @Result(column = "consignee_cell_phone_number", property = "consigneeCellPhoneNumber"),
            @Result(column = "invoice_title", property = "invoiceTitle"),
            @Result(column = "taxpayer_id", property = "taxpayerId"),
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "username", property = "username"),
            @Result(column = "order_comment", property = "orderComment")
    })
    List<OrderInfoDO> listByStatus(@Param("status") Integer status);
}
