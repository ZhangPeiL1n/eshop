package com.zpl.eshop.auth.domain;

import lombok.Data;

/**
 * 帐号查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/5/28 15:48
 **/
@Data
public class AccountQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 查询记录数
     */
    private Integer size;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;
}
