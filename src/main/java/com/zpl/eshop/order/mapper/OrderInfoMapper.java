package com.zpl.eshop.order.mapper;

import com.zpl.eshop.order.domain.OrderInfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

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
            "#{publishComment}," +
            "#{confirmReceiptTime}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyProperty = "id", keyColumn = "id", useGeneratedKeys = true)
    void save(OrderInfoDO order);
}
