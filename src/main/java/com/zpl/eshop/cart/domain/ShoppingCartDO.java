package com.zpl.eshop.cart.domain;

import lombok.Data;

import java.util.Date;

/**
 * 购物车
 *
 * @author ZhangPeiL1n
 * @date 2022/1/20 21:53
 **/
@Data
public class ShoppingCartDO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户帐号id
     */
    private Long userAccountId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
