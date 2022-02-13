package com.zpl.eshop.inventory.constant;

/**
 * 商品库存更新类型
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 13:57
 **/
public class GoodsStockUpdateOperation {
    private GoodsStockUpdateOperation() {
    }

    /**
     * 提交订单
     */
    public static final Integer SUBMIT_ORDER = 1;
    /**
     * 支付订单
     */
    public static final Integer PAY_ORDER = 2;
    /**
     * 取消订单
     */
    public static final Integer CANCEL_ORDER = 3;
}
