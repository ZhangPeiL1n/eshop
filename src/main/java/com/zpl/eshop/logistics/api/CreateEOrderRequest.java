package com.zpl.eshop.logistics.api;

import lombok.Data;

import java.util.List;

/**
 * 创建电子面单请求
 *
 * @author ZhangPeiL1n
 * @date 2022/10/22 23:50
 **/
@Data
public class CreateEOrderRequest {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 运费
     */
    private Double freight;

    /**
     * 收件人信息
     */
    private Receiver receiver;

    /**
     * 商品信息
     */
    private List<Goods> goodsList;

    /**
     * 商品总毛重
     */
    private Double totalGrossWeight;

    /**
     * 商品购买总件数
     */
    private Long totalPurchaseQuantity;

    /**
     * 商品总体积
     */
    private Double totalVolume;

    /**
     * 收件人信息
     */
    @Data
    public static class Receiver {

        /**
         * 收件人姓名
         */
        private String consignee;

        /**
         * 收件人电话号码
         */
        private String consigneeCellPhoneNumber;

        /**
         * 收货地址
         */
        private String deliveryAddress;
    }

    /**
     * 商品信息
     */
    @Data
    public static class Goods {

        /**
         * 商品名称
         */
        private String goodsName;

        /**
         * 购买数量
         */
        private Long purchaseQuantity;

        /**
         * 商品毛重
         */
        private Double grossWeight;
    }
}
