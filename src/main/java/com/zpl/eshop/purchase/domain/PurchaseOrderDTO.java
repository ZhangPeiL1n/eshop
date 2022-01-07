package com.zpl.eshop.purchase.domain;

import java.util.Date;

/**
 * 采购单DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/1/3 22:02
 **/
public class PurchaseOrderDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 预计到货时间
     */
    private Date expectArrivalTime;

    /**
     * 采购联系人
     */
    private String contactor;

    /**
     * 采购联系人电话
     */
    private String contactPhoneNumber;

    /**
     * 采购联系人邮箱
     */
    private String contactEmail;

    /**
     * 采购单备注
     */
    private String purchaseOrderComment;

    /**
     * 采购员
     */
    private String purchaser;

    /**
     * 采购单状态
     */
    private Integer purchaseOrderStatus;

    /**
     * 采购单创建时间
     */
    private Date gmtCreateTime;

    /**
     * 采购单修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Date getExpectArrivalTime() {
        return expectArrivalTime;
    }

    public void setExpectArrivalTime(Date expectArrivalTime) {
        this.expectArrivalTime = expectArrivalTime;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPurchaseOrderComment() {
        return purchaseOrderComment;
    }

    public void setPurchaseOrderComment(String purchaseOrderComment) {
        this.purchaseOrderComment = purchaseOrderComment;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public Integer getPurchaseOrderStatus() {
        return purchaseOrderStatus;
    }

    public void setPurchaseOrderStatus(Integer purchaseOrderStatus) {
        this.purchaseOrderStatus = purchaseOrderStatus;
    }

    public Date getGmtCreateTime() {
        return gmtCreateTime;
    }

    public void setGmtCreateTime(Date gmtCreateTime) {
        this.gmtCreateTime = gmtCreateTime;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
