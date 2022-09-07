package com.zpl.eshop.commodity.state;

import com.zpl.eshop.commodity.constant.GoodsStatus;
import com.zpl.eshop.commodity.dao.GoodsDAO;
import com.zpl.eshop.commodity.domain.GoodsDO;
import com.zpl.eshop.commodity.domain.GoodsDTO;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 审核未通过状态
 *
 * @author ZhangPeiL1n
 * @date 2022/9/7 15:48
 **/
@Component
public class ApproveRejectGoodsState implements GoodsState {

    /**
     * 商品管理DAO组件
     */
    @Autowired
    private GoodsDAO goodsDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 执行商品流转到当前状态的业务逻辑
     *
     * @param goods 商品
     * @throws Exception
     */
    @Override
    public void doTransition(GoodsDTO goods) throws Exception {
        goods.setStatus(GoodsStatus.APPROVE_REJECT);
        goods.setGmtModified(dateProvider.getCurrentTime());
        goodsDAO.updateStatus(goods.clone(GoodsDO.class));
    }

    /**
     * 当前状态能否执行编辑操作
     *
     * @return 能否执行编辑操作
     */
    @Override
    public Boolean canEdit() {
        return true;
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
        return true;
    }
}
