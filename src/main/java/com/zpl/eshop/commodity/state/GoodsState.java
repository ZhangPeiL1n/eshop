package com.zpl.eshop.commodity.state;

import com.zpl.eshop.commodity.domain.GoodsDTO;

/**
 * 商品状态
 *
 * @author ZhangPeiL1n
 * @date 2022/9/7 15:21
 **/
public interface GoodsState {

    /**
     * 执行商品流转到当前状态的业务逻辑
     *
     * @param goods 商品
     * @throws Exception
     */
    public void doTransition(GoodsDTO goods) throws Exception;

    /**
     * 当前状态能否执行编辑操作
     *
     * @return 能否执行编辑操作
     */
    Boolean canEdit();

    /**
     * 当前商品能否执行审核操作
     *
     * @return 能否执行审核操作
     */
    Boolean canApprove();

    /**
     * 能否上架商品
     *
     * @param goods 商品
     * @return 能否上架商品
     */
    Boolean canPutOnShelves();

    /**
     * 能否下架商品
     *
     * @return 能否下架商品
     */
    Boolean canPullOffShelves();

    /**
     * 能否删除商品
     *
     * @param goods 商品
     * @return 能否删除商品
     */
    Boolean canRemove();
}
