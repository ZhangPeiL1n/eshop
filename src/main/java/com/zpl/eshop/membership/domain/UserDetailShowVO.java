package com.zpl.eshop.membership.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户详细信息显示的VO类
 *
 * @author ZhangPeiL1n
 */
@Data
public class UserDetailShowVO {

    /**
     * 用户账号
     */
    private UserAccountVO userAccount;

    /**
     * 用户详细信息
     */
    private UserDetailVO userDetail;
}
