package com.zpl.eshop.commodity.state;

import com.zpl.eshop.commodity.domain.GoodsDTO;
import org.springframework.stereotype.Component;

/**
 * 默认商品状态
 *
 * @author ZhangPeiL1n
 * @date 2022/9/7 15:56
 **/
@Component
public class DefaultGoodsState implements GoodsState {

    /**
     * 执行商品流转到当前状态的业务逻辑
     *
     * @param goods 商品
     * @throws Exception
     */
    @Override
    public void doTransition(GoodsDTO goods) throws Exception {
    }

    /**
     * 当前状态能否执行编辑操作
     *
     * @return 能否执行编辑操作
     */
    @Override
    public Boolean canEdit() {
        return false;
    }

    /**
     * 当前商品能否执行审核操作
     *
     * @return 能否执行审核操作
     */
    @Override
    public Boolean canApprove() {
        return false;
    }

    /**
     * 能否上架商品
     *
     * @return 能否上架商品
     */
    @Override
    public Boolean canPutOnShelves() {
        return false;
    }

    /**
     * 能否下架商品
     *
     * @return 能否下架商品
     */
    @Override
    public Boolean canPullOffShelves() {
        return false;
    }

    /**
     * 能否删除商品
     *
     * @return 能否删除商品
     */
    @Override
    public Boolean canRemove() {
        return false;
    }
}
