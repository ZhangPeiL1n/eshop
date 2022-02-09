package com.zpl.eshop.cart.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 购物车VO类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/9 0:31
 **/
@Data
public class ShoppingCartVO {

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
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 购物车条目集合
     */
    private List<ShoppingCartItemVO> shoppingCartItemVOList;
}
