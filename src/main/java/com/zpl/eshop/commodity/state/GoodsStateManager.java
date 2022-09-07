package com.zpl.eshop.commodity.state;

import com.zpl.eshop.commodity.domain.GoodsDTO;

/**
 * 商品状态管理器
 *
 * @author ZhangPeiL1n
 * @date 2022/9/7 15:21
 **/
public interface GoodsStateManager {

    /**
     * 创建一个商品
     *
     * @param goods 商品
     */
    void create(GoodsDTO goods) throws Exception;

    /**
     * 当前商品能否执行编辑操作
     *
     * @param goods 商品
     * @return 能否执行编辑操作
     */
    Boolean canEdit(GoodsDTO goods);

    /**
     * 编辑商品
     *
     * @param goods 商品
     */
    void edit(GoodsDTO goods) throws Exception;

    /**
     * 当前商品能否执行审核操作
     *
     * @param goods 商品
     * @return 能否执行审核操作
     */
    Boolean canApprove(GoodsDTO goods);

    /**
     * 审核商品
     *
     * @param goods         商品
     * @param approveResult 审核结果
     */
    void approve(GoodsDTO goods, Integer approveResult) throws Exception;

    /**
     * 能否上架商品
     *
     * @param goods 商品
     * @return 能否上架商品
     */
    Boolean canPutOnShelves(GoodsDTO goods);

    /**
     * 上架商品
     *
     * @param goods 商品
     */
    void putOnShelves(GoodsDTO goods) throws Exception;

    /**
     * 能否下架商品
     *
     * @param goods 商品
     * @return 能否下架商品
     */
    Boolean canPullOffShelves(GoodsDTO goods);

    /**
     * 下架商品
     *
     * @param goods 商品
     */
    void pullOffShelves(GoodsDTO goods) throws Exception;

    /**
     * 能否删除商品
     *
     * @param goods 商品
     * @return 能否删除商品
     */
    Boolean canRemove(GoodsDTO goods);
}
