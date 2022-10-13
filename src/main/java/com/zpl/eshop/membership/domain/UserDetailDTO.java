package com.zpl.eshop.membership.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户详细信息
 *
 * @author ZhangPeiL1n
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDetailDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 用户账号id
     */
    private Long userAccountId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 住址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
