package com.zpl.eshop.membership.domain;

/**
 * 用户详细信息显示的VO类
 *
 * @author ZhangPeiL1n
 */
public class UserDetailShowVO {

    /**
     * 用户账号
     */
    private UserAccountVO userAccount;
    /**
     * 用户详细信息
     */
    private UserDetailVO userDetail;

    public UserAccountVO getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountVO userAccount) {
        this.userAccount = userAccount;
    }

    public UserDetailVO getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailVO userDetail) {
        this.userDetail = userDetail;
    }

}
