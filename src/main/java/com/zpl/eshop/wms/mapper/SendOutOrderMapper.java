package com.zpl.eshop.wms.mapper;

import com.zpl.eshop.wms.domain.SendOutOrderDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 发货单管理Mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/10/14 22:10
 **/
@Mapper
public interface SendOutOrderMapper {

    /**
     * 新增发货单
     *
     * @param sendOutOrder 发货单
     */
    @Insert("INSERT INTO wms_send_out_order("
            + "sale_delivery_order_id,"
            + "user_account_id,"
            + "username,"
            + "order_id,"
            + "order_no,"
            + "consignee,"
            + "delivery_address,"
            + "consignee_cell_phone_number,"
            + "freight,"
            + "pay_type,"
            + "total_amount,"
            + "discount_amount,"
            + "coupon_amount,"
            + "payable_amount,"
            + "invoice_title,"
            + "taxpayer_id,"
            + "order_comment,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{saleDeliveryOrderId},"
            + "#{userAccountId},"
            + "#{username},"
            + "#{orderId},"
            + "#{orderNo},"
            + "#{consignee},"
            + "#{deliveryAddress},"
            + "#{consigneeCellPhoneNumber},"
            + "#{freight},"
            + "#{payType},"
            + "#{totalAmount},"
            + "#{discountAmount},"
            + "#{couponAmount},"
            + "#{payableAmount},"
            + "#{invoiceTitle},"
            + "#{taxpayerId},"
            + "#{orderComment},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(SendOutOrderDO sendOutOrder);
}
