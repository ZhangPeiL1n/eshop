package com.zpl.eshop.customer.dto;

import java.util.Date;

/**
 * 退货工单DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 22:10
 **/
public class ReturnGoodsWorksheetDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 退货工单状态
     */
    private Integer returnGoodsWorksheetStatus;

    /**
     * 退货原因
     */
    private Integer returnGoodsReason;

    /**
     * 退货备注
     */
    private String returnGoodsComment;

    /**
     * 退货物流单号
     */
    private String returnGoodsCourierNumber;

    /**
     * 退货工单创建时间
     */
    private Date gmtCreate;

    /**
     * 退货工单修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getReturnGoodsWorksheetStatus() {
        return returnGoodsWorksheetStatus;
    }

    public void setReturnGoodsWorksheetStatus(Integer returnGoodsWorksheetStatus) {
        this.returnGoodsWorksheetStatus = returnGoodsWorksheetStatus;
    }

    public Integer getReturnGoodsReason() {
        return returnGoodsReason;
    }

    public void setReturnGoodsReason(Integer returnGoodsReason) {
        this.returnGoodsReason = returnGoodsReason;
    }

    public String getReturnGoodsComment() {
        return returnGoodsComment;
    }

    public void setReturnGoodsComment(String returnGoodsComment) {
        this.returnGoodsComment = returnGoodsComment;
    }

    public String getReturnGoodsCourierNumber() {
        return returnGoodsCourierNumber;
    }

    public void setReturnGoodsCourierNumber(String returnGoodsCourierNumber) {
        this.returnGoodsCourierNumber = returnGoodsCourierNumber;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
