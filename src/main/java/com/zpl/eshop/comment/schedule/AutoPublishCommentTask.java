package com.zpl.eshop.comment.schedule;

import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.comment.service.CommentAggregateService;
import com.zpl.eshop.comment.service.CommentInfoService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.order.service.OrderFacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/18 22:39
 **/
@EnableScheduling
@Component
public class AutoPublishCommentTask {
    private static final Logger logger = LoggerFactory.getLogger(AutoPublishCommentTask.class);

    /**
     * 订单中心Service组件
     */
    @Autowired
    private OrderFacadeService orderFacadeService;

    /**
     * 评论信息管理模块Service组件
     */
    @Autowired
    private CommentInfoService commentInfoService;

    @Autowired
    private CommentAggregateService commentAggregateService;

    /**
     * 每隔10分钟检查一次
     * 单位：毫秒
     */
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void execute() {
        try {
            // 先从订单中心查询确认时间超过7天，且没有发表评论的订单
            List<OrderInfoDTO> orderInfoDTOs = orderFacadeService.listNotPublishCommentOrders();
            if (orderInfoDTOs != null && orderInfoDTOs.size() > 0) {
                List<Long> orderInfoIds = new ArrayList<>(orderInfoDTOs.size());
                for (OrderInfoDTO orderInfoDTO : orderInfoDTOs) {
                    // 卫语句
                    if (orderInfoDTO.getOrderItems() == null || orderInfoDTO.getOrderItems().size() == 0) {
                        continue;
                    }

                    // 遍历订单中的订单项
                    for (OrderItemDTO orderItemDTO : orderInfoDTO.getOrderItems()) {
                        // 先保存自动发表的评论信息
                        CommentInfoDTO commentInfoDTO = commentInfoService.saveAutoPublishedCommentInfo(orderInfoDTO, orderItemDTO);
                        // 更新统计信息
                        commentAggregateService.updateCommentAggregate(commentInfoDTO);
                    }
                    orderInfoIds.add(orderInfoDTO.getId());
                }
                // 通知订单中心批量发表评论
                orderFacadeService.informBatchPublishCommentEvent(orderInfoIds);
            }
        } catch (Exception e) {
            logger.error("error", e);
        }
    }
}
