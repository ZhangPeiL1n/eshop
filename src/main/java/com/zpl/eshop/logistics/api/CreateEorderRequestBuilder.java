package com.zpl.eshop.logistics.api;

import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建电子面单的请求的构建器
 *
 * @author ZhangPeiL1n
 * @date 2022/10/22 23:55
 **/
public class CreateEorderRequestBuilder {

    private final CreateEorderRequest request = new CreateEorderRequest();

    public static CreateEorderRequestBuilder get() {
        return new CreateEorderRequestBuilder();
    }

    /**
     * 构建订单相关的数据
     *
     * @param order 订单
     * @return 构建器
     * @throws Exception
     */
    public CreateEorderRequestBuilder buildOrderRelatedInfo(
            OrderInfoDTO order) throws Exception {
        request.setOrderNo(order.getOrderNo());
        request.setFreight(order.getFreight());
        return this;
    }

    /**
     * 构建收件人信息
     *
     * @param order 订单
     * @return 构建器
     * @throws Exception
     */
    public CreateEorderRequestBuilder buildReceiver(OrderInfoDTO order) throws Exception {
        CreateEorderRequest.Receiver receiver = new CreateEorderRequest.Receiver();
        receiver.setConsignee(order.getConsignee());
        receiver.setConsigneeCellPhoneNumber(order.getConsigneeCellPhoneNumber());
        receiver.setDeliveryAddress(order.getDeliveryAddress());
        request.setReceiver(receiver);
        return this;
    }

    /**
     * 构建商品列表
     *
     * @param order 订单
     * @return 构建器
     * @throws Exception
     */
    public CreateEorderRequestBuilder buildGoodsList(OrderInfoDTO order) throws Exception {
        List<CreateEorderRequest.Goods> goodsList = new ArrayList<>();

        for (OrderItemDTO orderItem : order.getOrderItems()) {
            CreateEorderRequest.Goods goods = new CreateEorderRequest.Goods();
            goods.setGoodsName(orderItem.getGoodsName());
            goods.setPurchaseQuantity(orderItem.getPurchaseQuantity());
            goods.setGrossWeight(orderItem.getGoodsGrossWeight());
            goodsList.add(goods);
        }
        request.setGoodsList(goodsList);
        return this;
    }

    /**
     * 构建总数据指标
     *
     * @param order 订单
     * @return 构建器
     * @throws Exception
     */
    public CreateEorderRequestBuilder buildTotalDataMetric(OrderInfoDTO order) throws Exception {
        Double totalGrossWeight = 0.0;
        Long totalPurchaseQuantity = 0L;
        Double totalVolume = 0.0;

        for (OrderItemDTO orderItem : order.getOrderItems()) {
            totalGrossWeight += orderItem.getGoodsGrossWeight();
            totalPurchaseQuantity += orderItem.getPurchaseQuantity();
            totalVolume += orderItem.getGoodsLength() * orderItem.getGoodsWidth() * orderItem.getGoodsHeight();
        }

        request.setTotalGrossWeight(totalGrossWeight);
        request.setTotalPurchaseQuantity(totalPurchaseQuantity);
        request.setTotalVolume(totalVolume);

        return this;
    }

    /**
     * 创建请求
     *
     * @return 请求
     * @throws Exception
     */
    public CreateEorderRequest create() throws Exception {
        return request;
    }
}
