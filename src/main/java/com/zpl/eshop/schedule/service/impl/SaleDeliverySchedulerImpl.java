package com.zpl.eshop.schedule.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.schedule.dao.ScheduleGoodsAllocationStockDetailDAO;
import com.zpl.eshop.schedule.dao.ScheduleOrderPickingItemDAO;
import com.zpl.eshop.schedule.dao.ScheduleOrderSendOutDetailDAO;
import com.zpl.eshop.schedule.domain.*;
import com.zpl.eshop.schedule.service.SaleDeliveryScheduler;
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
     * 调度中心货位明细DAO组件
     */
    @Autowired
    private ScheduleGoodsAllocationStockDetailDAO stockDetailDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 拣货条目管理DAO组件
     */
    @Autowired
    private ScheduleOrderPickingItemDAO pickingItemDAO;

    /**
     * 发货明细管理DAO组件
     */
    @Autowired
    private ScheduleOrderSendOutDetailDAO sendOutDetailDAO;


    /**
     * 调度销售出库
     *
     * @param orderItem 订单条目
     * @return 调度结果
     */
    @Override
    public SaleDeliveryScheduleResult schedule(OrderItemDTO orderItem) throws Exception {
        //构造销售出库单条目
        SaleDeliveryScheduleResult scheduleResult = new SaleDeliveryScheduleResult();
        scheduleResult.setOrderItem(orderItem);

        //商品货位明细数据,商品 货位 商检时间 上架件数 剩余件数
        // 商品A A-01 2022-01-01 10:00:00 40 40
        // 商品A A-01 2022-01-01 11:00:00 60 60
        // 商品A A-02 2022-01-01 12:00:00 40 40

        // 获取商品所在货位的库存明细
        List<ScheduleGoodsAllocationStockDetailDO> stockDetails = stockDetailDAO.listByGoodsSkuId(orderItem.getGoodsSkuId());

        // 拣货单条目明细，同一个货位的拣货单需要合并
        // 因为这个是扣减货位库存明细的，货位库存明细可能会像上面的例子中，同一个 货位A-01 有 2条明细
        // 所以需要这个来合并相同货位上的拣货条目
        Map<Long, ScheduleOrderPickingItemDTO> pickingItems = new HashMap<>();
        // 出库单明细
        List<ScheduleOrderSendOutDetailDTO> sendOutDetails = new ArrayList<>();

        // 剩余要发货的数量
        Long remainingSendOutQuantity = orderItem.getPurchaseQuantity();
        for (ScheduleGoodsAllocationStockDetailDO stockDetail : stockDetails) {
            // 当前货位库存明细满足购买数量
            if (stockDetail.getCurrentStockQuantity() >= remainingSendOutQuantity) {
                updatePickingItem(stockDetail, orderItem.getGoodsSkuId(), remainingSendOutQuantity, pickingItems);
                updateSendOutDetail(sendOutDetails, createSendOutDetail(stockDetail.getId(), remainingSendOutQuantity));
                break;
                //  还要从其他货位上取货
            }
            // 处理拣货条目
            updatePickingItem(stockDetail, orderItem.getGoodsSkuId(), stockDetail.getCurrentStockQuantity(), pickingItems);
            updateSendOutDetail(sendOutDetails, createSendOutDetail(stockDetail.getId(), stockDetail.getCurrentStockQuantity()));
            remainingSendOutQuantity = remainingSendOutQuantity - stockDetail.getPutOnQuantity();
        }
        scheduleResult.setPickingItems(new ArrayList<>(pickingItems.values()));
        scheduleResult.setSendOutDetails(sendOutDetails);

        return scheduleResult;
    }

    /**
     * 更新拣货条目
     *
     * @param pickingItems 拣货条目map
     * @param stockDetail  库存明细
     */
    private void updatePickingItem(
            ScheduleGoodsAllocationStockDetailDO stockDetail,
            Long goodsSkuId,
            Long pickingCount,
            Map<Long, ScheduleOrderPickingItemDTO> pickingItems) throws Exception {
        ScheduleOrderPickingItemDTO pickingItem = pickingItems.get(stockDetail.getGoodsAllocationId());
        if (pickingItem == null) {
            pickingItem = createPickingItem(goodsSkuId, stockDetail.getGoodsAllocationId());
            pickingItems.put(stockDetail.getGoodsAllocationId(), pickingItem);
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
    private void updateSendOutDetail(List<ScheduleOrderSendOutDetailDTO> sendOutDetails, ScheduleOrderSendOutDetailDTO sendOutDetail) {
        sendOutDetails.add(sendOutDetail);
    }

    /**
     * 创建拣货条目
     *
     * @param goodsAllocationId 货位id
     * @return
     */
    private ScheduleOrderPickingItemDTO createPickingItem(
            Long goodsSkuId, Long goodsAllocationId) throws Exception {
        ScheduleOrderPickingItemDTO pickingItem = new ScheduleOrderPickingItemDTO();
        pickingItem.setGoodsSkuId(goodsSkuId);
        pickingItem.setGoodsAllocationId(goodsAllocationId);
        pickingItem.setPickingCount(0L);
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
    private ScheduleOrderSendOutDetailDTO createSendOutDetail(Long goodsAllocationStockDetailId, Long sendOutCount) throws Exception {
        ScheduleOrderSendOutDetailDTO sendOutDetail = new ScheduleOrderSendOutDetailDTO();
        // 货位
        sendOutDetail.setGoodsAllocationStockDetailId(goodsAllocationStockDetailId);
        // 发货数
        sendOutDetail.setSendOutCount(sendOutCount);
        sendOutDetail.setGmtCreate(dateProvider.getCurrentTime());
        sendOutDetail.setGmtModified(dateProvider.getCurrentTime());
        return sendOutDetail;
    }

    /**
     * 获取订单条目的调度结果
     *
     * @param order 订单
     * @return 调度结果
     * @throws Exception
     */
    @Override
    public SaleDeliveryScheduleResult getScheduleResult(OrderItemDTO orderItem) throws Exception {
        List<ScheduleOrderPickingItemDO> pickingItems = pickingItemDAO
                .listByOrderItemId(orderItem.getOrderInfoId(), orderItem.getId());
        List<ScheduleOrderSendOutDetailDO> sendOutDetails = sendOutDetailDAO
                .listByOrderItemId(orderItem.getOrderInfoId(), orderItem.getId());

        SaleDeliveryScheduleResult scheduleResult = new SaleDeliveryScheduleResult();
        scheduleResult.setOrderItem(orderItem);
        scheduleResult.setPickingItems(ObjectUtils.convertList(
                pickingItems, ScheduleOrderPickingItemDTO.class));
        scheduleResult.setSendOutDetails(ObjectUtils.convertList(
                sendOutDetails, ScheduleOrderSendOutDetailDTO.class));

        return scheduleResult;
    }
}
