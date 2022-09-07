package com.zpl.eshop.commodity.state;

import com.zpl.eshop.commodity.constant.GoodsStatus;
import com.zpl.eshop.commodity.domain.GoodsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ZhangPeiL1n
 * @date 2022/9/7 15:22
 **/
@Component
public class GoodsStateManagerImpl implements GoodsStateManager {

    /**
     * 商品状态工厂
     */
    @Autowired
    private GoodsStateFactory goodsStateFactory;

    /**
     * 创建一个商品
     *
     * @param goods 商品
     */
    @Override
    public void create(GoodsDTO goods) throws Exception {
        goodsStateFactory.getByGoodsStatus(GoodsStatus.WAIT_FOR_APPROVE).doTransition(goods);
    }

    /**
     * 当前商品能否执行编辑操作
     *
     * @param goods 商品
     * @return 能否执行编辑操作
     */
    @Override
    public Boolean canEdit(GoodsDTO goods) {
        GoodsState goodsState = goodsStateFactory.get(goods);
        return goodsState.canEdit();
    }

    /**
     * 编辑商品
     *
     * @param goods 商品
     */
    @Override
    public void edit(GoodsDTO goods) throws Exception {
        goodsStateFactory.getByGoodsStatus(GoodsStatus.WAIT_FOR_APPROVE).doTransition(goods);
    }

    /**
     * 当前商品能否执行审核操作
     *
     * @param goods 商品
     * @return 能否执行编辑操作
     */
    @Override
    public Boolean canApprove(GoodsDTO goods) {
        GoodsState goodsState = goodsStateFactory.get(goods);
        return goodsState.canApprove();
    }

    /**
     * 审核商品
     *
     * @param goods         商品
     * @param approveResult 审核结果
     */
    @Override
    public void approve(GoodsDTO goods, Integer approveResult) throws Exception {
        if (ApproveResult.APPROVE_PASS.equals(approveResult)) {
            goodsStateFactory.getByGoodsStatus(GoodsStatus.WAIT_FOR_PUT_ON_SHELVES).doTransition(goods);
        } else if (ApproveResult.APPROVE_REJECT.equals(approveResult)) {
            goodsStateFactory.getByGoodsStatus(GoodsStatus.APPROVE_REJECT).doTransition(goods);
        }
    }

    /**
     * 能否上架商品
     *
     * @param goods 商品
     * @return 能否上架商品
     */
    @Override
    public Boolean canPutOnShelves(GoodsDTO goods) {
        GoodsState goodsState = goodsStateFactory.get(goods);
        return goodsState.canPutOnShelves();
    }

    /**
     * 上架商品
     *
     * @param goods 商品
     */
    @Override
    public void putOnShelves(GoodsDTO goods) throws Exception {
        goodsStateFactory.getByGoodsStatus(GoodsStatus.PUTTED_ON_SHELVES).doTransition(goods);
    }

    /**
     * 能否下架商品
     *
     * @param goods 商品
     * @return 能否下架商品
     */
    @Override
    public Boolean canPullOffShelves(GoodsDTO goods) {
        GoodsState goodsState = goodsStateFactory.get(goods);
        return goodsState.canPullOffShelves();
    }

    /**
     * 下架商品
     *
     * @param goods 商品
     */
    @Override
    public void pullOffShelves(GoodsDTO goods) throws Exception {
        goodsStateFactory.getByGoodsStatus(GoodsStatus.WAIT_FOR_PUT_ON_SHELVES).doTransition(goods);
    }

    /**
     * 能否删除商品
     *
     * @param goods 商品
     * @return 能否删除商品
     */
    @Override
    public Boolean canRemove(GoodsDTO goods) {
        GoodsState goodsState = goodsStateFactory.get(goods);
        return goodsState.canRemove();
    }
}
