package com.zpl.eshop.order.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.inventory.service.InventoryService;
import com.zpl.eshop.order.constant.OrderOperateType;
import com.zpl.eshop.order.dao.OrderInfoDAO;
import com.zpl.eshop.order.dao.OrderItemDAO;
import com.zpl.eshop.order.dao.OrderOperateLogDAO;
import com.zpl.eshop.order.domain.OrderInfoDO;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.order.state.OrderStateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 自动取消订单任务
 *
 * @author ZhangPeiL1n
 * @date 2022/9/15 21:38
 **/
@Component
public class AutoCancelOrderTask {


    private static final Logger logger = LoggerFactory.getLogger(AutoCancelOrderTask.class);
    /**
     * 未支付订单超时时间
     */
    private final Long timeout = 24 * 60 * 60 * 1000L;
    /**
     * 订单管理DAO组件
     */
    @Autowired
    private OrderInfoDAO orderInfoDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;
    /**
     * 订单条目DAO组件
     */
    @Autowired
    private OrderItemDAO orderItemDAO;
    /**
     * 订单状态管理组件
     */
    @Autowired
    private OrderStateManager orderStateManager;
    /**
     * 库存中心接口
     */
    @Autowired
    private InventoryService inventoryService;
    /**
     * 订单操作日志DAO
     */
    @Autowired
    private OrderOperateLogDAO orderOperateLogDAO;
    /**
     * 订单操作内容工厂
     */
    @Autowired
    private OrderOperateLogFactory orderOperateLogFactory;

    @Scheduled(fixedRate = 60 * 1000)
    public void task() {
        try {
            Date currentTime = dateProvider.getCurrentTime();
            orderInfoDAO.listAllUnpaid().forEach(orderInfoDO -> {
                try {
                    OrderInfoDTO order = getOrderInfoDTO(orderInfoDO);
                    if (dateProvider.getCurrentTime().getTime() - order.getGmtCreate().getTime() < timeout) {
                        return;
                    }
                    orderStateManager.cancelOrder(order);
                    inventoryService.informCancelOrderEvent(order);
                    orderOperateLogDAO.save(orderOperateLogFactory.get(order, OrderOperateType.AUTO_CANCEL_ORDER));
                } catch (Exception e) {
                    logger.error("error", e);
                }
            });
        } catch (Exception e) {
            logger.error("error", e);
        }
    }

    /**
     * 获取订单DTO
     *
     * @param order 订单
     * @return 订单DTO
     */
    private OrderInfoDTO getOrderInfoDTO(OrderInfoDO order) throws Exception {
        OrderInfoDTO orderInfoDTO = order.clone(OrderInfoDTO.class);
        List<OrderItemDTO> orderItemDTOList = ObjectUtils.convertList(orderItemDAO.listByOrderInfoId(orderInfoDTO.getId()), OrderItemDTO.class);
        orderInfoDTO.setOrderItems(orderItemDTOList);
        return orderInfoDTO;
    }
}
