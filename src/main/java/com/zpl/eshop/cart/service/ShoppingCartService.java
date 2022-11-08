package com.zpl.eshop.cart.service;

import com.zpl.eshop.cart.domain.ShoppingCartDTO;

/**
 * 购物车管理模块Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/20 22:45
 **/
public interface ShoppingCartService {

    /**
     * 添加购物车条目
     *
     * @param userAccountId 用户帐号id
     * @param goodsSkuId    商品条目
     * @return 处理结果
     * @throws Exception
     */
    Boolean addShoppingCartItem(Long userAccountId, Long goodsSkuId) throws Exception;

    /**
     * 查看用户购物车中的数据
     *
     * @param userAccountId 用户帐号id
     * @return 购物车DTO对象
     * @throws Exception
     */
    ShoppingCartDTO getShoppingCartDtoByUserAccountId(Long userAccountId) throws Exception;


}
