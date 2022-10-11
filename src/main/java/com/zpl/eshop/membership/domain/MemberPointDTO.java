package com.zpl.eshop.membership.domain;

import com.zpl.eshop.common.util.AbstractObject;

import java.util.Date;

/**
 * 会员积分
 *
 * @author ZhangPeiL1n
 */
public class MemberPointDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;
    /**
     * 用户账号id
     */
    private Long userAccountId;
    /**
     * 会员等级
     */
    private Integer point;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
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
