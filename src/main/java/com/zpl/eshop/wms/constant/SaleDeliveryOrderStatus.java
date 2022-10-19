package com.zpl.eshop.wms.constant;

/**
 * 销售出库单状态
 *
 * @author ZhangPeiL1n
 * @date 2022/8/20 12:42
 **/
public class SaleDeliveryOrderStatus {

    /**
     * 编辑中
     */
    public static final Integer EDITING = 1;

    /**
     * 待审核
     */
    public static final Integer WAIT_FOR_APPROVE = 2;

    /**
     * 已完成
     */
    public static final Integer FINISHED = 3;

    private SaleDeliveryOrderStatus() {
    }
}
