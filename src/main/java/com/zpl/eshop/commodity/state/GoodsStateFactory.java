package com.zpl.eshop.commodity.state;

import com.zpl.eshop.commodity.constant.GoodsStatus;
import com.zpl.eshop.commodity.domain.GoodsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商品状态工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/9/7 15:43
 **/
@Component
public class GoodsStateFactory {

    /**
     * 待审核状态
     */
    @Autowired
    private WaitForApproveGoodsState waitForApproveGoodsState;

    /**
     * 待上架状态
     */
    @Autowired
    private WaitForPutOnShelvesGoodsState waitForPutOnShelvesGoodsState;

    /**
     * 审核未通过状态
     */
    @Autowired
    private ApproveRejectGoodsState approveRejectGoodsState;

    /**
     * 已上架状态
     */
    @Autowired
    private PuttedOnShelvesGoodsState puttedOnShelvesGoodsState;

    /**
     * 默认商品状态
     */
    @Autowired
    private DefaultGoodsState defaultGoodsState;

    /**
     * 获取商品对应的状态组件
     *
     * @param goods 商品
     * @return 状态组件
     */
    public GoodsState get(GoodsDTO goods) {
        return choose(goods.getStatus());
    }

    /**
     * 根据商品状态值获取商品状态对象
     *
     * @param goodsStatus 商品状态值
     * @return 商品状态对象
     */
    public GoodsState getByGoodsStatus(Integer goodsStatus) {
        return choose(goodsStatus);
    }

    private GoodsState choose(Integer goodsStatus) {
        if (GoodsStatus.WAIT_FOR_APPROVE.equals(goodsStatus)) {
            return waitForApproveGoodsState;
        } else if (GoodsStatus.WAIT_FOR_PUT_ON_SHELVES.equals(goodsStatus)) {
            return waitForPutOnShelvesGoodsState;
        } else if (GoodsStatus.APPROVE_REJECT.equals(goodsStatus)) {
            return approveRejectGoodsState;
        } else if (GoodsStatus.PUTTED_ON_SHELVES.equals(goodsStatus)) {
            return puttedOnShelvesGoodsState;
        }
        return defaultGoodsState;
    }
}
