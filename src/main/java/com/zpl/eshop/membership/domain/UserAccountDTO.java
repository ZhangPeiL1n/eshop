package com.zpl.eshop.membership.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户账号
 *
 * @author ZhangPeiL1n
 * @date 2022/8/30 21:51
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccountDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String cellPhoneNumber;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}