package com.zpl.eshop.schedule.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.wms.domain.GoodsAllocationStockDetailDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderItemDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderPickingItemDTO;
import com.zpl.eshop.wms.domain.SaleDeliveryOrderSendOutDetailDTO;
import com.zpl.eshop.wms.service.WmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 销售出库调度器实现类
 *
 * @author ZhangPeiL1n
 * @date 2022/8/17 20:38
 **/
@Component
public class SaleDeliverySchedulerImpl implements SaleDeliveryScheduler {

    /**
     * wms中心
     */
    @Autowired
    private WmsService wmsService;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 对订单条目进行调度销售出库
     * 主要是 销售出库单条目 的 拣货单明细 和 发货单明细
     *
     * @param orderItem 订单条目
     * @return 销售出库单
     */
    @Override
    public SaleDeliveryOrderItemDTO schedule(OrderItemDTO orderItem) throws Exception {
        //构造销售出库单条目
        SaleDeliveryOrderItemDTO saleDeliveryOrderItem = new SaleDeliveryOrderItemDTO();
        saleDeliveryOrderItem.setGoodsSkuId(orderItem.getGoodsSkuId());

        //商品货位明细数据,商品 货位 商检时间 上架件数 剩余件数
        // 商品A A-01 2022-01-01 10:00:00 40 40
        // 商品A A-01 2022-01-01 11:00:00 60 60
        // 商品A A-02 2022-01-01 12:00:00 40 40

        // 获取商品所在货位的库存明细
        List<GoodsAllocationStockDetailDTO> stockDetails = wmsService.listStockDetailsByGoodsSkuId(orderItem.getGoodsSkuId());

        // 拣货单条目明细，同一个货位的拣货单需要合并
        // 因为这个是扣减货位库存明细的，货位库存明细可能会像上面的例子中，同一个 货位A-01 有 2条明细
        // 所以需要这个来合并相同货位上的拣货条目
        Map<Long, SaleDeliveryOrderPickingItemDTO> pickingItemMap = new HashMap<>();
        // 出库单明细
        List<SaleDeliveryOrderSendOutDetailDTO> sendOutDetails = new ArrayList<>();

        Long purchaseQuantity = orderItem.getPurchaseQuantity();
        for (GoodsAllocationStockDetailDTO stockDetail : stockDetails) {
            // 当前货位库存明细满足购买数量
            if (stockDetail.getCurrentStockQuantity() >= purchaseQuantity) {
                updatePickingItem(orderItem.getGoodsSkuId(), pickingItemMap, stockDetail, purchaseQuantity);
                updateSendOutDetail(sendOutDetails, createSendOutDetail(stockDetail.getId(), purchaseQuantity));
                break;
                //  还要从其他货位上取货
            } else {
                // 处理拣货条目
                updatePickingItem(orderItem.getGoodsSkuId(), pickingItemMap, stockDetail, stockDetail.getCurrentStockQuantity());
                updateSendOutDetail(sendOutDetails, createSendOutDetail(stockDetail.getId(), stockDetail.getCurrentStockQuantity()));
                purchaseQuantity = purchaseQuantity - stockDetail.getPutOnQuantity();
            }
        }
        saleDeliveryOrderItem.setPickingItems(new ArrayList<>(pickingItemMap.values()));
        saleDeliveryOrderItem.setSendOutItems(sendOutDetails);

        return saleDeliveryOrderItem;
    }


    /**
     * 更新拣货条目
     *
     * @param pickingItemMap 拣货条目map
     * @param stockDetail    库存明细
     */
    private void updatePickingItem(Long goodsSkuId,
                                   Map<Long, SaleDeliveryOrderPickingItemDTO> pickingItemMap,
                                   GoodsAllocationStockDetailDTO stockDetail, Long pickingCount) throws Exception {
        SaleDeliveryOrderPickingItemDTO pickingItem = pickingItemMap.get(stockDetail.getGoodsAllocationId());
        if (pickingItem == null) {
            pickingItem = createPickingItem(goodsSkuId, stockDetail.getGoodsAllocationId(), 0L);
            pickingItemMap.put(stockDetail.getGoodsAllocationId(), pickingItem);
        }
        // 合并同一个货位的拣货条目
        pickingItem.setPickingCount(pickingItem.getPickingCount() + pickingCount);
    }

    /**
     * 更新发货条目
     *
     * @param sendOutDetails 发货条目集合
     * @param sendOutDetail  发货条目
     */
    private void updateSendOutDetail(List<SaleDeliveryOrderSendOutDetailDTO> sendOutDetails, SaleDeliveryOrderSendOutDetailDTO sendOutDetail) {
        sendOutDetails.add(sendOutDetail);
    }

    /**
     * 创建拣货条目
     *
     * @param goodsAllocationId 货位id
     * @param pickingCount      拣货数量
     * @return
     */
    private SaleDeliveryOrderPickingItemDTO createPickingItem(
            Long goodsSkuId, Long goodsAllocationId, Long pickingCount) throws Exception {
        SaleDeliveryOrderPickingItemDTO pickingItem = new SaleDeliveryOrderPickingItemDTO();
        pickingItem.setGoodsSkuId(goodsSkuId);
        pickingItem.setGoodsAllocationId(goodsAllocationId);
        pickingItem.setPickingCount(pickingCount);
        pickingItem.setGmtCreate(dateProvider.getCurrentTime());
        pickingItem.setGmtModified(dateProvider.getCurrentTime());
        return pickingItem;
    }

    /**
     * 创建发货明细
     * 这个不能合并
     *
     * @param goodsAllocationStockDetailId 货位明细id
     * @param sendOutCount                 发货数量
     * @return 发货明细
     */
    private SaleDeliveryOrderSendOutDetailDTO createSendOutDetail(Long goodsAllocationStockDetailId, Long sendOutCount) throws Exception {
        SaleDeliveryOrderSendOutDetailDTO sendOutDetail = new SaleDeliveryOrderSendOutDetailDTO();
        // 货位
        sendOutDetail.setGoodsAllocationStockDetailId(goodsAllocationStockDetailId);
        // 发货数
        sendOutDetail.setSendOutCount(sendOutCount);
        sendOutDetail.setGmtCreate(dateProvider.getCurrentTime());
        sendOutDetail.setGmtModified(dateProvider.getCurrentTime());
        return sendOutDetail;
    }
}
