package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;

import java.util.Date;

/**
 * 帐号DO类
 *
 * @author ZhangPeiL1n
 * @date 2022/5/28 15:43
 **/
@Data
public class AccountDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
